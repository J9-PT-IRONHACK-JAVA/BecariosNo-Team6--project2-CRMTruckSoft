package com.ironhack.team6crm.repository;

import com.ironhack.team6crm.Team6CrmApplication;
import com.ironhack.team6crm.model.*;
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
class OpportunityRepositoryTest {
    @MockBean
    private Team6CrmApplication Team6CRMApplication;

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private SalesRepService salesRepService;

    @Autowired
    private OpportunityRepository opportunityRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SalesRepRepository salesRepRepository;

    @BeforeEach
    void setUp() {

        var Sarah = new SalesRep("Sarah");
        var Peter = new SalesRep("Peter");
        Sarah = salesRepService.save(Sarah);
        Peter = salesRepService.save(Peter);

        var listOfContacts = List.of(
                new Contact("Mike LeadOne", "+34 111111111", "mail111@gmail.com", Sarah),
                new Contact("Pedro LeadTwo", "+34 222222222", "mail222@gmail.com", Peter),
                new Contact("William LeadThree", "+34 333333333", "mail333@gmail.com", Sarah),
                new Contact("Erika LeadFour", "+34 444444444", "mail444@gmail.com", Sarah),
                new Contact("Olaf LeadFive", "+34 555555555", "mail555@gmail.com", Peter)
        );
        var savedContacts = contactRepository.saveAll(listOfContacts);


        var listOfOpportunities = List.of(
                new Opportunity(Product.BOX, 5, savedContacts.get(0), Sarah, Status.OPEN),
                new Opportunity(Product.FLATBED, 10, savedContacts.get(1), Sarah, Status.OPEN),
                new Opportunity(Product.BOX, 20, savedContacts.get(2), Sarah, Status.CLOSED_LOST),
                new Opportunity(Product.HYBRID, 8, savedContacts.get(3), Sarah, Status.CLOSED_WON)
        );
        var savedOpportunities = opportunityRepository.saveAll(listOfOpportunities);
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
    void countBySalesRep() {
        var result = opportunityRepository.countBySalesRep();
        assertEquals(1, result.size());
    }

    @Test
    void countByProduct() {
       var result = opportunityRepository.countByProduct();
        assertEquals(3, result.size());
    }

    @Test
    void countCloseWonBySalesRep() {
        var result = opportunityRepository.countCloseWonBySalesRep();
        assertEquals(0, result.size());
    }



    @Test
    void countCloseLostBySalesRep() {
        var result = opportunityRepository.countCloseLostBySalesRep();
        assertEquals(0, result.size());
    }
    @Test
    void countOpenBySalesRep() {
        var result = opportunityRepository.countOpenBySalesRep();
        assertEquals(1, result.size());
    }

    @Test
    void averageProductsPerOrder() {
     var result = opportunityRepository.averageProductsPerOrder();
        assertEquals(11, Integer.parseInt(result));
    }

    @Test
    void listQuantities() {
    var result = opportunityRepository.listQuantities();
        assertEquals(4, result.size());
    }



    @Test
    void maxProductsPerOrder() {
    var result = opportunityRepository.maxProductsPerOrder();
        assertEquals(20, Integer.parseInt(result));
    }

    @Test
    void minProductsPerOrder() {
        var result = opportunityRepository.minProductsPerOrder();
        assertEquals(5, Integer.parseInt(result));

    }

}