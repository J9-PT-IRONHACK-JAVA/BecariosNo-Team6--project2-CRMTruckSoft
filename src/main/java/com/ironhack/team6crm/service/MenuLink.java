package com.ironhack.team6crm.service;

import com.ironhack.team6crm.repository.AccountRepository;
import com.ironhack.team6crm.repository.ContactRepository;
import com.ironhack.team6crm.repository.OpportunityRepository;
import com.ironhack.team6crm.utils.ConsoleColors;
import com.ironhack.team6crm.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
                        System.out.println(ConsoleColors.RED_BOLD+
                                "Please put the command complete, for more information type 'help'."+
                                ConsoleColors.RESET);
                        break;
                    }else if (opportunity.isEmpty()){
                        System.out.println(ConsoleColors.RED_BOLD+
                                "Please put a correct Nº of Opportunity." +
                                " For see all Opportunities type 'list opportunity'"+
                                ConsoleColors.RESET);
                        break;
                    }else if (account.isEmpty()){
                        System.out.println(ConsoleColors.RED_BOLD+
                                "Please put a correct Nº of Account." +
                                " For see all Accounts type 'list account'"+
                                ConsoleColors.RESET);
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

                        System.out.println(ConsoleColors.GREEN_BOLD+
                                "Opportunity Nº " + opportunity.get().getId() +
                                " assigned to Company "+account.get().getCompanyName()+
                                " (Account Nº " + account.get().getId() + ")"+
                                        ConsoleColors.RESET);
                    }

                }catch (Exception e){
                    System.out.println(ConsoleColors.RED_BOLD+
                            "Error, format must be 'link opportunity A to B' " +
                            "where A is a number of Contact and B is a number of Opportunity.\n" +
                            "Type 'help' for more information."+
                            ConsoleColors.RESET);
                }
                utils.promptEnterKey();
                utils.clearScreen();
                break;

            }
            case "contact": {
                try {
                    var contact = contactRepository.findById(Long.valueOf(option[2]));
                    var account = accountRepository.findById(Long.valueOf(option[4]));
                    if (!option[3].toLowerCase().equals("to")){
                        System.out.println(ConsoleColors.RED_BOLD+
                                "Please put the command complete, for more information type 'help'."+
                                ConsoleColors.RESET);
                        break;
                    }else if (account.isEmpty()){
                        System.out.println(ConsoleColors.RED_BOLD+
                                "Please put a correct Nº of Account." +
                                " For see all Opportunities type 'list account'"+
                                ConsoleColors.RESET);
                        break;
                    }else if (contact.isEmpty()){
                        System.out.println(ConsoleColors.RED_BOLD+
                                "Please put a correct Nº of Contact." +
                                " For see all Contacts type 'list contact'"+
                                ConsoleColors.RESET);
                        break;
                    }else {
                        // Get list of Contacts of this Account
                        var listOfContacts = contactRepository.findAllByAccount_Id(account.get().getId());
                        // Add the new contact
                        listOfContacts.add(contact.get());
                        // Update contacts lists with new Contact
                        account.get().setContactList(listOfContacts);
                        accountRepository.save(account.get());
                        contact.get().setAccount(account.get());
                        contactRepository.save(contact.get());
                        System.out.println(ConsoleColors.GREEN_BOLD+
                                "Contact " + contact.get().getName() +
                                " assigned to Account Nº " + account.get().getId()+
                                ConsoleColors.RESET);
                    }

                }catch (Exception e){
                    System.out.println(ConsoleColors.RED_BOLD+
                            "Error, format must be 'link contact A to B' " +
                            "where A is a number of Contact and B is a number of Account.\n" +
                            "Type 'help' for more information."+
                            ConsoleColors.RESET);
                }
                utils.promptEnterKey();
                utils.clearScreen();
                break;
            }
            default:{
                System.out.println(ConsoleColors.RED_BOLD+
                        "Please put the command complete, for more information type 'help'."+
                        ConsoleColors.RESET);
                utils.promptEnterKey();
                utils.clearScreen();
            }
        }

    }
}
