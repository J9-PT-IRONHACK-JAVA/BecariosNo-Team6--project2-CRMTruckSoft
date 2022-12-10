package com.ironhack.team6crm.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private int employeeCount;
    private String city;
    private String country;
    @Enumerated(EnumType.STRING)
    private Industry industry;

    @OneToMany(mappedBy = "account")
    private List<Contact> contactList;

    @OneToMany(mappedBy = "account")
    private List<Opportunity> opportunityList;

    @ManyToOne
    @JoinColumn(name = "sales_rep_id")
    private SalesRep salesRep;

    public Account(String companyName, int employeeCount, String city, String country, Industry industry, SalesRep salesRep) {
        this.companyName = companyName;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
        this.industry = industry;
        this.salesRep = salesRep;
    }
}
