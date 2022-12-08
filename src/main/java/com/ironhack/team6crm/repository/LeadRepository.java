package com.ironhack.team6crm.repository;

import com.ironhack.team6crm.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeadRepository extends JpaRepository<Lead,Long> {

    @Query(value = "SELECT sr.name, COUNT(t.id) FROM account_lead t INNER JOIN sales_rep sr ON t.sales_rep_id=sr.id GROUP BY sr.name", nativeQuery = true)
    List<Object[]> countBySalesRep();


}
