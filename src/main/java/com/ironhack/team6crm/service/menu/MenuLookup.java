package com.ironhack.team6crm.service.menu;

import com.ironhack.team6crm.repository.*;
import com.ironhack.team6crm.utils.ConsoleColors;
import com.ironhack.team6crm.utils.UtilPrints;
import com.ironhack.team6crm.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class MenuLookup {
    private final LeadRepository leadRepository;
    private final OpportunityRepository opportunityRepository;
    private final AccountRepository accountRepository;
    private final ContactRepository contactRepository;
    private final Utils utils;
    private final UtilPrints utilPrints;
    public void lookupMenu(String[] options) {
        switch (options[1]) {
            case "lead": {
                if (utils.isNumeric(options[2])) {
                    try {
                        var lead = leadRepository.findById(
                                Long.valueOf(options[2])).orElseThrow(
                                NoSuchElementException::new);
                        utilPrints.printLead(lead);
                    } catch (Exception e) {
                        String message = "Error ID Nº" + options[2] + "  does not exist. Try again with another ID...";
                        utilPrints.printWithColor(message,ConsoleColors.RED_BOLD);
                    }
                } else {
                    utilPrints.printWithColor("The ID must be Number",ConsoleColors.RED_BOLD);
                }
                utils.promptEnterKey();
                utils.clearScreen();
                break;
            }
            case "opportunity": {
                if (utils.isNumeric(options[2])) {
                    try {
                        var opportunity = opportunityRepository.findById(
                                Long.valueOf(options[2])).orElseThrow(
                                NoSuchElementException::new);
                        utilPrints.printOpportunity(opportunity);
                    } catch (Exception e) {
                        String message = "Error ID Nº" + options[2] + "  does not exist. Try again with another ID...";
                        utilPrints.printWithColor(message,ConsoleColors.RED_BOLD);
                    }
                } else {
                    utilPrints.printWithColor("The ID must be Number",ConsoleColors.RED_BOLD);
                }
                utils.promptEnterKey();
                utils.clearScreen();
                break;
            }
            case "account": {
                if (utils.isNumeric(options[2])) {
                    try {
                        var account = accountRepository.findById(
                                Long.valueOf(options[2])).orElseThrow(
                                NoSuchElementException::new);
                        utilPrints.printAccount(account);
                    } catch (Exception e) {
                        String message = "Error ID Nº" + options[2] + "  does not exist. Try again with another ID...";
                        utilPrints.printWithColor(message,ConsoleColors.RED_BOLD);
                    }
                } else {
                    utilPrints.printWithColor("The ID must be Number",ConsoleColors.RED_BOLD);
                }
                utils.promptEnterKey();
                utils.clearScreen();
                break;
            }
            case "contact": {
                if (utils.isNumeric(options[2])) {
                    try {
                        var contact = contactRepository.findById(
                                Long.valueOf(options[2])).orElseThrow(
                                NoSuchElementException::new);
                        utilPrints.printContact(contact);
                    } catch (Exception e) {
                        String message = "Error ID Nº" + options[2] + "  does not exist. Try again with another ID...";
                        utilPrints.printWithColor("The ID must be Number",ConsoleColors.RED_BOLD);
                    }
                } else {
                    utilPrints.printWithColor("The ID must be Number",ConsoleColors.RED_BOLD);
                }
                utils.promptEnterKey();
                utils.clearScreen();
                break;
            }
            default: {
                utilPrints.printWithColor("Please put the command complete, for more information type 'help'.",
                        ConsoleColors.RED_BOLD);
                utils.promptEnterKey();
                utils.clearScreen();
            }
        }
    }
}
