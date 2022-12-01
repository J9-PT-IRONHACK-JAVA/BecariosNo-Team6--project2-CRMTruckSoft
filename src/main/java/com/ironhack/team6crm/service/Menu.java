package com.ironhack.team6crm.service;

import java.util.Scanner;

public class Menu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void run(){

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
                        MenuLookup.lookupMenu(options);
                    }
                    break;
                }

                case "update": {
                    System.out.println("update Menu");
                    break;
                }

                case "new": {
                    if(options.length < 2){
                        System.out.println("Please insert the type");
                    } else {
                        MenuNew.createNew(options[1]);
                    }
                    break;
                }
                case "report": {
                    if (options.length < 3){
                    System.out.println("More information please!");
                    }else {
                    MenuReport.reportsMenu(options);
                     }
                    break;
                }

                case "convert": {
                    System.out.println("convert");
                    break;
                }

                case "list": {
                    System.out.println("list");
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
                    System.out.println("exit");
                    break;
                }

                default: {
                    System.out.println("default");
                }
            }
        }while(!options[0].equals("exit"));

    }
}


