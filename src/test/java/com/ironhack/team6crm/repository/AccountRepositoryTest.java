package com.ironhack.team6crm.repository;
import com.ironhack.team6crm.Team6CrmApplication;
import com.ironhack.team6crm.model.Account;
import com.ironhack.team6crm.model.Industry;
import com.ironhack.team6crm.model.Lead;
import com.ironhack.team6crm.model.SalesRep;
import com.ironhack.team6crm.service.SalesRepService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class AccountRepositoryTest {

    @MockBean
    private Team6CrmApplication team6CrmApplication;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private SalesRepRepository salesRepRepository;
    @Autowired
    private OpportunityRepository opportunityRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private SalesRepService salesRepService;

    @BeforeEach
    void setUp() {
        var Sarah = new SalesRep("Sarah");
        var Peter = new SalesRep("Peter");
        Sarah = salesRepService.save(Sarah);
        Peter = salesRepService.save(Peter);
        var listOfAccounts = List.of(
                new Account(Industry.OTHER, "Man Truck SA", 200, "Detroit", "USA", Sarah),
                new Account(Industry.MEDICAL, "Ivecco Logistics SL", 100, "Barcelona", "ESP", Peter),
                new Account(Industry.OTHER, "Pegaso Motor SA", 230, "London", "UK", Sarah),
                new Account(Industry.MANUFACTURING, "Mercedes Benz Trucks SA", 1100, "Frankfurt AM", "Germany", Sarah),
                new Account(Industry.MANUFACTURING, "Volvo Trucking Company SA", 670, "Goteborg", "Sweden", Sarah)
        );
        var savedAccounts = accountRepository.saveAll(listOfAccounts);
    }

    @AfterEach
    void tearDown() {
        opportunityRepository.deleteAll();
        contactRepository.deleteAll();
        accountRepository.deleteAll();

    }

    @Test
    void listEmployeeCount() {
        var result = accountRepository.listEmployeeCount();
        assertEquals(5, result.size());
    }

    @Test
    void averageEmployeeCount() {
        var result = accountRepository.averageEmployeeCount();
        assertEquals(460, Integer.parseInt(result));
    }

    @Test
    void maxEmployeeCount() {
        var result = accountRepository.maxEmployeeCount();
        assertEquals(1100,  Integer.parseInt(result));
    }


    @Test
    void minEmployeeCount() {
        var result = accountRepository.minEmployeeCount();
        assertEquals(100, Integer.parseInt(result));
    }

}