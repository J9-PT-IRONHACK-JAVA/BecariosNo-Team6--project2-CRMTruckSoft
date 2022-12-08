package com.ironhack.team6crm.service;

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
    private final SalesRepRepository salesRepRepository;

    private final Utils utils;
    private final UtilPrints utilPrints;
    public void lookupMenu(String[] options) throws Exception {
        switch (options[1]) {
            case "lead": {
                if (utils.isNumeric(options[2])) {
                    try {
                        var lead = leadRepository.findById(
                                Long.valueOf(options[2])).orElseThrow(
                                NoSuchElementException::new);
                        utilPrints.printLead(lead);
                    } catch (Exception e) {
                        System.out.println(ConsoleColors.RED_BOLD+
                                "Error ID Nº" + options[2] + "  does not exist. Try again with another ID..."+
                                ConsoleColors.RESET);
                    }
                } else {
                    System.out.println(ConsoleColors.RED_BOLD+"The ID must be Number"+ConsoleColors.RESET);
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
                        System.out.println(ConsoleColors.RED_BOLD+
                                "Error ID Nº" + options[2] + "  does not exist. Try again with another ID..."+
                                ConsoleColors.RESET);
                    }
                } else {
                    System.out.println(ConsoleColors.RED_BOLD+"The ID must be Number"+ConsoleColors.RESET);
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
                        System.out.println(ConsoleColors.RED_BOLD+
                                "Error ID Nº" + options[2] + "  does not exist. Try again with another ID..."+
                                ConsoleColors.RESET);
                    }
                } else {
                    System.out.println(ConsoleColors.RED_BOLD+"The ID must be Number"+ConsoleColors.RESET);
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
                        System.out.println(ConsoleColors.RED_BOLD+
                                "Error ID Nº" + options[2] + "  does not exist. Try again with another ID..."+
                                ConsoleColors.RESET);
                    }
                } else {
                    System.out.println(ConsoleColors.RED_BOLD+"The ID must be Number"+ConsoleColors.RESET);
                }
                utils.promptEnterKey();
                utils.clearScreen();
                break;
            }
            default: {
                System.out.println(ConsoleColors.RED_BOLD+
                        "Please put the command complete, for more information type 'help'."+
                        ConsoleColors.RESET);
                utils.promptEnterKey();
                utils.clearScreen();
            }
        }
    }
}
