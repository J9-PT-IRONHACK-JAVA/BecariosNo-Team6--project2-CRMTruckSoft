package com.ironhack.team6crm.utils;

import com.ironhack.team6crm.model.Account;
import com.ironhack.team6crm.model.Contact;
import com.ironhack.team6crm.model.Lead;
import com.ironhack.team6crm.model.Opportunity;
import com.ironhack.team6crm.repository.ContactRepository;
import com.ironhack.team6crm.repository.OpportunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UtilPrints {

    final ContactRepository contactRepository;
    final OpportunityRepository opportunityRepository;


    public void printLead(Lead lead){
        String nameOfSalesRep;
        try{
            nameOfSalesRep = lead.getSalesRep().getName();
        }catch (Exception e){
            nameOfSalesRep = "No SalesRep Assigned Yet";
        }
        System.out.println("*******************");
        System.out.println("Lead Nº "+lead.getId());
        System.out.println("Phone Number: "+lead.getPhoneNumber());
        System.out.println("Email: "+lead.getEmail());
        System.out.println("Company: "+lead.getCompanyName());
        System.out.println("User Sales: "+nameOfSalesRep);
        System.out.println("*******************");
    }

    public void printOpportunity(Opportunity opportunity) {
        System.out.println("*******************");
        System.out.println("Opportunity Nº "+opportunity.getId());
        System.out.println("Company Name: "+opportunity.getAccount().getCompanyName());
        System.out.println("Status: "+opportunity.getStatus().toString());
        System.out.println("------------");
        System.out.println("Product: "+opportunity.getProduct()+ " | Quantity :"+opportunity.getQuantity());
        System.out.println("Contact: "+opportunity.getDecisionMaker().getName());
        System.out.println("Contact Phone: "+opportunity.getDecisionMaker().getPhoneNumber());
        System.out.println("Contact Email: "+opportunity.getDecisionMaker().getEmail());
        System.out.println("------------");
        System.out.println("User Sales: "+opportunity.getSalesRep().getName());
        System.out.println("*******************");
    }

    public void printAccount(Account account) {
        System.out.println("*******************");
        System.out.println("Account Nº "+account.getId());
        System.out.println("Company Name: "+account.getCompanyName());
        System.out.println("Industry: "+account.getIndustry().toString());
        System.out.println("------------");
        System.out.println("Location: "+account.getCity()+" - "+account.getCountry().toUpperCase());
        System.out.println("Employees: "+account.getEmployeeCount());
        System.out.println("------------");
        System.out.println("Opportunities:");
        try {
            listOpportunitiesOfAccount(opportunityRepository.findAllByAccount_Id(account.getId()));
        }catch (Exception e){
            System.out.println("   - The account does not have Opportunities.");
        }
        System.out.println("------------");
        System.out.println("Contacts:");
        listContactsOfAccount(contactRepository.findAllByAccount_Id(account.getId()));
        System.out.println("------------");
        System.out.println("User Sales: "+account.getSalesRep().getName());
        System.out.println("*******************");
    }

    private void listContactsOfAccount(List<Contact> contactList) {
        for (Contact contact : contactList) {
            System.out.println("    - Contact Nº: " + contact.getId());
            System.out.println("    - Name: " + contact.getName());
            System.out.println("    - Contact Phone: " + contact.getPhoneNumber());
            System.out.println("    - Contact Email: " + contact.getEmail());
            System.out.println("");
        }
    }

    private void listOpportunitiesOfAccount(List<Opportunity> opportunityList) {
        for (Opportunity opportunity : opportunityList) {
            System.out.println("    - Opportunity Nº: " + opportunity.getId());
            System.out.println("    - Product: " + opportunity.getProduct());
            System.out.println("    - Quantity: " + opportunity.getQuantity());
            System.out.println("    - Status: " + opportunity.getStatus().toString() + "\n");
            System.out.println("");
        }
    }


    public void printContact(Contact contact) {
        String nameOfSalesRep;
        try{
            nameOfSalesRep = contact.getSalesRep().getName();
        }catch (Exception e){
            nameOfSalesRep = "No SalesRep Assigned Yet";
        }
        System.out.println("*******************");
        System.out.println("Contact Nº "+contact.getId());
        System.out.println("Name: "+contact.getName());
        System.out.println("Phone Number: "+contact.getPhoneNumber());
        System.out.println("Email: "+contact.getEmail());
        System.out.println("------------");
        System.out.println("User Sales: "+nameOfSalesRep);
        System.out.println("*******************");
    }

}
