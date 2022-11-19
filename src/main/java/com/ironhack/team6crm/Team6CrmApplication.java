package com.ironhack.team6crm;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// ELIMINAR ->
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


// ELMINAR ESTO!!!!!! ->
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })

// Original ->
//@SpringBootApplication
public class Team6CrmApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Team6CrmApplication.class, args);
    }

    @Override
    public void run(String... args)  {
        System.out.println("Hello World!");

    }
}
