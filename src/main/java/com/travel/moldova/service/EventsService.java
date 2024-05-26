package com.travel.moldova.service;

import com.travel.moldova.domain.Assets;
import com.travel.moldova.domain.Events;
import com.travel.moldova.domain.User;
import com.travel.moldova.domain.enumeration.Type;
import com.travel.moldova.repository.AssetsRepository;
import com.travel.moldova.repository.EventsRepository;
import com.travel.moldova.repository.UserRepository;
import com.travel.moldova.security.SecurityUtils;
import com.travel.moldova.service.dto.CreateEventDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.nonNull;

/**
 * Service Implementation for managing {@link Events}.
 */
@Service
@Transactional
public class EventsService {

    private final Logger log = LoggerFactory.getLogger(EventsService.class);

    private final EventsRepository eventsRepository;

    private final UserRepository userRepository;

    private final AssetsRepository assetsRepository;


    public EventsService(EventsRepository eventsRepository, UserRepository userRepository, AssetsRepository assetsRepository) {
        this.eventsRepository = eventsRepository;
        this.userRepository = userRepository;
        this.assetsRepository = assetsRepository;
    }

    /**
     * Save a events.
     *
     * @param events the entity to save.
     * @return the persisted entity.
     */
    public Events save(Events events) {
        log.debug("Request to save Events : {}", events);
        return eventsRepository.save(events);
    }

    /**
     * Update a events.
     *
     * @param events the entity to save.
     * @return the persisted entity.
     */
    public Events update(Events events) {
        log.debug("Request to update Events : {}", events);
        return eventsRepository.save(events);
    }

    /**
     * Get all the events.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Events> findAll(Pageable pageable) {
        log.debug("Request to get all Events");
        return eventsRepository.findAll(pageable);
    }

    /**
     * Get one events by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Events> findOne(Long id) {
        log.debug("Request to get Events : {}", id);
        return eventsRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Page<Events> findAllByUser(Pageable pageable, Type type) {
        String login = SecurityUtils.getCurrentUserLogin().orElseThrow();
        User user = userRepository.findOneByLogin(login).orElseThrow();

        Page<Events> events;

        if (type.equals(Type.Toate)) {
            events = eventsRepository.findAllByUsersIn(Set.of(user), pageable);
        } else {
            events = eventsRepository.findAllByUsersInAndType(Set.of(user), type, pageable);
        }

        events.forEach(event -> {
            event.setFavorite(user);
        });
        return events;
    }

    @Transactional()
    public void setFavorite(Long id) {
        Events event = eventsRepository.findById(id).orElseThrow();

        String login = SecurityUtils.getCurrentUserLogin().orElseThrow();
        User user = userRepository.findOneByLogin(login).orElseThrow();

        event.getUsers().add(user);
        eventsRepository.save(event);
    }

    @Transactional()
    public void removeFavorite(Long id) {
        Events event = eventsRepository.findById(id).orElseThrow();

        String login = SecurityUtils.getCurrentUserLogin().orElseThrow();
        User user = userRepository.findOneByLogin(login).orElseThrow();

        event.getUsers().remove(user);
        eventsRepository.save(event);
    }

    @Transactional()
    public void createEvent(CreateEventDTO eventDTO) {
        String login = SecurityUtils.getCurrentUserLogin().orElseThrow();
        User user = userRepository.findOneByLogin(login).orElseThrow();

        Events event = new Events();
        event.setTitle(eventDTO.getTitle());
        event.setDescription(eventDTO.getDescription());
        event.setRating(nonNull(eventDTO.getRating()) ? eventDTO.getRating() : 0);
        event.setNoOfTours(nonNull(eventDTO.getNoOfTours()) ? eventDTO.getNoOfTours() : 0);
        event.setPreViewImg(nonNull(eventDTO.getPreViewImg()) ? eventDTO.getPreViewImg() : null);
        event.setType(nonNull(eventDTO.getType()) ? eventDTO.getType() : null);
        event.setSubType(nonNull(eventDTO.getSubType()) ? eventDTO.getSubType() : null);
        event.setEventDate(nonNull(eventDTO.getEventDate()) ? eventDTO.getEventDate() : null);
        event.setLat(nonNull(eventDTO.getLat()) ? eventDTO.getLat() : null);
        event.setLongitudine(nonNull(eventDTO.getLongitudine()) ? eventDTO.getLongitudine() : null);
        event.setUrl(nonNull(eventDTO.getUrl()) ? eventDTO.getUrl() : null);
        event.setLocation(nonNull(eventDTO.getLocation()) ? eventDTO.getLocation() : null);
        event.setCompanyId(nonNull(user.getCompany().getId()) ? user.getCompany().getId() : null);
        event.setCreatedBy(user.getFirstName() + " " + user.getLastName());
        event.setCreatedDate(Instant.now());

        Events events = eventsRepository.save(event);

        if (!eventDTO.getAssets().isEmpty()) {
            for (String asset : eventDTO.getAssets()) {
                Assets assets = new Assets();
                assets.setUrl(asset);
                assets.setEvents(events);

                assetsRepository.save(assets);
            }
        }
    }


    /**
     * Delete the events by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Events : {}", id);
        Optional<Events> events = eventsRepository.findById(id);
        events.ifPresent(value -> value.getUsers().clear());
        eventsRepository.deleteById(id);
    }
}
