package com.ironhack.team6crm.repository;

import com.ironhack.team6crm.model.Lead;
import com.ironhack.team6crm.model.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface LeadRepository extends JpaRepository<Lead,Long> {

    @Query(value = "SELECT t.sales_rep_id, COUNT(t.id) FROM account_lead AS t GROUP BY t.sales_rep_id", nativeQuery = true)
    List<Object[]> countBySalesRep();


}
