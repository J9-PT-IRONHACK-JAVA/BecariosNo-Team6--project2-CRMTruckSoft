package com.ironhack.team6crm.service;

import com.ironhack.team6crm.utils.ConsoleColors;
import com.ironhack.team6crm.utils.UtilPrints;

import com.ironhack.team6crm.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MenuHelp {

    private final Utils utils;
    private final UtilPrints utilPrints;

    public void help() throws IOException {
        utils.clearScreen();
        utilPrints.printWithColor("""
                      ___       __ \s
                |__| |__  |    |__)\s
                |  | |___ |___ |   \s
                                  \s
                """, ConsoleColors.PURPLE_BOLD);

        System.out.println("CRM Truck Soft. - Version 1.0\n");
        utils.promptEnterKey();
        System.out.println("**************************************");
        System.out.println("\nAVAILABLE COMMANDS:");

        System.out.println("\n- 'help'");
        System.out.println("    - 'help' : Show info and help.");
        System.out.println("\n- 'new'");
        System.out.println("    - 'new lead' : Create new Lead.");
        System.out.println("    - 'new opportunity' : Create new Opportunity.");
        System.out.println("    - 'new account' : Create new Account.");
        System.out.println("    - 'new contact' : Create new Contact.");
        System.out.println("\n- 'lookup'");
        System.out.println("    - 'lookup lead X' : Lookup for Lead, where X is the Id# of Lead.");
        System.out.println("    - 'lookup opportunity X' : Lookup for Opportunity, where X is the Id# of Opportunity.");
        System.out.println("    - 'lookup account X' : Lookup for Account, where X is the Id# of Account.");
        System.out.println("    - 'lookup contact X' : Lookup for Contact, where X is the Id# of Contact.");
        System.out.println("\n- 'list'");
        System.out.println("    - 'list lead' : List of all Leads.");
        System.out.println("    - 'list opportunity' : List of all Opportunities.");
        System.out.println("    - 'list account' : List of all Accounts.");
        System.out.println("    - 'list contact' : List of all Contacts.");

        utils.promptEnterKey();
        System.out.println("\n- 'update'");
        System.out.println("    The command works like this: 'update %object% #Id %property% %new value%'");
        System.out.println("             ** example1: 'update lead 2 email mike@optimusprime.com' **");
        System.out.println("             ** example2: 'update opportunity 1 status Close_Won.' **");
        System.out.println("             ** example3: 'update account 4 companyName Optimus Trucks Ltd.' **");
        System.out.println("    - 'update lead X %property% %new value%' : update the %property% of the Lead #X to the %value% given.");
        System.out.println("    - 'update opportunity X %property% %new value%' : update the %property% of the Opportunity #X to the %value% given.");
        System.out.println("    - 'update account X %property% %new value%' : update the %property% of the Account #X to the %value% given.");
        System.out.println("    - 'update contact X %property% %new value%' : update the %property% of the Contact #X to the %value% given.");
        System.out.println("\n- 'convert'");
        System.out.println("    - 'convert lead X' : Convert Lead X (where X is the Id#) into a new Opportunity and new Contact. Then create a new Account with the associated Opportunity and Contact.");
        System.out.println("\n- 'link'");
        System.out.println("    - 'link opportunity A to B' : Assign the Opportunity # A to Account # B.");
        System.out.println("    - 'link contact A to B' : Assign the Contact # A to Account # B.");
        System.out.println("\n- 'report' ");
        System.out.println("    - 'report lead by SalesRep' : A count of Leads by SalesRep");
        System.out.println("    - 'report opportunity by X' : A count of all Opportunities by X, where 'X' can be either SalesRep, Product, City, Country or Industry");
        System.out.println("    - 'report close_won X ' : A count of all Close_Won Opportunities by X, where 'X' can be either SalesRep, Product, City, Country or Industry");
        System.out.println("    - 'report close_lost X ' : A count of all Close_Lost Opportunities by X, where 'X' can be either SalesRep, Product, City, Country or Industry.");
        System.out.println("    - 'report open X ' : A count of all Open Opportunities by X, where 'X' can be either SalesRep, Product, City, Country or Industry.");
        System.out.println("    - 'report stats X employeeCount' : The X number of employees in the client companies (Accounts), where 'X' can be either mean, median, max or min.");
        System.out.println("    - 'report stats X productQuantity' : The X quantity of Product of all our Opportunities, where 'X' can be either mean, median, max or min.");
        System.out.println("    - 'report stats X oppsPerAccount' : The X number of opportunities linked to each of our client Accounts, where 'X' can be either mean, median, max or min.");


        System.out.println(ConsoleColors.BLUE + "\nFor support contact: admin@crmtrucksoft.com");
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "**************************************" + ConsoleColors.RESET);
        utils.promptEnterKey();
        utils.clearScreen();
    }

}