package com.ironhack.team6crm.service;

public class MenuConvert {
    public static void createNew(String option){
        switch (option) {
            case "lead": {
                System.out.println("Convert Lead");
                break;
            }
            default:{
                System.out.println("Need more actions!");
            }
        }

    }
}
