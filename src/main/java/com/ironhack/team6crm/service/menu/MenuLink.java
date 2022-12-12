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

                    if (!option[3].equalsIgnoreCase("to")){
                        utilPrints.printCommandIncomplete();
                        break;
                    }else if (opportunity.isEmpty()){
                        utilPrints.printWithColor("Please type a correct Id# of Opportunity. " +
                                "To see all available Opportunities type 'list opportunities'", ConsoleColors.RED);
                        utils.promptEnterKey();
                        break;
                    }else if (account.isEmpty()){
                        utilPrints.printWithColor("Please type a correct Id# of Account. " +
                                "To see all available Accounts type 'list accounts'", ConsoleColors.RED);
                        utils.promptEnterKey();
                        break;
                    }else {
                        // Set the account to opportunity
                        opportunity.get().setAccount(account.get());
                        // Save opportunity
                        opportunityRepository.save(opportunity.get());
                        String message = "Opportunity Nº " + opportunity.get().getId() +
                                " assigned to Company "+account.get().getCompanyName()+
                                " (Account Nº " + account.get().getId() + ")";
                        utilPrints.printWithColor(message, ConsoleColors.GREEN);

                    }

                }catch (Exception e){
                    String message = "Error, format must be 'link opportunity A to B' " +
                            "where A is a number of Contact and B is a number of Account.\n" +
                            "Type 'help' for more information.";
                    utilPrints.printWithColor(message, ConsoleColors.RED);

                }
                utils.promptEnterKey();
                break;

            }
            case "contact": {
                try {
                    var contact = contactRepository.findById(Long.valueOf(option[2]));
                    var account = accountRepository.findById(Long.valueOf(option[4]));
                    if (!option[3].equalsIgnoreCase("to")){
                        utilPrints.printCommandIncomplete();
                        break;
                    }else if (account.isEmpty()){
                        utilPrints.printWithColor("Please type a correct Id# of Account. " +
                                "To see all available Accounts type 'list accounts'", ConsoleColors.RED);
                        utils.promptEnterKey();
                        break;
                    }else if (contact.isEmpty()){
                        utilPrints.printWithColor("Please type a correct Id# of Contact. " +
                                "To see all available Contacts type 'list contacts'", ConsoleColors.RED);
                        utils.promptEnterKey();
                        break;
                    }else {
                        // Set the account to contact
                        contact.get().setAccount(account.get());
                        contactRepository.save(contact.get());
                        String message ="Contact " + contact.get().getName() +
                                " assigned to Account number " + account.get().getId();
                        utilPrints.printWithColor(message, ConsoleColors.GREEN);
                    }

                }catch (Exception e){
                    String message ="Error, format must be 'link contact A to B' " +
                            "where A is a number of Contact and B is a number of Account.\n" +
                            "Type 'help' for more information.";
                    utilPrints.printWithColor(message, ConsoleColors.RED);
                }
                utils.promptEnterKey();
                break;
            }
            default:{
                utilPrints.printInvalidCommand();
            }
        }
    }
}
