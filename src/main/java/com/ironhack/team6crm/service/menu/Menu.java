package com.ironhack.team6crm.service.menu;

import com.ironhack.team6crm.service.SalesRepService;
import com.ironhack.team6crm.utils.ConsoleColors;
import com.ironhack.team6crm.utils.UtilPrints;
import com.ironhack.team6crm.utils.Utils;
import com.ironhack.team6crm.model.SalesRep;
import lombok.RequiredArgsConstructor;
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
    private final MenuConvert menuConvert;
    private final MenuUpdate updateMenu;
    private final UtilPrints utilPrints;
    private final SalesRepService salesRepService;
    private final Scanner scanner = new Scanner(System.in);
    static Long currentUserId = null;
    static SalesRep currentUserLogged = null;

    public void run() throws Exception {
        utils.clearScreen();
        utilPrints.printLogo();

        while (currentUserId == null) {
            userSelectionRoutine();
            utilPrints.printWithColor("\nWelcome " + currentUserLogged.getName(),ConsoleColors.WHITE_BOLD_BRIGHT);
            utils.pause(1000);
            loggedUserRoutine();
        }
    }
    private void loggedUserRoutine() throws Exception {

        String input;
        String[] options;
        String inputLowerCase;
        String[] optionsOriginalCase;
        do {
            utils.clearScreen();
            utilPrints.printWithColor("Insert command:", ConsoleColors.WHITE_BOLD_BRIGHT);

            input = scanner.nextLine().trim();
            inputLowerCase = input.toLowerCase();

            options = inputLowerCase.split(" ");
            optionsOriginalCase = input.split(" ");

            switch (options[0]) {
                case "lookup": {
                    if (options.length<3){
                        utilPrints.printCommandIncomplete();
                    }else {
                        menuLookup.lookupMenu(options);
                    }
                    break;
                }

                case "update": {
                    if (options.length<5){
                        utilPrints.printCommandIncomplete();
                    }else {
                        updateMenu.updateMenu(options, optionsOriginalCase);
                    }
                    break;
                }

                case "new": {
                    if(options.length < 2){
                        utilPrints.printCommandIncomplete();
                    } else {
                        menuNew.createNew(options[1]);
                    }
                    break;
                }
                case "report": {
                    if (options.length < 4){
                        utilPrints.printCommandIncomplete();
                    }else {
                        menuReport.reportsMenu(options);
                    }
                    break;
                }
                case "convert": {
                    menuConvert.convertMenu(options);
                    break;
                }

                case "list": {
                    if (options.length < 2){
                        utilPrints.printCommandIncomplete();
                    }else {
                        menuList.listMenu(options);
                    }
                    break;
                }

                case "link": {
                    if (options.length < 5){
                        utilPrints.printCommandIncomplete();
                    }else {
                        menuLink.linkOpportunity(options);
                    }
                    break;
                }

                case "help": {
                    menuHelp.help();
                    break;
                }

                case "exit": {
                    utilPrints.printWithColor("Goodbye!", ConsoleColors.WHITE_BOLD_BRIGHT);
                    System.exit(0);
                    break;
                }

                default: {
                    utilPrints.printCommandIncomplete();
                }
            }
        }while(!options[0].equals("exit"));

    }
    public void userSelectionRoutine() {
        var input = "";
        while (!input.equalsIgnoreCase("EXIT")) {
            utilPrints.printWithColor("Available users: ", ConsoleColors.WHITE_BOLD_BRIGHT);
            var salesReps = salesRepService.findAll();
            for (SalesRep s : salesReps) {
                System.out.printf(ConsoleColors.WHITE_BOLD_BRIGHT+"%s - %s\n"+ConsoleColors.RESET
                        , s.getId(), s.getName());
            }
            utilPrints.printWithColor("\nPick your salesRep, CREATE a new salesRep, or EXIT",
                    ConsoleColors.WHITE_BOLD_BRIGHT);
            input = scanner.nextLine();
            if (input.matches("\\d+")) {
                var selectedId = Long.parseLong(input);
                var salesRepFound = salesRepService.findById(selectedId);
                if (salesRepFound.isPresent()) {
                    currentUserId = selectedId;
                    currentUserLogged = salesRepFound.get();
                    break;
                } else {
                    utilPrints.printWithColor("Not a valid user selection", ConsoleColors.RED);
                    System.out.println();
                }
            }else if (input.equalsIgnoreCase("create")) {
                System.out.println("\nOK! Let's create a new salesRep:");
                createUserRoutine();
            }
            else if (!input.equalsIgnoreCase("exit")) {
                utilPrints.printWithColor("Unrecognized command!", ConsoleColors.RED);
            } else {
                utilPrints.printWithColor("Goodbye!", ConsoleColors.WHITE_BOLD_BRIGHT);
                System.exit(0);
            }
        }
    }
    private void createUserRoutine() {
        var input = "";
        while (!input.equalsIgnoreCase("BACK")) {
            System.out.println("\nEnter your name:");
            input = scanner.nextLine();
            if (input.isEmpty() || input.matches("\\d+")){
                utilPrints.printWithColor("Invalid input!", ConsoleColors.RED);
            } else if(!input.equalsIgnoreCase("BACK")) {

                var salesRep = salesRepService.save(new SalesRep(input.trim()));
                System.out.printf(ConsoleColors.GREEN_BOLD+
                                "\nCongrats! new SalesRep created with name: %s and id: %s\n\n"+
                                ConsoleColors.RESET,
                        salesRep.getName(), salesRep.getId());
                break;
            }
        }
    }
}
