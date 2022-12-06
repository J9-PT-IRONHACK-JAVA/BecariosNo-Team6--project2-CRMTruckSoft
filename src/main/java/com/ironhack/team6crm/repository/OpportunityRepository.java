package com.ironhack.team6crm.repository;

import com.ironhack.team6crm.model.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity,Long> {

    @Query(value = "SELECT sr.name, COUNT(t.id) FROM opportunity t inner join sales_rep sr on t.sales_rep_id = sr.id GROUP BY sr.name", nativeQuery = true)
    List<Object[]> countBySalesRep();

    @Query(value = "SELECT t.product, COUNT(t.id) FROM opportunity AS t GROUP BY t.product", nativeQuery = true)
    List<Object[]> countByProduct();

    @Query(value = "SELECT a.city, COUNT(t.id) FROM opportunity t inner join account a on t.account_id = a.id GROUP BY a.city", nativeQuery = true)
    List<Object[]> countByCity();

    @Query(value = "SELECT a.country, COUNT(t.id) FROM opportunity t inner join account a on t.account_id = a.id GROUP BY a.country", nativeQuery = true)
    List<Object[]> countByCountry();

    @Query(value = "SELECT a.industry, COUNT(t.id) FROM opportunity t inner join account a on t.account_id = a.id GROUP BY a.industry", nativeQuery = true)
    List<Object[]> countByIndustry();

    //Close-Won
    @Query(value = "SELECT sr.name, COUNT(t.id) FROM opportunity t inner join sales_rep sr on t.sales_rep_id = sr.id where t.status = 'close_won' GROUP BY sr.name", nativeQuery = true)
    List<Object[]> countCloseWonBySalesRep();

    @Query(value = "SELECT t.product, COUNT(t.id) FROM opportunity AS t where status = 'close_won' GROUP BY t.product", nativeQuery = true)
    List<Object[]> countCloseWonByProduct();

    @Query(value = "SELECT a.city, COUNT(t.id) FROM opportunity t inner join account a on t.account_id = a.id where t.status = 'close_won' GROUP BY a.city", nativeQuery = true)
    List<Object[]> countCloseWonByCity();

    @Query(value = "SELECT a.country, COUNT(t.id) FROM opportunity t inner join account a on t.account_id = a.id where t.status = 'close_won' GROUP BY a.country", nativeQuery = true)
    List<Object[]> countCloseWonByCountry();

    @Query(value = "SELECT a.industry, COUNT(t.id) FROM opportunity t inner join account a on t.account_id = a.id where t.status = 'close_won' GROUP BY a.industry", nativeQuery = true)
    List<Object[]> countCloseWonByIndustry();

    //Close-Lost
    @Query(value = "SELECT sr.name, COUNT(t.id) FROM opportunity t inner join sales_rep sr on t.sales_rep_id = sr.id where t.status = 'close_lost' GROUP BY sr.name", nativeQuery = true)
    List<Object[]> countCloseLostBySalesRep();

    @Query(value = "SELECT t.product, COUNT(t.id) FROM opportunity AS t where status = 'close_lost' GROUP BY t.product", nativeQuery = true)
    List<Object[]> countCloseLostByProduct();

    @Query(value = "SELECT a.city, COUNT(t.id) FROM opportunity t inner join account a on t.account_id = a.id where t.status = 'close_lost' GROUP BY a.city", nativeQuery = true)
    List<Object[]> countCloseLostByCity();

    @Query(value = "SELECT a.country, COUNT(t.id) FROM opportunity t inner join account a on t.account_id = a.id where t.status = 'close_lost' GROUP BY a.country", nativeQuery = true)
    List<Object[]> countCloseLostByCountry();

    @Query(value = "SELECT a.industry, COUNT(t.id) FROM opportunity t inner join account a on t.account_id = a.id where t.status = 'close_lost' GROUP BY a.industry", nativeQuery = true)
    List<Object[]> countCloseLostByIndustry();

    //Open
    @Query(value = "SELECT sr.name, COUNT(t.id) FROM opportunity t inner join sales_rep sr on t.sales_rep_id = sr.id where t.status = 'open' GROUP BY sr.name", nativeQuery = true)
    List<Object[]> countOpenBySalesRep();

    @Query(value = "SELECT t.product, COUNT(t.id) FROM opportunity AS t where status = 'open' GROUP BY t.product", nativeQuery = true)
    List<Object[]> countOpenByProduct();

    @Query(value = "SELECT a.city, COUNT(t.id) FROM opportunity t inner join account a on t.account_id = a.id where t.status = 'open' GROUP BY a.city", nativeQuery = true)
    List<Object[]> countOpenByCity();

    @Query(value = "SELECT a.country, COUNT(t.id) FROM opportunity t inner join account a on t.account_id = a.id where t.status = 'open' GROUP BY a.country", nativeQuery = true)
    List<Object[]> countOpenByCountry();

    @Query(value = "SELECT a.industry, COUNT(t.id) FROM opportunity t inner join account a on t.account_id = a.id where t.status = 'open' GROUP BY a.industry", nativeQuery = true)
    List<Object[]> countOpenByIndustry();

    //Stats
    @Query(value = "SELECT FORMAT(AVG(t.quantity),0,'es_ES') FROM opportunity t", nativeQuery = true)
    String averageProductsPerOrder();
    @Query(value = "SELECT FORMAT(AVG(sq.c),0,'es_ES') FROM (SELECT t.account_id, COUNT(t.id) c FROM opportunity t GROUP BY t.account_id) sq", nativeQuery = true)
    String averageOppsByAccount();

    @Query(value = "SELECT t.quantity FROM opportunity t", nativeQuery = true)
    List<Integer> listQuantities();
    @Query(value = "SELECT sq.c FROM (SELECT t.account_id, COUNT(t.id) c FROM opportunity t GROUP BY t.account_id) sq", nativeQuery = true)
    List<Integer> listOppsPerAccount();

    @Query(value = "SELECT MAX(t.quantity) FROM opportunity t", nativeQuery = true)
    String maxProductsPerOrder();
    @Query(value = "SELECT MAX(sq.c) FROM (SELECT t.account_id, COUNT(t.id) c FROM opportunity t GROUP BY t.account_id) sq", nativeQuery = true)
    String maxOppsByAccount();

    @Query(value = "SELECT MIN(t.quantity) FROM opportunity t", nativeQuery = true)
    String minProductsPerOrder();
    @Query(value = "SELECT MIN(sq.c) FROM (SELECT t.account_id, COUNT(t.id) c FROM opportunity t GROUP BY t.account_id) sq", nativeQuery = true)
    String minOppsByAccount();

}
