package com.travel.moldova.repository;

import com.travel.moldova.domain.Assets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Assets entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssetsRepository extends JpaRepository<Assets, Long>, JpaSpecificationExecutor<Assets> {}
