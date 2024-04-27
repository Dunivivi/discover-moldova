package com.travel.moldova.repository;

import com.travel.moldova.domain.Events;
import com.travel.moldova.domain.User;
import com.travel.moldova.domain.enumeration.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Spring Data JPA repository for the Events entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EventsRepository extends JpaRepository<Events, Long>, JpaSpecificationExecutor<Events> {

    @Query(value = "SELECT * FROM events ORDER BY RANDOM() LIMIT 10", nativeQuery = true)
    List<Events> findRandom10();

    @EntityGraph(attributePaths = {
        "assets"
    })
    @Override
    Optional<Events> findById(Long aLong);

    Page<Events> findAllByUsersInAndType(Set<User> users, Type type, Pageable pageable);

    Page<Events> findAllByUsersIn(Set<User> users, Pageable pageable);
}
