package com.ironhack.team6crm.service;

import com.ironhack.team6crm.repository.AccountRepository;
import com.ironhack.team6crm.repository.ContactRepository;
import com.ironhack.team6crm.repository.LeadRepository;
import com.ironhack.team6crm.repository.OpportunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MenuLookup {
    private final LeadRepository leadRepository;
    private final OpportunityRepository opportunityRepository;
    private final AccountRepository accountRepository;
    private final ContactRepository contactRepository;
    public void lookupMenu(String[] options){
        switch (options[1]){
            case "lead":{
                System.out.println("Lookup lead Nº "+options[2]);
                System.out.println(leadRepository.findById(Long.valueOf(options[2])).toString());
                break;
            }
            case "opportunity":{
                System.out.println("Lookup Opportunity Nº "+options[2]);
                System.out.println(opportunityRepository.findById(Long.valueOf(options[2])).toString());
                break;
            }
            case "account":{
                System.out.println("Lookup Account Nº "+options[2]);
                System.out.println(accountRepository.findById(Long.valueOf(options[2])).toString());
                break;
            }
            case "contact":{
                System.out.println("Lookup Contact Nº "+options[2]);
                System.out.println(contactRepository.findById(Long.valueOf(options[2])).toString());
                break;
            }
            default:{
                System.out.println("Please more info!");
            }
        }
    }
}
