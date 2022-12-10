package com.ironhack.team6crm;


import com.ironhack.team6crm.service.menu.Menu;
import com.ironhack.team6crm.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@RequiredArgsConstructor
public class Team6CrmApplication implements CommandLineRunner {

    private final Utils utils;
    private final Menu mainMenu;

    public static void main(String[] args) {
        SpringApplication.run(Team6CrmApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        utils.simulateLinks();
        mainMenu.run();
    }
}
