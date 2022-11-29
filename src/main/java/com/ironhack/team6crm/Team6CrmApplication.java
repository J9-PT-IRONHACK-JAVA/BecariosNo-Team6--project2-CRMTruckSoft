package com.ironhack.team6crm;

import com.ironhack.team6crm.service.Menu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Team6CrmApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Team6CrmApplication.class, args);
    }

    @Override
    public void run(String... args)  {

        //Menu.run();
    }
}
