package com.ironhack.team6crm.service;

import com.ironhack.team6crm.model.Account;
import com.ironhack.team6crm.model.Contact;
import com.ironhack.team6crm.model.Lead;
import com.ironhack.team6crm.model.Opportunity;
import com.ironhack.team6crm.repository.AccountRepository;
import com.ironhack.team6crm.repository.ContactRepository;
import com.ironhack.team6crm.repository.LeadRepository;
import com.ironhack.team6crm.repository.OpportunityRepository;
import com.ironhack.team6crm.utils.UtilPrints;
import com.ironhack.team6crm.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@RequiredArgsConstructor
@Service
public class MenuList {
    private final LeadRepository leadRepository;
    private final OpportunityRepository opportunityRepository;
    private final AccountRepository accountRepository;
    private final ContactRepository contactRepository;
    private final UtilPrints utilPrints;
    private final Utils utils;

    public void listMenu(String[] options) throws IOException {
        switch (options[1]){
            case "lead":{
                System.out.println("List of all leads:\n================================");
                var listOfLeads = leadRepository.findAll();
                if (listOfLeads.isEmpty()){
                    System.out.println("Leads table is empty...");
                }else {
                    for (Lead lead : listOfLeads) {
                        utilPrints.printLead(lead);
                    }
                }
                System.out.println("================================");
                utils.promptEnterKey();
                utils.clearScreen();
                break;
            }
            case "opportunity":{
                System.out.println("List of all Opportunities:\n================================");
                var listOfAllOpportunities = opportunityRepository.findAll();
                if (listOfAllOpportunities.isEmpty()){
                    System.out.println("Opportunities table is empty...");
                }else {
                    for (Opportunity opportunity : listOfAllOpportunities) {
                        utilPrints.printOpportunity(opportunity);
                    }
                }
                System.out.println("================================");
                utils.promptEnterKey();
                utils.clearScreen();
                break;
            }
            case "account":{
                System.out.println("List of all Accounts:\n================================");
                var listOfAllAccounts = accountRepository.findAll();
                if (listOfAllAccounts.isEmpty()){
                    System.out.println("Account table is empty...");
                }else {
                    for (Account account : listOfAllAccounts) {
                        utilPrints.printAccount(account);
                    }
                }
                System.out.println("================================");
                utils.promptEnterKey();
                utils.clearScreen();
                break;
            }
            case "contact":{
                System.out.println("List of all Contacts:\n================================");
                var listOfAllAccounts = contactRepository.findAll();
                if (listOfAllAccounts.isEmpty()){
                    System.out.println("Contacts table is empty...");
                }else {
                    for (Contact contact : listOfAllAccounts) {
                        utilPrints.printContact(contact);
                    }
                }
                System.out.println("================================");
                utils.promptEnterKey();
                utils.clearScreen();
                break;
            }
            default:{
                System.out.println("Please put the command complete, for more information type 'help'.");
                utils.promptEnterKey();
                utils.clearScreen();
            }
        }
    }
}
