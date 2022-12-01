package com.ironhack.team6crm.service;

public class MenuLookup {
    public static void lookupMenu(String[] options){
        switch (options[1]){
            case "lead":{
                System.out.println("Lookup lead");
                break;
            }
            case "opportunity":{
                System.out.println("Lookup opportunity");
                break;
            }
            case "account":{
                System.out.println("Lookup account");
                break;
            }
            case "contact":{
                System.out.println("Lookup contact");
                break;
            }
            default:{
                System.out.println("Please more info!");
            }
        }
    }
}
