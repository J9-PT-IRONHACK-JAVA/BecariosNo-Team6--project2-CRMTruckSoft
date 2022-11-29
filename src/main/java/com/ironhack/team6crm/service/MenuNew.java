package com.ironhack.team6crm.service;

import com.ironhack.team6crm.model.Lead;
import com.ironhack.team6crm.utils.InputData;

import java.util.List;

public class MenuNew {
    public static void createNew(String option){
        switch (option) {
            case "lead": {
                List<String> leadData = InputData.getInputData("name: \n", "phone number: \n", "email: \n", "company name: \n", "sales rep name: \n");
                System.out.println(leadData);
                Lead newLead= new Lead(leadData.get(0), )
                break;
            }
            case "opportunity": {
                List<String> opportunityData = InputData.getInputData("product: \n", "phone number: \n", "email: \n", "company name: \n", "sales rep name: \n");
                break;
            }
            case "account": {
                break;
            }
            case "contact": {
                break;
            }
        }

    }
}

