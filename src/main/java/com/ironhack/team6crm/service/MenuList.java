package com.ironhack.team6crm.service;

public class MenuList {
    public static void listMenu(String[] options) {
        switch (options[1]){
            case "lead":{
                System.out.println("List lead");
                break;
            }
            case "opportunity":{
                System.out.println("List opportunity");
                break;
            }
            case "account":{
                System.out.println("List account");
                break;
            }
            case "contact":{
                System.out.println("List contact");
                break;
            }
            default:{
                System.out.println("Please more info!");
            }
        }
    }
}
