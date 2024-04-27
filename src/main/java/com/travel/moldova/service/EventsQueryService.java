package com.travel.moldova.service;

import com.travel.moldova.domain.Events;
import com.travel.moldova.domain.Events_;
import com.travel.moldova.domain.User;
import com.travel.moldova.domain.enumeration.Type;
import com.travel.moldova.repository.EventsRepository;
import com.travel.moldova.repository.UserRepository;
import com.travel.moldova.security.SecurityUtils;
import com.travel.moldova.service.criteria.EventsCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.travel.moldova.domain.User_.login;

/**
 * Service for executing complex queries for {@link Events} entities in the database.
 * The main input is a {@link EventsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Events} or a {@link Page} of {@link Events} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EventsQueryService extends QueryService<Events> {

    private final Logger log = LoggerFactory.getLogger(EventsQueryService.class);

    private final EventsRepository eventsRepository;
    private final UserRepository userRepository;

    public EventsQueryService(EventsRepository eventsRepository, UserRepository userRepository) {
        this.eventsRepository = eventsRepository;
        this.userRepository = userRepository;
    }

    /**
     * Return a {@link List} of {@link Events} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Events> findByCriteria(EventsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Events> specification = createSpecification(criteria);
        return eventsRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Events} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Events> findByCriteria(EventsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Events> specification = createSpecification(criteria);

//        String login = SecurityUtils.getCurrentUserLogin().orElseThrow();
        String login = "admin";
        User user = userRepository.findOneByLogin(login).orElseThrow();
        Page<Events> events = eventsRepository.findAll(specification, page);
        events.forEach(event -> {
            event.setFavorite(user);
        });
        return events;
    }

    @Transactional(readOnly = true)
    public List<Events> findRecommended() {
        String login = SecurityUtils.getCurrentUserLogin().orElseThrow();
//        String login = "admin";
        User user = userRepository.findOneByLogin(login).orElseThrow();
        List<Events> events = eventsRepository.findRandom10();
        events.forEach(event -> {
            event.setFavorite(user);
        });
        return events;
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EventsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Events> specification = createSpecification(criteria);
        return eventsRepository.count(specification);
    }

    /**
     * Function to convert {@link EventsCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Events> createSpecification(EventsCriteria criteria) {
        Specification<Events> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
//            if (criteria.getId() != null) {
//                specification = specification.and(buildRangeSpecification(criteria.getId(), Events_.id));
//            }
//            if (criteria.getTitle() != null) {
//                specification = specification.and(buildStringSpecification(criteria.getTitle(), Events_.title));
//            }
//            if (criteria.getNoOfTours() != null) {
//                specification = specification.and(buildRangeSpecification(criteria.getNoOfTours(), Events_.noOfTours));
//            }
//            if (criteria.getRating() != null) {
//                specification = specification.and(buildRangeSpecification(criteria.getRating(), Events_.rating));
//            }
//            if (criteria.getPreViewImg() != null) {
//                specification = specification.and(buildStringSpecification(criteria.getPreViewImg(), Events_.preViewImg));
//            }
//            if (criteria.getDescription() != null) {
//                specification = specification.and(buildStringSpecification(criteria.getDescription(), Events_.description));
//            }
            if (criteria.getType() != null && !Objects.equals(criteria.getType(), new EventsCriteria.TypeFilter().setEquals(Type.Toate))) {
                specification = specification.and(buildSpecification(criteria.getType(), Events_.type));
            }
//            if (criteria.getSubType() != null) {
//                specification = specification.and(buildStringSpecification(criteria.getSubType(), Events_.subType));
//            }
//            if (criteria.getPrice() != null) {
//                specification = specification.and(buildRangeSpecification(criteria.getPrice(), Events_.price));
//            }
//            if (criteria.getEventDate() != null) {
//                specification = specification.and(buildRangeSpecification(criteria.getEventDate(), Events_.eventDate));
//            }
//            if (criteria.getCreatedBy() != null) {
//                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), Events_.createdBy));
//            }
//            if (criteria.getCreatedDate() != null) {
//                specification = specification.and(buildRangeSpecification(criteria.getCreatedDate(), Events_.createdDate));
//            }
//            if (criteria.getLastModifiedBy() != null) {
//                specification = specification.and(buildStringSpecification(criteria.getLastModifiedBy(), Events_.lastModifiedBy));
//            }
//            if (criteria.getLastModifiedDate() != null) {
//                specification = specification.and(buildRangeSpecification(criteria.getLastModifiedDate(), Events_.lastModifiedDate));
//            }
//            if (criteria.getCompanyId() != null) {
//                specification = specification.and(buildRangeSpecification(criteria.getCompanyId(), Events_.companyId));
//            }
        }
        return specification;
    }
}
