package com.ironhack.team6crm.service;

public class MenuReport {
    public static void reportsMenu(String[] object){

        switch (object[1]){
            case "lead":{
                System.out.println("lead");
                break;
            }
            case "opportunity":{
                System.out.println("opportunity");
                break;
            }
            case "close-won":{
                System.out.println("close-won");
                break;
            }
            case "close-lost":{
                System.out.println("close-lost");
                break;
            }
            case "open":{
                System.out.println("open");
                break;
            }
            case "stats":{
                System.out.println("stats");
                break;
            }
            default:{
                System.out.println("Need More actions!");
            }
        }
    }
}
