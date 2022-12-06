package com.ironhack.team6crm.repository;

import com.ironhack.team6crm.model.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepRepository extends JpaRepository<SalesRep,Long> {
}