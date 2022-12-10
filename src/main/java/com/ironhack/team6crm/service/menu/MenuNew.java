package com.ironhack.team6crm.service.menu;

import com.ironhack.team6crm.model.*;
import com.ironhack.team6crm.repository.AccountRepository;
import com.ironhack.team6crm.repository.ContactRepository;
import com.ironhack.team6crm.repository.LeadRepository;
import com.ironhack.team6crm.repository.OpportunityRepository;
import com.ironhack.team6crm.service.AccountService;
import com.ironhack.team6crm.service.ContactService;
import com.ironhack.team6crm.utils.ConsoleColors;
import com.ironhack.team6crm.utils.InputData;
import com.ironhack.team6crm.utils.UtilPrints;
import com.ironhack.team6crm.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
@Service
public class MenuNew {
    private final LeadRepository leadRepository;
    private final OpportunityRepository opportunityRepository;
    private final AccountRepository accountRepository;
    private final ContactRepository contactRepository;
    private final ContactService contactService;
    private final AccountService accountService;
    private final MenuConvert menuConvert;
    private final Scanner scanner = new Scanner(System.in);
    static Industry currentIndustry= Industry.OTHER;
    static Product currentProduct= Product.BOX;
    static Status currentStatus= Status.OPEN;
    static Long currentContactId = null;
    static Contact currentContact = null;
    static Long currentAccountId = null;
    static Account currentAccount = null;
    private final Utils utils;
    private final UtilPrints utilPrints;

    private final InputData inputData;

    public void createNew(String option) {
        switch (option) {
            case "lead": {
                //Get the data for the lead
                List<String> leadData = inputData.getInputData("Name: ", "Phone number: ");
                var email = inputData.inputEmail("Email:");
                leadData.add(inputData.getInputData("Company name: ").get(0));
                //Create lead
                Lead newLead = new Lead(leadData.get(0), leadData.get(1), email, leadData.get(2), Menu.currentUserLogged);
                leadRepository.save(newLead);
                utilPrints.printWithColor("New lead " + leadData.get(0) + " has been successfully created", ConsoleColors.GREEN);
                utils.promptEnterKey();
                break;
            }
            case "account": {
                var companyName = inputData.inputString("Company name:");
                var employeeCount = inputData.inputInteger("Employee count:");
                List<String> accountData = inputData.getInputData("City:", "Country:");
                //Pick an industry from the enums
                var industry = inputData.selectIndustry();
                //Pick a contact from the contact list or create a contact
                var initialContact = chooseContact();
                Account newAccount= new Account(companyName, employeeCount, accountData.get(0), accountData.get(1), industry, Menu.currentUserLogged);
                newAccount = accountRepository.save(newAccount);
                initialContact.setAccount(newAccount);
                contactRepository.save(initialContact);
                utilPrints.printWithColor("New account for " + accountData.get(0) + " has been successfully created", ConsoleColors.GREEN);
                utils.promptEnterKey();
                break;
            }
            case "opportunity": {
                //Pick a product from the enums
                var product = inputData.selectProduct();
                var productQuantity = inputData.inputInteger("Quantity:");
                //Pick a contact from the contact list or create a contact
                var decisionMaker = chooseContact();
                //Pick a status from the enums
                var status = inputData.selectStatus();
                //Pick an account from a list
                var relatedAccount = chooseAccount();
                Opportunity newOpportunity= new Opportunity(product, productQuantity,decisionMaker, relatedAccount, Menu.currentUserLogged, status);
                opportunityRepository.save(newOpportunity);
                utilPrints.printWithColor("New opportunity has been successfully created", ConsoleColors.GREEN);
                utils.promptEnterKey();
                break;
            }
            case "contact": {
                createContact();
                utils.promptEnterKey();
                break;
            }
            default:{
                utilPrints.printWithColor("Please type a valid command. For more information, type 'help'", ConsoleColors.RED);
                utils.promptEnterKey();
            }
        }
    }

    private Contact chooseContact() {
        Contact contactChosen = null;
        do {
            System.out.println("Available contacts to be linked: ");
            var contacts = contactService.findAll();
            for (Contact c : contacts) {
                System.out.printf("%s - %s\n", c.getId(), c.getName());
            }
            System.out.println("Pick a contact or CREATE a new contact");
            var inputContact = scanner.nextLine();
            if (inputContact.matches("\\d+")) {
                var selectedId = Long.parseLong(inputContact);
                var contactFound = contactService.findById(selectedId);
                if (contactFound.isPresent()) {
                    contactChosen = contactFound.get();
                } else {
                    utilPrints.printWithColor("Not a valid contact selection", ConsoleColors.RED);
                }
            } else if (inputContact.equalsIgnoreCase("create")) {
                System.out.println("OK! Let's create a new contact");
                createContact();
            } else utilPrints.printWithColor("Unrecognized command!", ConsoleColors.RED);
        } while(contactChosen==null);
        return contactChosen;
    }


    private Account chooseAccount() {
        Account accountChosen = null;
        do {
            System.out.println("Available accounts to connect to the opportunity: ");
            var accounts = accountService.findAll();
            for (Account a : accounts) {
                System.out.printf("%s - %s\n", a.getId(), a.getCompanyName());
            }
            var selectedId = Long.valueOf(inputData.inputIntegerWithRange("Pick an account", 0, accounts.size()));
            var accountFound = accountService.findById(selectedId);
            if (accountFound.isPresent()) {
                accountChosen = accountFound.get();
            } else utilPrints.printWithColor("Not a valid account selection", ConsoleColors.RED);
        } while(accountChosen==null);
        return accountChosen;
    }

    private void createContact() {
        List<String> contactData = inputData.getInputData("Name:", "Phone number:");
        var email = inputData.inputEmail("Email:");
        Contact newContact= new Contact(contactData.get(0), contactData.get(1), email, Menu.currentUserLogged);
        contactRepository.save(newContact);
        utilPrints.printWithColor("New contact " + contactData.get(0) + " has been successfully created", ConsoleColors.GREEN);
    }
}

