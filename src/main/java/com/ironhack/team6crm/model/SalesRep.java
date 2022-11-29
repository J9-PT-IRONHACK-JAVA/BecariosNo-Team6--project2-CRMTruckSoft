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
public class SalesRep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "salesRep")
    private List<Lead> lead;
    @OneToMany(mappedBy = "salesRep")
    private List<Contact> contact;
    @OneToMany(mappedBy = "salesRep")
    private List<Opportunity> opportunity;
    @OneToMany(mappedBy = "salesRep")
    private List<Account> account;

}
