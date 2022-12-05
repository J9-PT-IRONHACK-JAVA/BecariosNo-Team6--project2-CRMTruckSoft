package com.ironhack.team6crm.service;

import com.ironhack.team6crm.model.Lead;
import com.ironhack.team6crm.repository.AccountRepository;
import com.ironhack.team6crm.repository.ContactRepository;
import com.ironhack.team6crm.repository.OpportunityRepository;
import com.ironhack.team6crm.utils.InputData;
import com.ironhack.team6crm.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuLink {

    final OpportunityRepository opportunityRepository;
    final ContactRepository contactRepository;
    final AccountRepository accountRepository;

    final Utils utils;

    public void linkOpportunity(String[] option) throws IOException {
        switch (option[1]) {
            case "opportunity": {
                try {
                    var opportunity = opportunityRepository.findById(Long.valueOf(option[2]));
                    var account = accountRepository.findById(Long.valueOf(option[4]));

                    if (!option[3].toLowerCase().equals("to")){
                        System.out.println("Please put the command complete, for more information type 'help'.");
                        break;
                    }else if (opportunity.isEmpty()){
                        System.out.println("Please put a correct Nº of Opportunity." +
                                " For see all Opportunities type 'list opportunity'");
                        break;
                    }else if (account.isEmpty()){
                        System.out.println("Please put a correct Nº of Account." +
                                " For see all Accounts type 'list account'");
                        break;
                    }else {
                        // Get list of opportunities of Account
                        var listOfOpportunities = opportunityRepository.findAllByAccount_Id(account.get().getId());
                        // Add the new opportunity to list
                        listOfOpportunities.add(opportunity.get());
                        // Update list of opportunities in account
                        accountRepository.save(account.get());
                        // Update account in opportunity
                        opportunity.get().setAccount(account.get());
                        // Save opportunity
                        opportunityRepository.save(opportunity.get());

                        System.out.println("Opportunity Nº " + opportunity.get().getId() +
                                " assigned to Company "+account.get().getCompanyName()+
                                " (Account Nº " + account.get().getId() + ")");
                    }

                }catch (Exception e){
                    System.out.println("Error, format must be 'link opportunity A to B' " +
                            "where A is a number of Contact and B is a number of Opporunity.\n" +
                            "Type 'help' for more information.");
                }
                utils.promptEnterKey();
                utils.clearScreen();
                break;

            }
            case "contact": {
                try {
                    var contact = contactRepository.findById(Long.valueOf(option[2]));
                    var opportunity = opportunityRepository.findById(Long.valueOf(option[4]));
                    if (!option[3].toLowerCase().equals("to")){
                        System.out.println("Please put the command complete, for more information type 'help'.");
                        break;
                    }else if (opportunity.isEmpty()){
                        System.out.println("Please put a correct Nº of Opportunity." +
                                " For see all Opportunities type 'list opportunity'");
                        break;
                    }else if (contact.isEmpty()){
                        System.out.println("Please put a correct Nº of Contact." +
                                " For see all Contacts type 'list contact'");
                        break;
                    }else {
                        opportunity.get().setDecisionMaker(contact.get());
                        // Update opportunity with new Contact (setDecisionMaker() )
                        opportunityRepository.save(opportunity.get());
                        contact.get().setAccount(opportunity.get().getAccount());
                        contactRepository.save(contact.get());
                        System.out.println("Contact " + contact.get().getName() +
                                " assigned to opportunity Nº " + opportunity.get().getId());
                    }

                }catch (Exception e){
                    System.out.println("Error, format must be 'link opportunity A to B' " +
                            "where A is a number of Contact and B is a number of Opporunity.\n" +
                            "Type 'help' for more information.");
                }
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
