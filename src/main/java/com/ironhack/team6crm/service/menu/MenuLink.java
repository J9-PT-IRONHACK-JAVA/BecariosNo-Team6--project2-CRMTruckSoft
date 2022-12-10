package com.ironhack.team6crm.service.menu;

import com.ironhack.team6crm.repository.AccountRepository;
import com.ironhack.team6crm.repository.ContactRepository;
import com.ironhack.team6crm.repository.OpportunityRepository;
import com.ironhack.team6crm.utils.ConsoleColors;
import com.ironhack.team6crm.utils.UtilPrints;
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
    final UtilPrints utilPrints;
    final Utils utils;

    public void linkOpportunity(String[] option) throws IOException {
        switch (option[1]) {
            case "opportunity": {
                try {
                    var opportunity = opportunityRepository.findById(Long.valueOf(option[2]));
                    var account = accountRepository.findById(Long.valueOf(option[4]));

                    if (!option[3].toLowerCase().equals("to")){
                        String message = "Please put the command complete, for more information type 'help'.";
                        utilPrints.printWithColor(message, ConsoleColors.RED_BOLD);
                        utils.promptEnterKey();
                        utils.clearScreen();
                        break;
                    }else if (opportunity.isEmpty()){
                        String message = "Please put a correct Nº of Opportunity." +
                                " For see all Opportunities type 'list opportunity'";
                        utilPrints.printWithColor(message, ConsoleColors.RED_BOLD);
                        utils.promptEnterKey();
                        utils.clearScreen();
                        break;
                    }else if (account.isEmpty()){
                        String message = "Please put a correct Nº of Account." +
                                " For see all Accounts type 'list account'";
                        utilPrints.printWithColor(message, ConsoleColors.RED_BOLD);
                        utils.promptEnterKey();
                        utils.clearScreen();
                        break;
                    }else {
                        // Set the account to opportunity
                        opportunity.get().setAccount(account.get());
                        // Save opportunity
                        opportunityRepository.save(opportunity.get());
                        String message = "Opportunity Nº " + opportunity.get().getId() +
                                " assigned to Company "+account.get().getCompanyName()+
                                " (Account Nº " + account.get().getId() + ")";
                        utilPrints.printWithColor(message, ConsoleColors.GREEN_BOLD);

                    }

                }catch (Exception e){
                    String message = "Error, format must be 'link opportunity A to B' " +
                            "where A is a number of Contact and B is a number of Opportunity.\n" +
                            "Type 'help' for more information.";
                    utilPrints.printWithColor(message, ConsoleColors.RED_BOLD);

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
                        String message = "Please put the command complete, for more information type 'help'.";
                        utilPrints.printWithColor(message, ConsoleColors.RED_BOLD);
                        utils.promptEnterKey();
                        utils.clearScreen();
                        break;
                    }else if (account.isEmpty()){
                        String message ="Please put a correct Nº of Account." +
                                " For see all Opportunities type 'list account'";
                        utilPrints.printWithColor(message, ConsoleColors.RED_BOLD);
                        utils.promptEnterKey();
                        utils.clearScreen();
                        break;
                    }else if (contact.isEmpty()){
                        String message ="Please put a correct Nº of Contact." +
                                " For see all Contacts type 'list contact'";
                        utilPrints.printWithColor(message, ConsoleColors.RED_BOLD);
                        utils.promptEnterKey();
                        utils.clearScreen();
                        break;
                    }else {
                        // Set the account to contact
                        contact.get().setAccount(account.get());
                        contactRepository.save(contact.get());
                        String message ="Contact " + contact.get().getName() +
                                " assigned to Account Nº " + account.get().getId();
                        utilPrints.printWithColor(message, ConsoleColors.GREEN_BOLD);
                    }

                }catch (Exception e){
                    String message ="Error, format must be 'link contact A to B' " +
                            "where A is a number of Contact and B is a number of Account.\n" +
                            "Type 'help' for more information.";
                    utilPrints.printWithColor(message, ConsoleColors.RED_BOLD);
                }
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
