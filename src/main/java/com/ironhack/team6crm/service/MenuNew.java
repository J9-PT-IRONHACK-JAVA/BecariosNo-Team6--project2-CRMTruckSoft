package com.ironhack.team6crm.service;

import com.ironhack.team6crm.model.*;
import com.ironhack.team6crm.repository.AccountRepository;
import com.ironhack.team6crm.repository.ContactRepository;
import com.ironhack.team6crm.repository.LeadRepository;
import com.ironhack.team6crm.repository.OpportunityRepository;
import com.ironhack.team6crm.utils.InputData;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
@Service
public class MenuNew {
    private final LeadRepository leadRepository;
    private final OpportunityRepository opportunityRepository;
    private final AccountRepository accountRepository;
    private final ContactRepository contactRepository;

    private final Scanner scanner = new Scanner(System.in);
    static Industry currentIndustry= Industry.OTHER;
    public void createNew(String option, SalesRep salesRep){
        switch (option) {
            case "lead": {
                List<String> leadData = InputData.getInputData("name: \n", "phone number: \n", "email: \n", "company name: \n");
                Lead newLead= new Lead(leadData.get(0), leadData.get(1), leadData.get(2), leadData.get(3), salesRep);
                leadRepository.save(newLead);
                System.out.println("New lead " + leadData.get(0) + " has been successfully created");
                break;
            }
            case "account": {
                var input = "";
                while (!input.equalsIgnoreCase("EXIT")) {
                    System.out.println("Available industries: ");
                    var industries =  List.of(Industry.PRODUCE, Industry.ECOMMERCE, Industry.MANUFACTURING, Industry.MEDICAL,Industry.OTHER);
                    for (Industry i : industries) {
                        System.out.printf("%s - %s\n", i.ordinal() , i.name());
                    }
                    System.out.println("Pick an industry or EXIT");
                    input = scanner.nextLine();
                    int parsedInput = Integer.parseInt(input);
                    if (parsedInput <= 5 && parsedInput >=0) {
                        System.out.println("You picked an industry");
                        currentIndustry=Industry.values()[parsedInput];
                        break;
                    }
                    else if (!input.equalsIgnoreCase("exit")) {
                        System.out.println("Unrecognized command!");
                    } else {
                        System.exit(0);
                    }
                }
                List<String> accountData = InputData.getInputData("company name: \n", "employee count: \n", "city: \n", "country: \n");
                Account newAccount= new Account(currentIndustry, accountData.get(0), Integer.parseInt(accountData.get(1)), accountData.get(2), accountData.get(3), salesRep);
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
                Contact newContact= new Contact(contactData.get(0), contactData.get(1), contactData.get(2), salesRep);
                contactRepository.save(newContact);
                System.out.println("New contact " + contactData.get(0) + " has been successfully created");
                break;
            }
        }

    }
}

