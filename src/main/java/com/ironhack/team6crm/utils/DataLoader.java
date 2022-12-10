package com.ironhack.team6crm.utils;

import com.ironhack.team6crm.model.*;
import com.ironhack.team6crm.repository.AccountRepository;
import com.ironhack.team6crm.repository.ContactRepository;
import com.ironhack.team6crm.repository.LeadRepository;
import com.ironhack.team6crm.repository.OpportunityRepository;
import com.ironhack.team6crm.service.SalesRepService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataLoader {

    private final LeadRepository leadRepository;
    private final OpportunityRepository opportunityRepository;
    private final AccountRepository accountRepository;
    private final ContactRepository contactRepository;
    private final SalesRepService salesRepService;

    @Bean
    void loadData() {
        var listOfSalesReps = List.of(
                new SalesRep("Sarah"),
                new SalesRep("Peter"),
                new SalesRep("Britney")
        );
        var savedSalesRepList = salesRepService.saveAll(listOfSalesReps);

        var listOfLeads = List.of(
                new Lead("Mike Smith", "+34 111111111", "mail111@gmail.com", "Man Truck SA", savedSalesRepList.get(0)),
                new Lead("Wang Chen", "+34 222222222", "mail222@gmail.com", "Nissan Heavy Duty Corp", savedSalesRepList.get(0)),
                new Lead("Jose Gonzalez", "+34 333333333", "mail333@gmail.com", "Pegaso Motor SA", savedSalesRepList.get(2)),
                new Lead("Erika Weigang", "+34 444444444", "mail444@gmail.com", "Mercedes-Benz Trucks GmbH", savedSalesRepList.get(1)),
                new Lead("Olaf Smorgasbord", "+34 555555555", "mail555@gmail.com", "Volvo Trucking Company AB", savedSalesRepList.get(1))
        );
        leadRepository.saveAll(listOfLeads);
        //Sarah.setLead(listOfLeads);

        var listOfAccounts = List.of(
                new Account("GMC Truck Inc", 200, "Detroit", "USA", Industry.OTHER, savedSalesRepList.get(0)),
                new Account("Ivecco Logistics SL", 100, "Barcelona", "ESP", Industry.MEDICAL, savedSalesRepList.get(1)),
                new Account("Douglas Motor Ltd", 230, "London", "UK", Industry.OTHER, savedSalesRepList.get(2)),
                new Account("Caterpillar GmbH", 1100, "Frankfurt AM", "Germany", Industry.MANUFACTURING, savedSalesRepList.get(1)),
                new Account("Scania AB", 670, "Goteborg", "Sweden", Industry.MANUFACTURING, savedSalesRepList.get(0))
        );
        var savedAccounts = accountRepository.saveAll(listOfAccounts);

        var listOfContacts = List.of(
                new Contact("Robert Anderson", "+34 111111111", "robert@gmail.com", savedSalesRepList.get(0)),
                new Contact("Antonio Machado", "+34 222222222", "antonio@gmail.com", savedSalesRepList.get(1)),
                new Contact("Peter O'Toole", "+34 333333333", "peter@gmail.com", savedSalesRepList.get(0)),
                new Contact("Franka Potente", "+34 444444444", "franka@gmail.com", savedSalesRepList.get(2)),
                new Contact("Martin Svenson", "+34 555555555", "martin@gmail.com", savedSalesRepList.get(2))
        );
        var savedContacts = contactRepository.saveAll(listOfContacts);

        var listOfOpportunities = List.of(
                new Opportunity(Product.BOX, 5, listOfContacts.get(0), savedSalesRepList.get(0), Status.OPEN),
                new Opportunity(Product.FLATBED, 10, listOfContacts.get(1), savedSalesRepList.get(0), Status.OPEN),
                new Opportunity(Product.BOX, 20, listOfContacts.get(2), savedSalesRepList.get(1), Status.CLOSED_LOST),
                new Opportunity(Product.FLATBED, 4, listOfContacts.get(3), savedSalesRepList.get(1), Status.CLOSED_LOST),
                new Opportunity(Product.HYBRID, 8, listOfContacts.get(4), savedSalesRepList.get(2), Status.CLOSED_WON)
        );
        var savedOpportunities = opportunityRepository.saveAll(listOfOpportunities);

    }
}
