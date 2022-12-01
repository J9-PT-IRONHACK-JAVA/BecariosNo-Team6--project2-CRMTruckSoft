package com.ironhack.team6crm;

import com.ironhack.team6crm.model.Lead;
import com.ironhack.team6crm.repository.LeadRepository;
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
    Menu mainMenu;
    public static void main(String[] args) {
        SpringApplication.run(Team6CrmApplication.class, args);
    }

    @Override
    public void run(String... args)  {

        var listOfLeads = List.of(
                new Lead("Number One","+34 6564532345","mail111@gmail.com","COCA COLA"),
                new Lead("Two Two Two ","+34 234432345","mail222@gmail.com","PEPSI"),
                new Lead("Three Three","+34 33333345","mail333@gmail.com","ESTRELLA"),
                new Lead("Four Four","+34 44444444","mail444@gmail.com","LOGISTICA"),
                new Lead("Five Five","+34 5555555","mail555@gmail.com","BARZA"),
                new Lead("Six Six","+34 666666","mail666@gmail.com","CEPSSA")
        );
        leadRepository.saveAll(listOfLeads);
        mainMenu.run();
    }
}
