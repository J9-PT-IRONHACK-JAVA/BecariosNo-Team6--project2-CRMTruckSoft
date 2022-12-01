package com.ironhack.team6crm.service;

import com.ironhack.team6crm.model.Lead;
import com.ironhack.team6crm.utils.InputData;

import java.util.List;

public class MenuLink {

    public static void createNew(String option){
        switch (option) {
            case "opportunity": {
                System.out.println("Link oportunity");
                break;
            }
            case "contact": {
                System.out.println("Link Contact");
                break;
            }
            default:{
                System.out.println("Need more actions!");
            }
        }

    }
}
