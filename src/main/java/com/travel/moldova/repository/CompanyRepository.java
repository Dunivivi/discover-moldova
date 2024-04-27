package com.travel.moldova.repository;

import com.travel.moldova.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findOneByName(String name);
}
