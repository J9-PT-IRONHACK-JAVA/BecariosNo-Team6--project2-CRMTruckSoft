package com.ironhack.team6crm;

import com.ironhack.team6crm.model.Lead;
import com.ironhack.team6crm.model.SalesRep;
import com.ironhack.team6crm.repository.LeadRepository;
import com.ironhack.team6crm.repository.SalesRepRepository;
import com.ironhack.team6crm.service.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Team6CrmApplication implements CommandLineRunner {

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private SalesRepRepository salesRepRepository;
    @Autowired
    Menu mainMenu;
    public static void main(String[] args) {
        SpringApplication.run(Team6CrmApplication.class, args);
    }

    @Override
    public void run(String... args)  {
        var listOfSalesReps = List.of(
                new SalesRep("Sarah"),
                new SalesRep("Jason")
        );


        salesRepRepository.saveAll(listOfSalesReps);
        mainMenu.run();
    }
}
