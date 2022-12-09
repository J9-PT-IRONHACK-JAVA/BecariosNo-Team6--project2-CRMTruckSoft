package com.ironhack.team6crm.repository;

import com.ironhack.team6crm.Team6CrmApplication;
import com.ironhack.team6crm.utils.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContactRepositoryTest {
    @MockBean
    private Team6CrmApplication team6CrmApplication;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private Utils utils;
    @BeforeEach
    void setUp() {
        utils.simulateLinks();
    }

    @AfterEach
    void tearDown() {

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