package com.ironhack.team6crm.service.menu;

import com.ironhack.team6crm.model.Account;
import com.ironhack.team6crm.model.Contact;
import com.ironhack.team6crm.model.Lead;
import com.ironhack.team6crm.model.Opportunity;
import com.ironhack.team6crm.repository.AccountRepository;
import com.ironhack.team6crm.repository.ContactRepository;
import com.ironhack.team6crm.repository.LeadRepository;
import com.ironhack.team6crm.repository.OpportunityRepository;
import com.ironhack.team6crm.utils.ConsoleColors;
import com.ironhack.team6crm.utils.UtilPrints;
import com.ironhack.team6crm.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class MenuList {
    private final LeadRepository leadRepository;
    private final OpportunityRepository opportunityRepository;
    private final AccountRepository accountRepository;
    private final ContactRepository contactRepository;
    private final Utils utils;
    private final UtilPrints utilPrints;

    public void listMenu(String[] options) throws IOException {
        switch (options[1]){
            case "lead":{
                var listOfLeads = leadRepository.findAll();
                if (listOfLeads.isEmpty()){
                    utilPrints.printWithColor("Leads table is empty...",ConsoleColors.RED_BOLD);
                }else {
                    System.out.println(ConsoleColors.YELLOW_BOLD +
                            "LIST OF ALL LEADS\n" +
                            "===========================================" +
                            "========================================"+
                            "========================================");
                    System.out.printf("|%-3s | %-25s | %-35s | %-20s | %-25s | \n",
                            "ID","NAME","COMPANY NAME","PHONE NUMBER","EMAIL");
                    System.out.println("===========================================" +
                            "========================================"+
                            "========================================");
                    for (Lead lead : listOfLeads) {
                        System.out.printf("|%-3s | %-25s | %-35s | %-20s | %-25s | \n",
                                lead.getId(),lead.getName(),lead.getCompanyName(),
                                lead.getPhoneNumber(),lead.getEmail());
                    }
                }
                System.out.println("==========================================="+
                                "========================================"+
                                "========================================"+
                        ConsoleColors.RESET);
                utils.promptEnterKey();
                utils.clearScreen();
                break;
            }
            case "opportunity":{
                var listOfAllOpportunities = opportunityRepository.findAll();
                if (listOfAllOpportunities.isEmpty()){
                    utilPrints.printWithColor("Opportunities table is empty...",ConsoleColors.RED_BOLD);
                }else {
                    System.out.println(ConsoleColors.YELLOW_BOLD +
                            "LIST OF ALL OPPORTUNITIES\n" +
                            "==================================" +
                            "======================================================");
                    System.out.printf("|%-3s | %-10s | %-10s | %-15s | %-35s | \n",
                            "ID","PRODUCT","QUANTITY","STATUS","COMPANY NAME (ID)");
                    System.out.println("==================================" +
                            "======================================================");
                    for (Opportunity opportunity : listOfAllOpportunities) {
                        System.out.printf("|%-3s | %-10s | %-10s | %-15s | %-35s | \n",
                                opportunity.getId(),opportunity.getProduct(),opportunity.getQuantity(),
                                opportunity.getStatus(),
                                opportunity.getAccount().getCompanyName()+"("+opportunity.getAccount().getId()+")");                    }
                }
                System.out.println("==================================" +
                        "======================================================"+
                        ConsoleColors.RESET);
                utils.promptEnterKey();
                utils.clearScreen();
                break;
            }
            case "account":{
                var listOfAllAccounts = accountRepository.findAll();
                if (listOfAllAccounts.isEmpty()){
                    utilPrints.printWithColor("Account table is empty...",ConsoleColors.RED_BOLD);
                }else {
                    System.out.println(ConsoleColors.YELLOW_BOLD +
                            "LIST OF ALL ACCOUNTS\n" +
                            "==================================================" +
                            "=================================================");
                    System.out.printf("|%-3s | %-31s | %-25s | %-15s | %-10s | \n",
                            "ID","COMPANY NAME","LOCATION","INDUSTRY","EMPLOYEES");
                    System.out.println("==================================================" +
                            "=================================================");
                    for (Account account : listOfAllAccounts) {
                        System.out.printf("|%-3s | %-31s | %-25s | %-15s | %-10s | \n",
                                account.getId(),account.getCompanyName(),
                                account.getCity()+"("+account.getCountry()+")",
                                account.getIndustry(),account.getEmployeeCount());
                    }
                }
                System.out.println("==================================================" +
                        "=================================================");
                utils.promptEnterKey();
                utils.clearScreen();
                break;
            }
            case "contact":{
                var listOfAllAccounts = contactRepository.findAll();
                if (listOfAllAccounts.isEmpty()){
                    utilPrints.printWithColor("Contacts table is empty...",ConsoleColors.RED_BOLD);
                }else {
                    System.out.println(ConsoleColors.YELLOW_BOLD +
                            "LIST OF ALL CONTACTS\n" +
                            "====================================================" +
                            "===============================================================");
                    System.out.printf("|%-3s | %-22s | %-15s | %-25s | %-35s | \n",
                            "ID","CONTACT NAME","PHONE NUMBER","EMAIL","COMPANY");
                    System.out.println("====================================================" +
                            "===============================================================");

                    for (Contact contact : listOfAllAccounts) {
                        System.out.printf("|%-3s | %-22s | %-15s | %-25s | %-35s | \n",
                                contact.getId(),contact.getName(),contact.getPhoneNumber(),
                                contact.getEmail(),contact.getAccount().getCompanyName());
                    }
                }
                System.out.println("====================================================" +
                        "===============================================================");
                utils.promptEnterKey();
                utils.clearScreen();
                break;
            }
            default:{
                String message = "Please put the command complete, for more information type 'help'.";
                utilPrints.printWithColor(message, ConsoleColors.RED_BOLD);
                utils.promptEnterKey();
                utils.clearScreen();
            }
        }
    }
}
