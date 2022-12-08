package com.ironhack.team6crm.repository;

import com.ironhack.team6crm.model.Account;
import com.ironhack.team6crm.model.Contact;
import com.ironhack.team6crm.model.Industry;
import com.ironhack.team6crm.model.SalesRep;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContactRepositoryTest {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private SalesRepRepository salesRepRepository;
    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {

        /*

        contactRepository.deleteAll();

        var salesRep1 = new SalesRep("John");
        salesRep1 = salesRepRepository.save(salesRep1);

        var account1 = new Account(Industry.ECOMMERCE,
                "Cordoba Soft",
                133, "Cordoba", "Argentina",salesRep1);
        account1 = accountRepository.save(account1);

        var account2 = new Account(Industry.OTHER,
                "Renfe",
                12444, "Madrid", "Spain",salesRep1);

        var contact1 = new Contact("Juan Gomez",
                "+54 3516534352",
                "emailjuangomez@softmail.com",
                salesRep1);

        var contact2 = new Contact("Manuel Diaz Martinez",
                "+34 655442987",
                "pedrodiazmartinez@renfe.com",
                salesRep1);

        contact1.setAccount(account1);
        //contact2.setAccount(account2);

        contactRepository.save(contact1);
        //contactRepository.save(contact2);

        */

    }

    @AfterEach
    void tearDown() {
        //contactRepository.deleteAll();
    }

    @Test
    void findAllByAccount_Id() {
        var findContactAccount1 = contactRepository.findAllByAccount_Id(1L);
        assertEquals(findContactAccount1.get(0).getName(),"Mike LeadOne");
    }

    @Test
    void findByNameIgnoreCase() {
        var findByName = contactRepository.findByNameIgnoreCase("Mike LeadOne");
        assertEquals(findByName.get().getName(),"Mike LeadOne");

    }

}