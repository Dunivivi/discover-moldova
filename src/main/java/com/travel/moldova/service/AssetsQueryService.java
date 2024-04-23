package com.travel.moldova.service;

import com.travel.moldova.domain.Assets;
import com.travel.moldova.repository.AssetsRepository;
import com.travel.moldova.service.criteria.AssetsCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

import javax.persistence.criteria.JoinType;
import java.util.List;

/**
 * Service for executing complex queries for {@link Assets} entities in the database.
 * The main input is a {@link AssetsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Assets} or a {@link Page} of {@link Assets} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AssetsQueryService extends QueryService<Assets> {

    private final Logger log = LoggerFactory.getLogger(AssetsQueryService.class);

    private final AssetsRepository assetsRepository;

    public AssetsQueryService(AssetsRepository assetsRepository) {
        this.assetsRepository = assetsRepository;
    }

    /**
     * Return a {@link List} of {@link Assets} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Assets> findByCriteria(AssetsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Assets> specification = createSpecification(criteria);
        return assetsRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Assets} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Assets> findByCriteria(AssetsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Assets> specification = createSpecification(criteria);
        return assetsRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AssetsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Assets> specification = createSpecification(criteria);
        return assetsRepository.count(specification);
    }

    /**
     * Function to convert {@link AssetsCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Assets> createSpecification(AssetsCriteria criteria) {
        Specification<Assets> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
//            if (criteria.getId() != null) {
//                specification = specification.and(buildRangeSpecification(criteria.getId(), Assets_.id));
//            }
//            if (criteria.getUrl() != null) {
//                specification = specification.and(buildStringSpecification(criteria.getUrl(), Assets_.url));
//            }
//            if (criteria.getEventsId() != null) {
//                specification =
//                    specification.and(
//                        buildSpecification(criteria.getEventsId(), root -> root.join(Assets_.events, JoinType.LEFT).get(Events_.id))
//                    );
//            }
        }
        return specification;
    }
}
