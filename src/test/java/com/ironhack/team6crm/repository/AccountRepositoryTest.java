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
    private LeadRepository leadRepository;
    @Autowired
    private SalesRepService salesRepService;


    @BeforeEach
    void setUp() {
        var Sarah = new SalesRep("Sarah");
        var Peter = new SalesRep("Peter");
        Sarah = salesRepService.save(Sarah);
        Peter = salesRepService.save(Peter);
        var listOfAccounts = List.of(
                new Account("GMC Truck Inc", 200, "Detroit", "USA", Industry.OTHER, Sarah),
                new Account("Ivecco Logistics SL", 100, "Barcelona", "ESP", Industry.MEDICAL, Sarah),
                new Account("Douglas Motor Ltd", 230, "London", "UK", Industry.OTHER,Sarah),
                new Account("Caterpillar GmbH", 1100, "Frankfurt AM", "Germany", Industry.MANUFACTURING, Sarah),
                new Account("Scania AB", 670, "Goteborg", "Sweden", Industry.MANUFACTURING, Sarah)
        );
        var savedAccounts = accountRepository.saveAll(listOfAccounts);
    }

    @AfterEach
    void tearDown() {
        opportunityRepository.deleteAll();
        contactRepository.deleteAll();
        accountRepository.deleteAll();
        leadRepository.deleteAll();
        salesRepRepository.deleteAll();
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