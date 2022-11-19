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
                        lookupMenu(options);
                    }
                    break;
                }

                case "update": {
                    System.out.println("update Menu");
                    break;
                }

                case "new": {
                    System.out.println("new Menu");
                    break;
                }
                case "report": {
                    System.out.println("report Menu");
                    break;
                }

                case "close": {
                    System.out.println("close");
                    break;
                }
                case "convert": {
                    System.out.println("convert");
                    break;
                }

                case "show": {
                    System.out.println("show");
                    break;
                }

                default: {
                    System.out.println("default");
                }
            }
        }while(!options[0].equals("exit"));


    }

    public static void lookupMenu(String[] object){

        switch (object[1]){
            case "lead":{
                System.out.println("lead");
                break;
            }
        }
    }

    public static void reportsMenu(String[] object){

        switch (object[1]){
            case "lead":{
                System.out.println("lead");
                break;
            }
        }
    }
}
