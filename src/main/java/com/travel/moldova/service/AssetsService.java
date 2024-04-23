package com.travel.moldova.service;

import com.travel.moldova.domain.Assets;
import com.travel.moldova.repository.AssetsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Assets}.
 */
@Service
@Transactional
public class AssetsService {

    private final Logger log = LoggerFactory.getLogger(AssetsService.class);

    private final AssetsRepository assetsRepository;

    public AssetsService(AssetsRepository assetsRepository) {
        this.assetsRepository = assetsRepository;
    }

    /**
     * Save a assets.
     *
     * @param assets the entity to save.
     * @return the persisted entity.
     */
    public Assets save(Assets assets) {
        log.debug("Request to save Assets : {}", assets);
        return assetsRepository.save(assets);
    }

    /**
     * Update a assets.
     *
     * @param assets the entity to save.
     * @return the persisted entity.
     */
    public Assets update(Assets assets) {
        log.debug("Request to update Assets : {}", assets);
        return assetsRepository.save(assets);
    }

    /**
     * Partially update a assets.
     *
     * @param assets the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Assets> partialUpdate(Assets assets) {
        log.debug("Request to partially update Assets : {}", assets);

        return assetsRepository
            .findById(assets.getId())
            .map(existingAssets -> {
                if (assets.getUrl() != null) {
                    existingAssets.setUrl(assets.getUrl());
                }

                return existingAssets;
            })
            .map(assetsRepository::save);
    }

    /**
     * Get all the assets.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Assets> findAll(Pageable pageable) {
        log.debug("Request to get all Assets");
        return assetsRepository.findAll(pageable);
    }

    /**
     * Get one assets by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Assets> findOne(Long id) {
        log.debug("Request to get Assets : {}", id);
        return assetsRepository.findById(id);
    }

    /**
     * Delete the assets by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Assets : {}", id);
        assetsRepository.deleteById(id);
    }
}
