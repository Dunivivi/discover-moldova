package com.travel.moldova.service;

import com.travel.moldova.domain.Events;
import com.travel.moldova.repository.EventsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Events}.
 */
@Service
@Transactional
public class EventsService {

    private final Logger log = LoggerFactory.getLogger(EventsService.class);

    private final EventsRepository eventsRepository;

    public EventsService(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
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
     * Partially update a events.
     *
     * @param events the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Events> partialUpdate(Events events) {
        log.debug("Request to partially update Events : {}", events);

        return eventsRepository
            .findById(events.getId())
            .map(existingEvents -> {
                if (events.getTitle() != null) {
                    existingEvents.setTitle(events.getTitle());
                }
                if (events.getNoOfTours() != null) {
                    existingEvents.setNoOfTours(events.getNoOfTours());
                }
                if (events.getRating() != null) {
                    existingEvents.setRating(events.getRating());
                }
                if (events.getPreViewImg() != null) {
                    existingEvents.setPreViewImg(events.getPreViewImg());
                }
                if (events.getDescription() != null) {
                    existingEvents.setDescription(events.getDescription());
                }
                if (events.getType() != null) {
                    existingEvents.setType(events.getType());
                }
                if (events.getSubType() != null) {
                    existingEvents.setSubType(events.getSubType());
                }
                if (events.getPrice() != null) {
                    existingEvents.setPrice(events.getPrice());
                }
                if (events.getEventDate() != null) {
                    existingEvents.setEventDate(events.getEventDate());
                }
                if (events.getCreatedBy() != null) {
                    existingEvents.setCreatedBy(events.getCreatedBy());
                }
                if (events.getCreatedDate() != null) {
                    existingEvents.setCreatedDate(events.getCreatedDate());
                }
                if (events.getLastModifiedBy() != null) {
                    existingEvents.setLastModifiedBy(events.getLastModifiedBy());
                }
                if (events.getLastModifiedDate() != null) {
                    existingEvents.setLastModifiedDate(events.getLastModifiedDate());
                }
                if (events.getCompanyId() != null) {
                    existingEvents.setCompanyId(events.getCompanyId());
                }

                return existingEvents;
            })
            .map(eventsRepository::save);
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

    /**
     * Delete the events by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Events : {}", id);
        eventsRepository.deleteById(id);
    }
}
