
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
class LeadRepositoryTest {

    @MockBean
    private Team6CrmApplication team6CrmApplication;
    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private SalesRepService salesRepService;

    @BeforeEach
    void setUp() {
        var Sarah = new SalesRep("Sarah");
        Sarah = salesRepService.save(Sarah);

        var listOfLeads = List.of(
                new Lead("Mike LeadOne", "+34 111111111", "mail111@gmail.com", "Man Truck SA", Sarah),
                new Lead("Pedro LeadTwo", "+34 222222222", "mail222@gmail.com", "Ivecco Logistics SL", Sarah)
        );
        leadRepository.saveAll(listOfLeads);
    }

    @AfterEach
    void tearDown() {
        leadRepository.deleteAll();

    }

    @Test
    void countBySalesRep() {
        var result = leadRepository.countBySalesRep();
        assertEquals(3, result.size());
    }


}