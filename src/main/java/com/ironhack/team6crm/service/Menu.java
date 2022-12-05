package com.ironhack.team6crm.service;

import com.ironhack.team6crm.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;
@RequiredArgsConstructor
@Service
public class Menu {

    private final MenuList menuList;
    private final MenuLookup menuLookup;
    private final MenuNew menuNew;
    private final MenuReport menuReport;
    private final MenuLink menuLink;
    private final Utils utils;
    private final MenuHelp menuHelp;
    private final Scanner scanner = new Scanner(System.in);

    public void run() throws Exception {

        String input;
        String[] options;
        do {
            System.out.println("Welcome!");
            System.out.println("insert command:");

            input = scanner.nextLine().trim().toLowerCase();

            options = input.split(" ");

            switch (options[0]) {
                case "lookup": {
                    if (options.length<3){
                        System.out.println("More information please!");
                        utils.promptEnterKey();
                        utils.clearScreen();
                    }else {
                        menuLookup.lookupMenu(options);
                    }
                    break;
                }

                case "update": {
                    if (options.length<5){
                        System.out.println("More information please!");
                    }else {
                        MenuUpdate.updateMenu(options);
                    }
                    break;
                }

                case "new": {
                    if(options.length < 2){
                        System.out.println("Please insert the type");
                    } else {
                        menuNew.createNew(options[1]);
                    }
                    break;
                }
                case "report": {
                    if (options.length < 4){
                        System.out.println("More information please!");
                    }else {
                        menuReport.reportsMenu(options);
                    }
                    break;
                }
                case "convert": {
                    System.out.println("convert");
                    break;
                }

                case "list": {
                    if (options.length < 2){
                        System.out.println("More information please!");
                        utils.promptEnterKey();
                        utils.clearScreen();
                    }else {
                        menuList.listMenu(options);
                    }
                    break;
                }

                case "link": {
                    if (options.length < 5){
                        System.out.println("More information please!");
                        utils.promptEnterKey();
                        utils.clearScreen();
                    }else {
                        menuLink.linkOpportunity(options);
                    }
                    break;
                }

                case "help": {
                    menuHelp.help();
                    break;
                }

                //Show all options
                case "menu": {
                    System.out.println("menu");
                    break;
                }

                case "exit": {
                    System.out.println("Goodbye!");
                    break;
                }

                default: {
                    System.out.println("Error in command! Please write 'help' to see all commands...");
                }
            }
        }while(!options[0].equals("exit"));

    }
}
