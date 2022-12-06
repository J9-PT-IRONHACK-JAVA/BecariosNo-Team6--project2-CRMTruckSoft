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
import org.springframework.stereotype.Service;

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
        var Sarah = new SalesRep("Sarah");
        var Peter = new SalesRep("Peter");
        Sarah = salesRepService.save(Sarah);
        Peter = salesRepService.save(Peter);

        var listOfLeads = List.of(
                new Lead("Mike LeadOne", "+34 111111111", "mail111@gmail.com", "Man Truck SA", Sarah),
                new Lead("Pedro LeadTwo", "+34 222222222", "mail222@gmail.com", "Ivecco Logistics SL", Sarah),
                new Lead("William LeadThree", "+34 333333333", "mail333@gmail.com", "Pegaso Motor SA", Sarah),
                new Lead("Erika LeadFour", "+34 444444444", "mail444@gmail.com", "Mercedes Benz Trucks SA", Peter),
                new Lead("Olaf LeadFive", "+34 555555555", "mail555@gmail.com", "Volvo Trucking Company SA", Peter)
        );
        leadRepository.saveAll(listOfLeads);
        //Sarah.setLead(listOfLeads);

        var listOfAccounts = List.of(
                new Account(Industry.OTHER, "Man Truck SA", 200, "Detroit", "USA", Sarah),
                new Account(Industry.MEDICAL, "Ivecco Logistics SL", 100, "Barcelona", "ESP", Peter),
                new Account(Industry.OTHER, "Pegaso Motor SA", 230, "London", "UK", Sarah),
                new Account(Industry.MANUFACTURING, "Mercedes Benz Trucks SA", 1100, "Frankfurt AM", "Germany", Sarah),
                new Account(Industry.MANUFACTURING, "Volvo Trucking Company SA", 670, "Goteborg", "Sweden", Sarah)
        );
        var savedAccounts = accountRepository.saveAll(listOfAccounts);

        var listOfContacts = List.of(
                new Contact("Mike LeadOne", "+34 111111111", "mail111@gmail.com", Sarah),
                new Contact("Pedro LeadTwo", "+34 222222222", "mail222@gmail.com", Peter),
                new Contact("William LeadThree", "+34 333333333", "mail333@gmail.com", Sarah),
                new Contact("Erika LeadFour", "+34 444444444", "mail444@gmail.com", Sarah),
                new Contact("Olaf LeadFive", "+34 555555555", "mail555@gmail.com", Peter)
        );
        var savedContacts = contactRepository.saveAll(listOfContacts);

        var listOfOpportunities = List.of(
                new Opportunity(Product.BOX, 5, listOfContacts.get(0), Sarah, Status.OPEN),
                new Opportunity(Product.FLATBED, 10, listOfContacts.get(1), Sarah, Status.OPEN),
                new Opportunity(Product.BOX, 20, listOfContacts.get(2), Sarah, Status.CLOSED_LOST),
                new Opportunity(Product.HYBRID, 8, listOfContacts.get(3), Sarah, Status.CLOSED_WON)
        );
        var savedOpportunities = opportunityRepository.saveAll(listOfOpportunities);


    }

//    void linkUponLoading(List<Contact> savedContacts, List<Opportunity> savedOpportunities, List<Account> savedAccounts){
//        for (int i = 0; i < savedContacts.size(); i++) {
//            savedContacts.get(i).setAccount(savedAccounts.get(i));
//        }
//        contactRepository.saveAll(savedContacts);
//
//        for (int i = 0; i < savedContacts.size(); i++) {
//            savedOpportunities.get(i).setAccount(savedAccounts.get(i));
//        }
//        opportunityRepository.saveAll(savedOpportunities);
//    }

}
