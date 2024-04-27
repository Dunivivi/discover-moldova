package com.travel.moldova.service.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class CreateUserCompanyDTO {

    @Size(max = 50)
    @NotNull
    private String firstName;

    @Size(max = 50)
    @NotNull
    private String lastName;

    @Email
    @Size(min = 5, max = 254)
    private String email;

    @Size(max = 50)
    private String password;

    @Size(max = 50)
    @NotNull
    private String companyName;

    public CreateUserCompanyDTO() {
    }

    public CreateUserCompanyDTO(String firstName, String lastName, String email, String password, String companyName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.companyName = companyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateUserCompanyDTO that = (CreateUserCompanyDTO) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(companyName, that.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, password, companyName);
    }

    @Override
    public String toString() {
        return "CreateUserCompanyDTO{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", companyName='" + companyName + '\'' +
            '}';
    }
}
