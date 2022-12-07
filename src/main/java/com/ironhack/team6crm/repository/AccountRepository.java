package com.ironhack.team6crm.repository;

import com.ironhack.team6crm.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    @Query(value = "SELECT FORMAT(AVG(t.employee_count),0,'es_ES') FROM account t", nativeQuery = true)
    String averageEmployeeCount();
    @Query(value = "SELECT t.employee_count FROM account t", nativeQuery = true)
    List<Integer> listEmployeeCount();
    @Query(value = "SELECT MAX(t.employee_count) FROM account t", nativeQuery = true)
    String maxEmployeeCount();
    @Query(value = "SELECT MIN(t.employee_count) FROM account t", nativeQuery = true)
    String minEmployeeCount();
}
