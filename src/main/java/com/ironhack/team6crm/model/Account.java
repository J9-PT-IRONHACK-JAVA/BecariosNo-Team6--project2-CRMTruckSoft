package com.ironhack.team6crm.model;


import lombok.Data;

import java.util.List;

@Data
public class Account {

    private int id;
    private Industry industry;
    private int employeeCount;
    private String city;
    private String country;
    private List<Contact> contactList;
    private List<Opportunity> opportunityList;


}
