package com.travel.moldova.web.rest;

import com.travel.moldova.domain.Events;
import com.travel.moldova.domain.enumeration.Type;
import com.travel.moldova.repository.EventsRepository;
import com.travel.moldova.service.EventsQueryService;
import com.travel.moldova.service.EventsService;
import com.travel.moldova.service.criteria.EventsCriteria;
import com.travel.moldova.service.dto.CreateEventDTO;
import com.travel.moldova.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing {@link Events}.
 */
@RestController
@RequestMapping("/api")
public class EventsResource {

    private static final String ENTITY_NAME = "events";
    private final Logger log = LoggerFactory.getLogger(EventsResource.class);
    private final EventsService eventsService;
    private final EventsRepository eventsRepository;
    private final EventsQueryService eventsQueryService;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public EventsResource(EventsService eventsService, EventsRepository eventsRepository, EventsQueryService eventsQueryService) {
        this.eventsService = eventsService;
        this.eventsRepository = eventsRepository;
        this.eventsQueryService = eventsQueryService;
    }

    /**
     * {@code POST  /events} : Create a new events.
     *
     * @param eventDTO the events to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new events, or with status {@code 400 (Bad Request)} if the events has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/events")
    public ResponseEntity<HttpStatus> createEvents(@Valid @RequestBody CreateEventDTO eventDTO) throws URISyntaxException {
        log.debug("REST request to save Events : {}", eventDTO);
        eventsService.createEvent(eventDTO);
        return ResponseEntity
            .created(new URI("/api/events/" + eventDTO.getType()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, eventDTO.getTitle()))
            .body(HttpStatus.CREATED);
    }

    /**
     * {@code PUT  /events/:id} : Updates an existing events.
     *
     * @param id     the id of the events to save.
     * @param events the events to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated events,
     * or with status {@code 400 (Bad Request)} if the events is not valid,
     * or with status {@code 500 (Internal Server Error)} if the events couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/events/{id}")
    public ResponseEntity<Events> updateEvents(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Events events
    ) throws URISyntaxException {
        log.debug("REST request to update Events : {}, {}", id, events);
        if (events.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, events.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!eventsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Events result = eventsService.update(events);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, events.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /events} : get all the events.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of events in body.
     */
    @GetMapping("/events")
    public ResponseEntity<List<Events>> getAllEvents(
        EventsCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Events by criteria: {}", criteria);
        Page<Events> page = eventsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/events/favorites/{type}")
    public ResponseEntity<List<Events>> getAllFavoritesEvents(
        @PathVariable(value = "type", required = true) final Type type,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Events by type: {}", type);
        Page<Events> page = eventsService.findAllByUser(pageable, type);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/events/activities")
    public ResponseEntity<List<Events>> getActivitiesEvents(
        EventsCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get activities by pageable: {}", pageable);
        criteria.setType((EventsCriteria.TypeFilter) new EventsCriteria.TypeFilter().setEquals(Type.Evenimente));
        Page<Events> page = eventsQueryService.findByCriteria(criteria, pageable);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/events/suggested")
    public ResponseEntity<List<Events>> getSuggestedEvents(
        EventsCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get suggested Events by criteria: {}", criteria);
        List<Events> page = eventsQueryService.findRecommended();
        return ResponseEntity.ok().body(page);
    }

    /**
     * {@code GET  /events/count} : count all the events.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/events/count")
    public ResponseEntity<Long> countEvents(EventsCriteria criteria) {
        log.debug("REST request to count Events by criteria: {}", criteria);
        return ResponseEntity.ok().body(eventsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /events/:id} : get the "id" events.
     *
     * @param id the id of the events to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the events, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/events/{id}")
    public ResponseEntity<Events> getEvents(@PathVariable Long id) {
        log.debug("REST request to get Events : {}", id);
        Optional<Events> events = eventsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(events);
    }

    /**
     * {@code DELETE  /events/:id} : delete the "id" events.
     *
     * @param id the id of the events to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/events/{id}")
    public ResponseEntity<Void> deleteEvents(@PathVariable Long id) {
        log.debug("REST request to delete Events : {}", id);
        eventsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @PostMapping("/events/favorites/{id}")
    public ResponseEntity<Void> addFavoriteEvent(@PathVariable Long id) {
        log.debug("REST request to add favorite Event : {}", id);
        eventsService.setFavorite(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @DeleteMapping("/events/favorites/{id}")
    public ResponseEntity<Void> deleteFavoriteEvent(@PathVariable Long id) {
        log.debug("REST request to delete favorite Event : {}", id);
        eventsService.removeFavorite(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
