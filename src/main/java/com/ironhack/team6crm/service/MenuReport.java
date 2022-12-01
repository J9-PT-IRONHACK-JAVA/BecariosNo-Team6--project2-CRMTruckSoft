package com.ironhack.team6crm.service;

public class MenuReport {
    public static void reportsMenu(String[] options){

        switch (options[1]){
            case "lead":{
                System.out.println("Report lead");
                break;
            }
            case "opportunity":{
                System.out.println("Report opportunity");
                break;
            }
            case "close-won":{
                System.out.println("Report close-won");
                break;
            }
            case "close-lost":{
                System.out.println("Report close-lost");
                break;
            }
            case "open":{
                System.out.println("Report open");
                break;
            }
            case "stats":{
                System.out.println("Report stats");
                break;
            }
            default:{
                System.out.println("Need More actions!");
            }
        }
    }
}
