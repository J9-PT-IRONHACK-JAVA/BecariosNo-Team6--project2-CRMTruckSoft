package com.ironhack.team6crm.repository;

import com.ironhack.team6crm.model.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity,Long> {

    @Query(value = "SELECT t.sales_rep_id, COUNT(t.id) FROM opportunity AS t GROUP BY t.sales_rep_id", nativeQuery = true)
    List<Object[]> countBySalesRep();

    @Query(value = "SELECT t.product, COUNT(t.id) FROM opportunity AS t GROUP BY t.product", nativeQuery = true)
    List<Object[]> countByProduct();

    @Query(value = "SELECT a.city, COUNT(t.id) FROM opportunity t inner join account a on t.account_id = a.id GROUP BY a.city", nativeQuery = true)
    List<Object[]> countByCity();

    @Query(value = "SELECT a.country, COUNT(t.id) FROM opportunity t inner join account a on t.account_id = a.id GROUP BY a.country", nativeQuery = true)
    List<Object[]> countByCountry();

    @Query(value = "SELECT a.industry, COUNT(t.id) FROM opportunity t inner join account a on t.account_id = a.id GROUP BY a.industry", nativeQuery = true)
    List<Object[]> countByIndustry();
    @Query(value = "SELECT t.sales_rep_id, COUNT(t.id) FROM opportunity AS t where status = 'close-won' GROUP BY t.sales_rep_id", nativeQuery = true)
    List<Object[]> countCloseWonBySalesRep();

    @Query(value = "SELECT t.product, COUNT(t.id) FROM opportunity AS t where status = 'close-won' GROUP BY t.product", nativeQuery = true)
    List<Object[]> countCloseWonByProduct();


    List<Opportunity> findAllByAccount_Id(Long account_id);
}
