package com.ironhack.team6crm.model;

import lombok.Data;

@Data
public class Opportunity {

    private int id;
    private Product product;
    private int quantity;
    private Contact decisionMaker;
    private Status status;


}
