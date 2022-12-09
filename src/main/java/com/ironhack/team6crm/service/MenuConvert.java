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

@Service
@RequiredArgsConstructor
public class MenuConvert {

    private final LeadRepository leadRepository;
    private final OpportunityRepository opportunityRepository;
    private final AccountRepository accountRepository;
    private final ContactRepository contactRepository;
    private final Scanner scanner = new Scanner(System.in);
    private final MenuLookup lookUp;
    private final Utils utils;
    private final UtilPrints utilPrints;

    public void convertMenu(String[] options) throws Exception {
        Long leadId=null;
        if (options[1].equalsIgnoreCase("lead")) {
            try {
                leadId = Long.valueOf(options[2]);
            } catch (NumberFormatException e) {
                System.out.println(" is not a valid ID");
            }
            convertLead(leadId);
        } else utilPrints.invalidCommand();
    }


    //The CRM begins by creating a new Contact object with the contact information from Mike’s Lead object.
    //
    //The CRM then prompts Sara for the product that Mike is interested in (Hybrid Truck, Flatbed Truck, or Box Truck)
    // and the number of trucks that he is interested in buying.
    //
    // Sara inputs the information “Hybrid Truck” and “30”. The CRM creates a new Opportunity object with this information.
    // It adds the new Mike Contact object as the decisionMaker for the Opportunity and sets the status to “OPEN”.
    //
    //The CRM then prompts Sara for the industry, number of employees, city, and country of Mike’s company.
    // Sara inputs the information “Produce”, “4000”, “Berlin”, “Germany”.
    // The CRM takes the Company name from Mike’s Lead Object and creates a new Account Object with this information.
    // It adds the Mike Contact to the contactList of the Account and the new Opportunity to the opportunityList of the Account.
    //
    //Finally, the System deletes the Mike Lead.


    public void convertLead (Long id) throws Exception {
        Lead originLead = null;
        boolean validData = true;
        try {
            originLead = leadRepository.findById(id).orElseThrow(() -> new Exception("Lead not found in DB"));
        } catch (Exception e) {
            System.out.println(e);
            validData=false;
        }

        if (validData) {
            //Create the contact
            var contactFromLead = new Contact(originLead.getName(), originLead.getPhoneNumber(), originLead.getEmail(), Menu.currentUserLogged);
            var storedContactFromLead = contactRepository.save(contactFromLead);

            //Create the Opportunity
            var productSelected = selectProduct();
            System.out.println("\nNumber of units:");
            var productUnits = scanner.next().trim().toLowerCase(); //TODO validar que sea number y no dejar seguir sino.

            var newOpportunity = new Opportunity(productSelected, Integer.parseInt(productUnits), storedContactFromLead, Menu.currentUserLogged, Status.OPEN);
            var storedOpportunity = opportunityRepository.save(newOpportunity);

            //Create the Account //TODO meter en while para que te de la info correcta de account.
            System.out.println("\nPlease fill in some information about the Company, for the Account records: \n");
            List<String> accountData = InputData.getInputData("Employee count: ", "City: ", "Country: ");
            var industrySelected = selectIndustry();
            Account newAccount = new Account(industrySelected, originLead.getCompanyName(), Integer.parseInt(accountData.get(0)), accountData.get(1), accountData.get(2), Menu.currentUserLogged);
            var storedAccount = accountRepository.save(newAccount);

            //Link the Opportunity and Contact to the Account
            storedOpportunity.setAccount(storedAccount);
            opportunityRepository.save(storedOpportunity);
            storedContactFromLead.setAccount(storedAccount);
            contactRepository.save(storedContactFromLead);

            //Delete lead
            leadRepository.delete(originLead);

            //Confirm process and account created with related objects.
            System.out.println("\nLead converted successfully! \n");

            utils.pause(1500);

            utilPrints.printWithColor("New Account with associated objects:", ConsoleColors.YELLOW);
            lookUp.lookupMenu(new String[]{"lookup", "account", storedAccount.getId().toString()});
        }
    }

    public Product selectProduct() {
        Product productSelected = null;
        var input="";
        var gotcha="";
        while (!gotcha.equals("OK")) {
            System.out.println("\nWhich product is the client interested in?\n");

            for (Product prod : Product.values()){
                System.out.printf("%s - %s\n", prod.ordinal() , prod.name());
            }
            System.out.println("Please type your selection by number:");

            input = scanner.next();
            int parsedInput = Integer.parseInt(input); //TODO Handlear el error si no es un numero. Sea aquí o sea arriba en el scanner con un nextInt()
            if (parsedInput < Product.values().length && parsedInput >=0) {
                productSelected=Product.values()[parsedInput];
                gotcha="OK";
            } else {
                System.out.println("Unrecognized command!");
            }
        } return productSelected;
    }

    public Industry selectIndustry() {
        Industry industrySelected = null;
        var input="";
        var gotcha="";
        while (!gotcha.equals("OK")) {
            System.out.println("\nWhich is the client's industry?\n");

            for (Industry ind : Industry.values()){
                System.out.printf("%s - %s\n", ind.ordinal() , ind.name());
            }
            System.out.println("Please type your selection by number:");

            input = scanner.next();
            int parsedInput = Integer.parseInt(input); //TODO Handlear el error si no es un numero. Sea aquí o sea arriba en el scanner con un nextInt()
            if (parsedInput < Industry.values().length && parsedInput >=0) {
                industrySelected=Industry.values()[parsedInput];
                gotcha="OK";
            } else {
                System.out.println("Unrecognized command!");
            }
        } return industrySelected;
    }


}
