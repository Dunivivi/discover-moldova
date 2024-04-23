package com.travel.moldova.web.rest;

import com.travel.moldova.domain.Assets;
import com.travel.moldova.repository.AssetsRepository;
import com.travel.moldova.service.AssetsQueryService;
import com.travel.moldova.service.AssetsService;
import com.travel.moldova.service.criteria.AssetsCriteria;
import com.travel.moldova.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing {@link Assets}.
 */
@RestController
@RequestMapping("/api")
public class AssetsResource {

    private static final String ENTITY_NAME = "assets";
    private final Logger log = LoggerFactory.getLogger(AssetsResource.class);
    private final AssetsService assetsService;
    private final AssetsRepository assetsRepository;
    private final AssetsQueryService assetsQueryService;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public AssetsResource(AssetsService assetsService, AssetsRepository assetsRepository, AssetsQueryService assetsQueryService) {
        this.assetsService = assetsService;
        this.assetsRepository = assetsRepository;
        this.assetsQueryService = assetsQueryService;
    }

    /**
     * {@code POST  /assets} : Create a new assets.
     *
     * @param assets the assets to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new assets, or with status {@code 400 (Bad Request)} if the assets has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/assets")
    public ResponseEntity<Assets> createAssets(@RequestBody Assets assets) throws URISyntaxException {
        log.debug("REST request to save Assets : {}", assets);
        if (assets.getId() != null) {
            throw new BadRequestAlertException("A new assets cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Assets result = assetsService.save(assets);
        return ResponseEntity
            .created(new URI("/api/assets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /assets/:id} : Updates an existing assets.
     *
     * @param id     the id of the assets to save.
     * @param assets the assets to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated assets,
     * or with status {@code 400 (Bad Request)} if the assets is not valid,
     * or with status {@code 500 (Internal Server Error)} if the assets couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/assets/{id}")
    public ResponseEntity<Assets> updateAssets(@PathVariable(value = "id", required = false) final Long id, @RequestBody Assets assets)
        throws URISyntaxException {
        log.debug("REST request to update Assets : {}, {}", id, assets);
        if (assets.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, assets.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!assetsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Assets result = assetsService.update(assets);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, assets.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /assets} : get all the assets.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of assets in body.
     */
    @GetMapping("/assets")
    public ResponseEntity<List<Assets>> getAllAssets(
        AssetsCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Assets by criteria: {}", criteria);
        Page<Assets> page = assetsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /assets/count} : count all the assets.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/assets/count")
    public ResponseEntity<Long> countAssets(AssetsCriteria criteria) {
        log.debug("REST request to count Assets by criteria: {}", criteria);
        return ResponseEntity.ok().body(assetsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /assets/:id} : get the "id" assets.
     *
     * @param id the id of the assets to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the assets, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/assets/{id}")
    public ResponseEntity<Assets> getAssets(@PathVariable Long id) {
        log.debug("REST request to get Assets : {}", id);
        Optional<Assets> assets = assetsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(assets);
    }

    /**
     * {@code DELETE  /assets/:id} : delete the "id" assets.
     *
     * @param id the id of the assets to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/assets/{id}")
    public ResponseEntity<Void> deleteAssets(@PathVariable Long id) {
        log.debug("REST request to delete Assets : {}", id);
        assetsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
