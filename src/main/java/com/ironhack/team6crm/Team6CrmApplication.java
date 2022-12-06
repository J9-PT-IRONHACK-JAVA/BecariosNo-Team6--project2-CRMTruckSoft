package com.ironhack.team6crm;

import com.ironhack.team6crm.model.*;
import com.ironhack.team6crm.repository.AccountRepository;
import com.ironhack.team6crm.repository.ContactRepository;
import com.ironhack.team6crm.repository.LeadRepository;
import com.ironhack.team6crm.repository.OpportunityRepository;
import com.ironhack.team6crm.service.Menu;
import com.ironhack.team6crm.service.SalesRepService;
import com.ironhack.team6crm.utils.DataLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class Team6CrmApplication implements CommandLineRunner {

    private final LeadRepository leadRepository;
    private final OpportunityRepository opportunityRepository;
    private final AccountRepository accountRepository;
    private final ContactRepository contactRepository;
    private final SalesRepService salesRepService;

    private final Menu mainMenu;
    public static void main(String[] args) {
        SpringApplication.run(Team6CrmApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        var loadedAccounts = accountRepository.findAll();
        var loadedContacts = contactRepository.findAll();
        var loadedOpportunities = opportunityRepository.findAll();

        for (int i = 0; i < loadedContacts.size(); i++) {
            loadedContacts.get(i).setAccount(loadedAccounts.get(i));
        }
        contactRepository.saveAll(loadedContacts);

        for (int i = 0; i < loadedOpportunities.size(); i++) {
            loadedOpportunities.get(i).setAccount(loadedAccounts.get(i));
        }
        opportunityRepository.saveAll(loadedOpportunities);

        mainMenu.run();
    }
}
