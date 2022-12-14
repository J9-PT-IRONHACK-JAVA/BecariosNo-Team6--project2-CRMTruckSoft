package com.ironhack.team6crm.repository;

import com.ironhack.team6crm.model.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SalesRepRepository extends JpaRepository<SalesRep, Long> {
    Optional<SalesRep> findByNameIgnoreCase(String selectedId);
}