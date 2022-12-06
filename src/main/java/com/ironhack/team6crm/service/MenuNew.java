package com.ironhack.team6crm.service;

import com.ironhack.team6crm.model.*;
import com.ironhack.team6crm.repository.AccountRepository;
import com.ironhack.team6crm.repository.ContactRepository;
import com.ironhack.team6crm.repository.LeadRepository;
import com.ironhack.team6crm.repository.OpportunityRepository;
import com.ironhack.team6crm.utils.ConsoleColors;
import com.ironhack.team6crm.utils.InputData;
import com.ironhack.team6crm.utils.UtilPrints;
import com.ironhack.team6crm.utils.Utils;
import lombok.RequiredArgsConstructor;
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

    public void createNew(String option, SalesRep salesRep){
        switch (option) {
            case "lead": {
                List<String> leadData = InputData.getInputData("name: \n", "phone number: \n", "email: \n", "company name: \n");
                Lead newLead= new Lead(leadData.get(0), leadData.get(1), leadData.get(2), leadData.get(3), salesRep);
                leadRepository.save(newLead);
                utilPrints.printWithColor("New lead " + leadData.get(0) + " has been successfully created", ConsoleColors.GREEN);
                break;
            }
            case "account": {
                //Pick an industry from the enums
                menuConvert.selectIndustry();
                //Pick a contact from the contact list or create a contact
                chooseContact(salesRep);
                List<String> accountData = InputData.getInputData("company name: \n", "employee count: \n", "city: \n", "country: \n");
                Account newAccount= new Account(currentIndustry, accountData.get(0), Integer.parseInt(accountData.get(1)), accountData.get(2), accountData.get(3), salesRep);
                accountRepository.save(newAccount);
                utilPrints.printWithColor("New account for " + accountData.get(0) + " has been successfully created", ConsoleColors.GREEN);
                break;
            }
            case "opportunity": {
                //Pick a product from the enums
                menuConvert.selectProduct();
                List<String> opportunityData = InputData.getInputData("quantity: \n");
                //Pick a contact from the contact list or create a contact
                chooseContact(salesRep);
                //Pick a status from the enums
                chooseStatus();
                //Pick an account from a list
                chooseAccount();
                Opportunity newOpportunity= new Opportunity(currentProduct, Integer.parseInt(opportunityData.get(0)),currentContact, currentAccount, salesRep,currentStatus );
                opportunityRepository.save(newOpportunity);
                utilPrints.printWithColor("New opportunity has been successfully created", ConsoleColors.GREEN);
                break;
            }
            case "contact": {
                var status="";
                List<String> contactData = InputData.getInputData("name: \n", "phone number: \n", "email: \n");

                while (!status.equals("OK")) {
                    utilPrints.printWithColor("Please introduce a valid email", ConsoleColors.RED);
                    List<String> email = InputData.getInputData( "email: \n");

                    if (utils.validateEmail(email.get(0))) {
                        Contact newContact= new Contact(contactData.get(0), contactData.get(1), email.get(0), salesRep);
                        contactRepository.save(newContact);
                        utilPrints.printWithColor("New contact " + contactData.get(0) + " has been successfully created", ConsoleColors.GREEN);
                        status="OK";
                    }
                } break;
            }
        }
    }

    private void chooseContact(SalesRep salesRep) {
        var inputContact = "";
        while (!inputContact.equalsIgnoreCase("EXIT")) {
            System.out.println("Available contacts to connect to your account: ");
            var contacts = contactService.findAll();
            for (Contact c : contacts) {
                System.out.printf("%s - %s\n", c.getId(), c.getName());
            }
            System.out.println("Pick a contact or CREATE a new contact");
            inputContact = scanner.nextLine();
            if (inputContact.matches("\\d+")) {
                System.out.println("You picked an id");
                var selectedId = Long.parseLong(inputContact);
                var contactFound = contactService.findById(selectedId);
                if (contactFound.isPresent()) {
                    utilPrints.printWithColor("Valid contact picked", ConsoleColors.GREEN);
                    currentContactId =selectedId;
                    currentContact = contactFound.get();
                    break;
                } else {
                    utilPrints.printWithColor("Not a valid contact selection", ConsoleColors.RED);
                }
            }else if (inputContact.equalsIgnoreCase("create")) {
                System.out.println("you want to create an new contact");
                createContactRoutine(salesRep);
            }
            else if (!inputContact.equalsIgnoreCase("exit")) {
                utilPrints.printWithColor("Unrecognized command!", ConsoleColors.RED);
            } else {
                System.exit(0);
            }
        }
    }

    private void chooseAccount() {
        var inputAccount = "";
        while (!inputAccount.equalsIgnoreCase("EXIT")) {
            System.out.println("Available accounts to connect to the opportunity: ");
            var accounts = accountService.findAll();
            for (Account a : accounts) {
                System.out.printf("%s - %s\n", a.getId(), a.getCompanyName());
            }
            System.out.println("Pick an account");
            inputAccount = scanner.nextLine();
            if (inputAccount.matches("\\d+")) {
                System.out.println("You picked an id");
                var selectedId = Long.parseLong(inputAccount);
                var accountFound = accountService.findById(selectedId);
                if (accountFound.isPresent()) {
                    utilPrints.printWithColor("Valid account picked", ConsoleColors.BLUE);
                    currentAccountId =selectedId;1
                    currentAccount = accountFound.get();
                    break;
                } else {
                    utilPrints.printWithColor("Not a valid account selection", ConsoleColors.RED);
                }
            }
            else if (!inputAccount.equalsIgnoreCase("exit")) {
                utilPrints.printWithColor("Unrecognized command!", ConsoleColors.RED);
            } else {
                System.exit(0);
            }
        }
    }

    private void chooseStatus() {
        var input = "";
        while (!input.equalsIgnoreCase("EXIT")) {
            System.out.println("Available statuses: ");
            var statuses =  List.of(Status.OPEN, Status.CLOSED_WON, Status.CLOSED_LOST );
            for (Status s : statuses) {
                System.out.printf("%s - %s\n", s.ordinal() , s.name());
            }
            System.out.println("Pick a status or EXIT");
            input = scanner.nextLine();
            int parsedInput = Integer.parseInt(input);
            if (parsedInput < 3 && parsedInput >=0) {
                System.out.println("You picked a status");
                currentStatus=Status.values()[parsedInput];
                break;
            }
            else if (!input.equalsIgnoreCase("exit")) {
                System.out.println("Unrecognized command!");
            } else {
                System.exit(0);
            }
        }
    }
    private void createContactRoutine(SalesRep salesRep) {
        var inputName = "";
        var inputPhone = "";
        var inputEmail = "";
            var newContact = new Contact();
            System.out.println("Enter the name of the contact:");
            inputName = scanner.nextLine().trim();
            newContact.setName(inputName);

            System.out.println("Enter the phone of the contact:");
            inputPhone = scanner.nextLine().trim();
            newContact.setPhoneNumber(inputPhone);

            System.out.println("Enter the email of the contact:");
            inputEmail = scanner.nextLine().trim();
            newContact.setEmail(inputEmail);

            newContact.setSalesRep(salesRep);

            var contact = contactService.save(newContact);
            System.out.printf("Congrats! new contact created with name: %s and id: %s\n", contact.getName(), contact.getId());
    }
}

