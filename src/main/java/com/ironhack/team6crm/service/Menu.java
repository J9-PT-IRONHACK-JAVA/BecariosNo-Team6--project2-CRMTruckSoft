package com.ironhack.team6crm.service;

import com.ironhack.team6crm.model.SalesRep;
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

    private final SalesRepService salesRepService;
    private final Scanner scanner = new Scanner(System.in);
    static Long currentUserId = null;
    static SalesRep currentUserLogged = null;
    public void run(){
        while (currentUserId == null) {
            userSelectionRoutine();
            System.out.println("Welcome " + currentUserLogged.getName());
            loggedUserRoutine();
        }

    }
    private void loggedUserRoutine(){
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
                        menuNew.createNew(options[1], currentUserLogged);
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
                    }else {
                        menuList.listMenu(options);
                    }
                    break;
                }

                case "link": {
                    System.out.println("link");
                    break;
                }

                case "help": {
                    System.out.println("help");
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
    public void userSelectionRoutine() {
        var input = "";
        while (!input.equalsIgnoreCase("EXIT")) {
            System.out.println("Available users: ");
            var salesReps = salesRepService.findAll();
            for (SalesRep s : salesReps) {
                System.out.printf("%s - %s\n", s.getId(), s.getName());
            }
            System.out.println("Pick your salesRep, CREATE a new salesRep, or EXIT");
            input = scanner.nextLine();
            if (input.matches("\\d+")) {
                System.out.println("You picked an id");
                var selectedId = Long.parseLong(input);
                var salesRepFound = salesRepService.findById(selectedId);
                if (salesRepFound.isPresent()) {
                    System.out.println("Valid user picked");
                    currentUserId = selectedId;
                    currentUserLogged = salesRepFound.get();
                    break;
                } else {
                    System.out.println("Not a valid user selection");
                }
            }else if (input.equalsIgnoreCase("create")) {
                System.out.println("you want to create a salesRep");
                createUserRoutine();
            }
            else if (!input.equalsIgnoreCase("exit")) {
                System.out.println("Unrecognized command!");
            } else {
                System.exit(0);
            }
        }

    }
    private void createUserRoutine() {
        var input = "";
        while (!input.equalsIgnoreCase("BACK")) {
            System.out.println("Enter your name:");
            input = scanner.nextLine();
            if (input.isEmpty() || input.matches("\\d+")){
                System.out.println("Invalid input!");
            } else if(!input.equalsIgnoreCase("BACK")) {

                var salesRep = salesRepService.save(new SalesRep(input.trim().toLowerCase()));
                System.out.printf("Congrats! new SalesRep created with name: %s and id: %s\n", salesRep.getName(), salesRep.getId());
                break;
            }
        }
    }
}
