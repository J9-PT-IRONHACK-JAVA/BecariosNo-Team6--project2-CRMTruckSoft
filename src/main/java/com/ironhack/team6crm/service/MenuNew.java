package com.ironhack.team6crm.service;

import com.ironhack.team6crm.model.Account;
import com.ironhack.team6crm.model.Contact;
import com.ironhack.team6crm.model.Lead;
import com.ironhack.team6crm.model.Opportunity;
import com.ironhack.team6crm.repository.AccountRepository;
import com.ironhack.team6crm.repository.ContactRepository;
import com.ironhack.team6crm.repository.LeadRepository;
import com.ironhack.team6crm.repository.OpportunityRepository;
import com.ironhack.team6crm.utils.InputData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MenuNew {
    @Autowired
    LeadRepository leadRepository;
    @Autowired
    OpportunityRepository opportunityRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ContactRepository contactRepository;
    public void createNew(String option){
        switch (option) {
            case "lead": {
                List<String> leadData = InputData.getInputData("name: \n", "phone number: \n", "email: \n", "company name: \n");
                Lead newLead= new Lead(leadData.get(0), leadData.get(1), leadData.get(2), leadData.get(3));
                leadRepository.save(newLead);
                System.out.println("New lead " + leadData.get(0) + " has been successfully created");
                break;
            }
            case "account": {
                List<String> accountData = InputData.getInputData("company name: \n", "employee count: \n", "city: \n", "country: \n");
                Account newAccount= new Account(accountData.get(0), Integer.parseInt(accountData.get(1)), accountData.get(2), accountData.get(3));
                accountRepository.save(newAccount);
                System.out.println("New account for " + accountData.get(0) + " has been successfully created");
                break;
            }
            case "opportunity": {
                System.out.println("New opportunity Menu");
                break;
            }
            case "contact": {
                List<String> contactData = InputData.getInputData("name: \n", "phone number: \n", "email: \n");
                Contact newContact= new Contact(contactData.get(0), contactData.get(1), contactData.get(2));
                contactRepository.save(newContact);
                System.out.println("New contact " + contactData.get(0) + " has been successfully created");
                break;
            }
        }

    }
}

