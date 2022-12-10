package com.ironhack.team6crm.utils;

import com.ironhack.team6crm.model.Account;
import com.ironhack.team6crm.model.Contact;
import com.ironhack.team6crm.model.Lead;
import com.ironhack.team6crm.model.Opportunity;
import com.ironhack.team6crm.repository.ContactRepository;
import com.ironhack.team6crm.repository.OpportunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UtilPrints {

    final ContactRepository contactRepository;
    final OpportunityRepository opportunityRepository;
    final Utils utils;


    public void printLead(Lead lead){
        String nameOfSalesRep;
        try{
            nameOfSalesRep = lead.getSalesRep().getName();
        }catch (Exception e){
            nameOfSalesRep = "No SalesRep Assigned Yet";
        }
        System.out.println(ConsoleColors.YELLOW_BOLD+"\n*********************************************");
        System.out.println("Lead Nº         "+lead.getId());
        System.out.println("Phone Number:   "+lead.getPhoneNumber());
        System.out.println("Email:          "+lead.getEmail());
        System.out.println("Company:        "+lead.getCompanyName());
        System.out.println("User:           "+nameOfSalesRep);
        System.out.println("*********************************************\n"+ ConsoleColors.RESET);
    }

    public void printOpportunity(Opportunity opportunity) {
        String oppColor = "";

        if (opportunity.getStatus().toString().equals("OPEN")) oppColor = ConsoleColors.BLUE_BOLD;
        if (opportunity.getStatus().toString().equals("CLOSED_WON")) oppColor = ConsoleColors.GREEN_BOLD;
        if (opportunity.getStatus().toString().equals("CLOSED_LOST")) oppColor = ConsoleColors.RED_BOLD;

        System.out.println(ConsoleColors.YELLOW_BOLD+"\n*********************************************");
        System.out.println("Opportunity Nº      "+opportunity.getId());
        System.out.println("Company Name:       "+opportunity.getAccount().getCompanyName());
        System.out.println("Status:             "+oppColor+opportunity.getStatus().toString());
        System.out.println(ConsoleColors.YELLOW_BOLD+"---------------------------------------------");
        System.out.println("Product:            "+opportunity.getProduct()+ " | Quantity :  "+opportunity.getQuantity());
        System.out.println("Contact:            "+opportunity.getDecisionMaker().getName());
        System.out.println("Contact Phone:      "+opportunity.getDecisionMaker().getPhoneNumber());
        System.out.println("Contact Email:      "+opportunity.getDecisionMaker().getEmail());
        System.out.println("---------------------------------------------");
        System.out.println("User:               "+opportunity.getSalesRep().getName());
        System.out.println("*********************************************\n"+ ConsoleColors.RESET);
    }

    public void printAccount(Account account) {
        System.out.println(ConsoleColors.YELLOW_BOLD+"\n*********************************************");
        System.out.println("Account Nº      "+account.getId());
        System.out.println("Company Name:   "+account.getCompanyName());
        System.out.println("Industry:       "+account.getIndustry().toString());
        System.out.println("---------------------------------------------");
        System.out.println("Location:       "+account.getCity()+" - "+account.getCountry().toUpperCase());
        System.out.println("Employees:      "+account.getEmployeeCount());
        System.out.println("---------------------------------------------");
        System.out.println("Opportunities:");
        try {
            listOpportunitiesOfAccount(opportunityRepository.findAllByAccount_Id(account.getId()));
        }catch (Exception e){
            System.out.println("   - The account does not have Opportunities.");
        }
        System.out.println("---------------------------------------------");
        System.out.println("Contacts:");
        listContactsOfAccount(contactRepository.findAllByAccount_Id(account.getId()));
        System.out.println("---------------------------------------------");
        System.out.println("User Sales:     "+account.getSalesRep().getName());
        System.out.println("*********************************************\n"+ ConsoleColors.RESET);
    }

    private void listContactsOfAccount(List<Contact> contactList) {
        for (Contact contact : contactList) {
            System.out.printf("     - %-2s | %-22s | %-15s | %-25s \n",
                    contact.getId(),contact.getName(),contact.getPhoneNumber(),contact.getEmail());

        }
    }

    private void listOpportunitiesOfAccount(List<Opportunity> opportunityList) {

        for (Opportunity opportunity : opportunityList) {
            String oppColor = "";

            if (opportunity.getStatus().toString().equals("OPEN")) oppColor = ConsoleColors.BLUE_BOLD;
            if (opportunity.getStatus().toString().equals("CLOSED_WON")) oppColor = ConsoleColors.GREEN_BOLD;
            if (opportunity.getStatus().toString().equals("CLOSED_LOST")) oppColor = ConsoleColors.RED_BOLD;

            System.out.printf("     - %-3s | %-20s | %-15s | %-13s"+ConsoleColors.YELLOW_BOLD+"\n",
                    opportunity.getId(),"Product: "+ opportunity.getProduct(), "Quantity: "+opportunity.getQuantity(),
                    oppColor+opportunity.getStatus());
        }
    }


    public void printContact(Contact contact) {
        String nameOfSalesRep;
        try{
            nameOfSalesRep = contact.getSalesRep().getName();
        }catch (Exception e){
            nameOfSalesRep = "No SalesRep Assigned Yet";
        }
        System.out.println(ConsoleColors.YELLOW_BOLD+"\n*********************************************");
        System.out.println("Contact Nº      "+contact.getId());
        System.out.println("Name:           "+contact.getName());
        System.out.println("Phone Number:   "+contact.getPhoneNumber());
        System.out.println("Email:          "+contact.getEmail());
        System.out.println("---------------------------------------------");
        System.out.println("User Sales:     "+nameOfSalesRep);
        System.out.println("*********************************************");
    }

    public void printWithColor(String text, String color){
        System.out.println(color + text + ConsoleColors.RESET);
    }

    public void printInvalidCommand(){
        printWithColor("Please type a valid command. For more information, type 'help'", ConsoleColors.RED);
        utils.promptEnterKey();;
    }

    public void printLogo(){
        var logo = ("""
                
                  ______ ______  ______     _______                   _           _           ___     \s
                 / _____|_____ \\|  ___ \\   (_______)                 | |         | |         / __)_   \s
                | /      _____) ) | _ | |   _        ____ _   _  ____| |  _       \\ \\   ___ | |__| |_ \s
                | |     (_____ (| || || |  | |      / ___) | | |/ ___) | / )       \\ \\ / _ \\|  __)  _)\s
                | \\_____      | | || || |  | |_____| |   | |_| ( (___| |< (    _____) ) |_| | |  | |__\s
                 \\______)     |_|_||_||_|   \\______)_|    \\____|\\____)_| \\_)  (______/ \\___/|_|   \\___)
                                                                                                      \s
                """);

        printWithColor(logo, ConsoleColors.WHITE_BOLD_BRIGHT);

    }

}
