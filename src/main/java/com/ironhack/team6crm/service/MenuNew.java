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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class MenuNew {
    private final LeadRepository leadRepository;
    private final OpportunityRepository opportunityRepository;
    private final AccountRepository accountRepository;
    private final ContactRepository contactRepository;
<<<<<<< Updated upstream
    public void createNew(String option){
=======
    private final ContactService contactService;
    private final AccountService accountService;

    private final Scanner scanner = new Scanner(System.in);
    static Industry currentIndustry= Industry.OTHER;
    static Product currentProduct= Product.BOX;
    static Status currentStatus= Status.OPEN;
    static Long currentContactId = null;
    static Contact currentContact = null;
    static Long currentAccountId = null;
    static Account currentAccount = null;

    public void createNew(String option, SalesRep salesRep){
>>>>>>> Stashed changes
        switch (option) {
            case "lead": {
                List<String> leadData = InputData.getInputData("name: \n", "phone number: \n", "email: \n", "company name: \n");
                Lead newLead= new Lead(leadData.get(0), leadData.get(1), leadData.get(2), leadData.get(3));
                leadRepository.save(newLead);
                System.out.println("New lead " + leadData.get(0) + " has been successfully created");
                break;
            }
            case "account": {
<<<<<<< Updated upstream
=======
                //Pick an industry from the enums
                chooseIndustry();
                //Pick a contact from the contact list or create a contact
                chooseContact(salesRep);
>>>>>>> Stashed changes
                List<String> accountData = InputData.getInputData("company name: \n", "employee count: \n", "city: \n", "country: \n");
                Account newAccount= new Account(accountData.get(0), Integer.parseInt(accountData.get(1)), accountData.get(2), accountData.get(3));
                accountRepository.save(newAccount);
                System.out.println("New account for " + accountData.get(0) + " has been successfully created");
                break;
            }
            case "opportunity": {
                //Pick a product from the enums
                chooseProduct();
                List<String> opportunityData = InputData.getInputData("quantity: \n");
                //Pick a contact from the contact list or create a contact
                chooseContact(salesRep);
                //Pick a status from the enums
                chooseStatus();
                //Pick an account from a list
                chooseAccount();
                Opportunity newOpportunity= new Opportunity(currentProduct, Integer.parseInt(opportunityData.get(0)),currentContact, currentAccount, salesRep,currentStatus );
                opportunityRepository.save(newOpportunity);
                System.out.println("New opportunity has been successfully created");
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
                    System.out.println("Valid contact picked");
                    currentContactId =selectedId;
                    currentContact = contactFound.get();
                    break;
                } else {
                    System.out.println("Not a valid contact selection");
                }
            }else if (inputContact.equalsIgnoreCase("create")) {
                System.out.println("you want to create an new contact");
                createContactRoutine(salesRep);
            }
            else if (!inputContact.equalsIgnoreCase("exit")) {
                System.out.println("Unrecognized command!");
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
                    System.out.println("Valid account picked");
                    currentAccountId =selectedId;
                    currentAccount = accountFound.get();
                    break;
                } else {
                    System.out.println("Not a valid account selection");
                }
            }
            else if (!inputAccount.equalsIgnoreCase("exit")) {
                System.out.println("Unrecognized command!");
            } else {
                System.exit(0);
            }
        }
    }

    private void chooseIndustry() {
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
            if (parsedInput < 5 && parsedInput >=0) {
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
    }

    private void chooseProduct() {
        var input = "";
        while (!input.equalsIgnoreCase("EXIT")) {
            System.out.println("Available industries: ");
            var products =  List.of(Product.HYBRID, Product.FLATBED,Product.BOX );
            for (Product p : products) {
                System.out.printf("%s - %s\n", p.ordinal() , p.name());
            }
            System.out.println("Pick a product or EXIT");
            input = scanner.nextLine();
            int parsedInput = Integer.parseInt(input);
            if (parsedInput < 3 && parsedInput >=0) {
                System.out.println("You picked a product");
                currentProduct=Product.values()[parsedInput];
                break;
            }
            else if (!input.equalsIgnoreCase("exit")) {
                System.out.println("Unrecognized command!");
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
            inputName = scanner.nextLine().trim().toLowerCase();
            newContact.setName(inputName);

            System.out.println("Enter the phone of the contact:");
            inputPhone = scanner.nextLine().trim().toLowerCase();
            newContact.setPhoneNumber(inputPhone);

            System.out.println("Enter the email of the contact:");
            inputEmail = scanner.nextLine().trim().toLowerCase();
            newContact.setEmail(inputEmail);

            newContact.setSalesRep(salesRep);

            var contact = contactService.save(newContact);
            System.out.printf("Congrats! new contact created with name: %s and id: %s\n", contact.getName(), contact.getId());
    }
}

