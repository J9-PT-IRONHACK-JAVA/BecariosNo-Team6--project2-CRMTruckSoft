package com.ironhack.team6crm.repository;

import com.ironhack.team6crm.model.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity,Long> {
}
