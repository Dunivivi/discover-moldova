package com.travel.moldova.web.rest;

import com.travel.moldova.domain.User;
import com.travel.moldova.repository.CompanyRepository;
import com.travel.moldova.repository.UserRepository;
import com.travel.moldova.security.SecurityUtils;
import com.travel.moldova.service.MailService;
import com.travel.moldova.service.UserService;
import com.travel.moldova.service.dto.AdminUserDTO;
import com.travel.moldova.service.dto.CreateUserCompanyDTO;
import com.travel.moldova.service.dto.CreateUserDTO;
import com.travel.moldova.service.dto.PasswordChangeDTO;
import com.travel.moldova.web.rest.errors.CompanyAlreadyUsedException;
import com.travel.moldova.web.rest.errors.EmailAlreadyUsedException;
import com.travel.moldova.web.rest.errors.InvalidPasswordException;
import com.travel.moldova.web.rest.errors.LoginAlreadyUsedException;
import com.travel.moldova.web.rest.vm.ManagedUserVM;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api")
public class AccountResource {

    private final Logger log = LoggerFactory.getLogger(AccountResource.class);
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final UserService userService;
    private final MailService mailService;

    public AccountResource(UserRepository userRepository, CompanyRepository companyRepository, UserService userService, MailService mailService) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.userService = userService;
        this.mailService = mailService;
    }

    private static boolean isPasswordLengthInvalid(String password) {
        return (
            StringUtils.isEmpty(password) ||
                password.length() < ManagedUserVM.PASSWORD_MIN_LENGTH ||
                password.length() > ManagedUserVM.PASSWORD_MAX_LENGTH
        );
    }

    /**
     * {@code POST  /register} : register the user.
     *
     * @param managedUserVM the managed user View Model.
     * @throws InvalidPasswordException  {@code 400 (Bad Request)} if the password is incorrect.
     * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already used.
     * @throws LoginAlreadyUsedException {@code 400 (Bad Request)} if the login is already used.
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccount(@Valid @RequestBody ManagedUserVM managedUserVM) {
        if (isPasswordLengthInvalid(managedUserVM.getPassword())) {
            throw new InvalidPasswordException();
        }
        User user = userService.registerUser(managedUserVM, managedUserVM.getPassword());
        mailService.sendActivationEmail(user);
    }

    @PostMapping("/register/user")
    public ResponseEntity<HttpStatus> registerUserAccount(@Valid @RequestBody CreateUserDTO userDTO) throws URISyntaxException {
        if (userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyUsedException();
        }
        var user = userService.registerSimpleUser(userDTO);
        return ResponseEntity.created(new URI("" + user.getId())).body(HttpStatus.CREATED);
    }

    @PostMapping("/register/user-company")
    public ResponseEntity<HttpStatus> registerUserCompanyAccount(@Valid @RequestBody CreateUserCompanyDTO companyDTO) throws URISyntaxException {
        if (userRepository.findOneByEmailIgnoreCase(companyDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyUsedException();
        }

        if(companyRepository.findOneByName(companyDTO.getCompanyName()).isPresent()) {
            throw new CompanyAlreadyUsedException();
        }

        var user = userService.registerCompanyUser(companyDTO);
        return ResponseEntity.created(new URI("" + user.getId())).body(HttpStatus.CREATED);
    }


    /**
     * {@code GET  /authenticate} : check if the user is authenticated, and return its login.
     *
     * @param request the HTTP request.
     * @return the login if the user is authenticated.
     */
    @GetMapping("/authenticate")
    public String isAuthenticated(HttpServletRequest request) {
        log.debug("REST request to check if the current user is authenticated");
        return request.getRemoteUser();
    }

    /**
     * {@code GET  /account} : get the current user.
     *
     * @return the current user.
     * @throws RuntimeException {@code 500 (Internal Server Error)} if the user couldn't be returned.
     */
    @GetMapping("/account")
    public AdminUserDTO getAccount() {
        return userService
            .getUserWithAuthorities()
            .map(AdminUserDTO::new)
            .orElseThrow(() -> new AccountResourceException("User could not be found"));
    }

    /**
     * {@code POST  /account} : update the current user information.
     *
     * @param userDTO the current user information.
     * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already used.
     * @throws RuntimeException          {@code 500 (Internal Server Error)} if the user login wasn't found.
     */
    @PostMapping("/account")
    public void saveAccount(@Valid @RequestBody AdminUserDTO userDTO) {
        String userLogin = SecurityUtils
            .getCurrentUserLogin()
            .orElseThrow(() -> new AccountResourceException("Current user login not found"));
        Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(userDTO.getEmail());
        if (existingUser.isPresent() && (!existingUser.get().getLogin().equalsIgnoreCase(userLogin))) {
            throw new EmailAlreadyUsedException();
        }
        Optional<User> user = userRepository.findOneByLogin(userLogin);
        if (!user.isPresent()) {
            throw new AccountResourceException("User could not be found");
        }
        userService.updateUser(
            userDTO.getFirstName(),
            userDTO.getLastName(),
            userDTO.getEmail()
        );
    }

    /**
     * {@code POST  /account/change-password} : changes the current user's password.
     *
     * @param passwordChangeDto current and new password.
     * @throws InvalidPasswordException {@code 400 (Bad Request)} if the new password is incorrect.
     */
    @PostMapping(path = "/account/change-password")
    public void changePassword(@RequestBody PasswordChangeDTO passwordChangeDto) {
        if (isPasswordLengthInvalid(passwordChangeDto.getNewPassword())) {
            throw new InvalidPasswordException();
        }
        userService.changePassword(passwordChangeDto.getCurrentPassword(), passwordChangeDto.getNewPassword());
    }

    private static class AccountResourceException extends RuntimeException {

        private AccountResourceException(String message) {
            super(message);
        }
    }
}
