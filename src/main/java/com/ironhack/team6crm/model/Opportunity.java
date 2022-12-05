package com.ironhack.team6crm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Opportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Product product;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact decisionMaker;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "sales_rep_id")
    private SalesRep salesRep;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Opportunity(Product product, int quantity, Contact decisionMaker, Account account, SalesRep salesRep, Status status) {
        this.product = product;
        this.quantity = quantity;
        this.decisionMaker = decisionMaker;
        this.account = account;
        this.salesRep = salesRep;
        this.status = status;
    }
}
