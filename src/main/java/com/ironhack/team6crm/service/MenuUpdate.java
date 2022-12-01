package com.ironhack.team6crm.service;

public class MenuUpdate {
    public static void updateMenu(String[] options){

        switch (options[1]){
            case "lead":{
                System.out.println("Update lead");
                break;
            }
            case "opportunity":{
                System.out.println("Update opportunity");
                break;
            }
            case "account":{
                System.out.println("Update account");
                break;
            }
            case "contact":{
                System.out.println("Update contact");
                break;
            }
            default:{
                System.out.println("Please more info!");
            }
        }
    }

}
