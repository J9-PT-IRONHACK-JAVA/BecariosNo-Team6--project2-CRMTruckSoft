package com.ironhack.team6crm.service;

import com.ironhack.team6crm.utils.ConsoleColors;
import com.ironhack.team6crm.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MenuHelp {

    private final Utils utils;

    public void help() throws IOException {
        utils.clearScreen();
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT +"""
                
                  ______ ______  ______     _______                   _           _           ___     \s
                 / _____|_____ \\|  ___ \\   (_______)                 | |         | |         / __)_   \s
                | /      _____) ) | _ | |   _        ____ _   _  ____| |  _       \\ \\   ___ | |__| |_ \s
                | |     (_____ (| || || |  | |      / ___) | | |/ ___) | / )       \\ \\ / _ \\|  __)  _)\s
                | \\_____      | | || || |  | |_____| |   | |_| ( (___| |< (    _____) ) |_| | |  | |__\s
                 \\______)     |_|_||_||_|   \\______)_|    \\____|\\____)_| \\_)  (______/ \\___/|_|   \\___)
                                                                                                      \s
                """);
        System.out.println("Version 1.0\n");
        utils.promptEnterKey();
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT +"**************************************");
        System.out.println("\nHELP MENU:");
        System.out.println("\n- 'help'");
        System.out.println("    - 'help' : Show info and help.");
        System.out.println("\n- 'new'");
        System.out.println("    - 'new lead' : Create new Lead.");
        System.out.println("    - 'new opportunity' : Create new Opportunity.");
        System.out.println("    - 'new account' : Create new Account.");
        System.out.println("    - 'new contact' : Create new Contact.");
        System.out.println("\n- 'lookup'");
        System.out.println("    - 'lookup lead X' : Lookup for Lead, X is the Nº of Lead.");
        System.out.println("    - 'lookup opportunity X' : Lookup for Opportunity, X is the Nº of Opportunity.");
        System.out.println("    - 'lookup account X' : Lookup for Account, X is the Nº of Account.");
        System.out.println("    - 'lookup contact X' : Lookup for Contact, X is the Nº of Contact.");
        System.out.println("\n- 'list'");
        System.out.println("    - 'list lead' : List of all Leads.");
        System.out.println("    - 'list opportunity' : List of all Opportunities.");
        System.out.println("    - 'list account' : List of all Accounts.");
        System.out.println("    - 'list contact' : List of all Contacts.");
        //utils.promptEnterKey();
        System.out.println("\n- 'update' VER AQUI!!! ***************");
        System.out.println("    - 'update lead X ' : aaaaaaaaaaaaaaaaaa.");
        System.out.println("    - 'update opportunity X ' : eeeeeeeeeeeeeeeee");
        System.out.println("    - 'update account X ' : iiiiiiiiiiiiiii.");
        System.out.println("    - 'update contact X ' : iooooooooooooooooooooo");
        System.out.println("\n- 'convert'");
        System.out.println("    - 'convert lead X ' : Create a New Opportunity from Lead Nº X.");
        System.out.println("\n- 'link'");
        System.out.println("    - 'link opportunity A to B' : Assign the Opportunity Nº A to Account Nº B.");
        System.out.println("    - 'link contact A to B ' : Assign the Contact Nº A to Account Nº B.");
        System.out.println("\n- 'report' VER AQUI!!! ***************");
        System.out.println("    - 'report lead X ' : xxxxxxxxxxxxxxxxxx");
        System.out.println("    - 'report opportunity X ' : yyyyyyyyyyyyyyy.");
        System.out.println("    - 'report close-won X ' : zzzzzzzzzzzzzzzzzz");
        System.out.println("    - 'report close-lost X ' : aaaaaaaaaaaaaa.");
        System.out.println("    - 'report open X ' : bbbbbbbbbbbbbb");
        System.out.println("    - 'report stats X ' : cccccccccc");

        System.out.println(ConsoleColors.BLUE + "\nFor support contact: admin@crmtrucksoft.com");
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "**************************************" + ConsoleColors.RESET);
        utils.promptEnterKey();
        utils.clearScreen();
    }

}
