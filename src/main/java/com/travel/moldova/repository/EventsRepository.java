package com.travel.moldova.repository;

import com.travel.moldova.domain.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the Events entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EventsRepository extends JpaRepository<Events, Long>, JpaSpecificationExecutor<Events> {

    @Query(value = "SELECT * FROM events ORDER BY RANDOM() LIMIT 10", nativeQuery = true)
    List<Events> findRandom10();
}
