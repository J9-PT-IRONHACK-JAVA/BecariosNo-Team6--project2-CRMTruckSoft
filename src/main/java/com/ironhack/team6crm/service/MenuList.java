package com.ironhack.team6crm.service;

import com.ironhack.team6crm.model.Account;
import com.ironhack.team6crm.model.Contact;
import com.ironhack.team6crm.model.Lead;
import com.ironhack.team6crm.model.Opportunity;
import com.ironhack.team6crm.repository.AccountRepository;
import com.ironhack.team6crm.repository.ContactRepository;
import com.ironhack.team6crm.repository.LeadRepository;
import com.ironhack.team6crm.repository.OpportunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
@Service
public class MenuList {
    @Autowired
    LeadRepository leadRepository;
    @Autowired
    OpportunityRepository opportunityRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ContactRepository contactRepository;

    public void listMenu(String[] options) {
        switch (options[1]){
            case "lead":{
                System.out.println("List of leads:");
                var listOfLeads = leadRepository.findAll();
                for(Lead lead : listOfLeads){
                    System.out.println(lead.toString());
                }
                break;
            }
            case "opportunity":{
                System.out.println("List of opportunity");
                var listOfOpportunities = opportunityRepository.findAll();
                for(Opportunity opportunity : listOfOpportunities){
                    System.out.println(opportunity.toString());
                }
                break;
            }
            case "account":{
                System.out.println("List of accounts: ");
                var listOfAccounts = accountRepository.findAll();
                for(Account account : listOfAccounts){
                    System.out.println(account.toString());
                }
                break;
            }
            case "contact":{
                System.out.println("List contact");
                var listofContacts = contactRepository.findAll();
                for(Contact contact : listofContacts){
                    System.out.println(contact.toString());
                }
                break;
            }
            default:{
                System.out.println("Please more info!");
            }
        }
    }
}
