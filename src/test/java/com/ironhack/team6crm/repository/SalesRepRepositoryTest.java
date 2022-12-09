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
class SalesRepRepositoryTest {

    @MockBean
    private Team6CrmApplication team6CrmApplication;
    @Autowired
    private SalesRepRepository salesRepRepository;
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
    void findByNameIgnoreCase() {
        var saleRepFinded = salesRepRepository.findByNameIgnoreCase("saRAH");
        assertEquals(saleRepFinded.get().getName(),"Sarah");
    }
}