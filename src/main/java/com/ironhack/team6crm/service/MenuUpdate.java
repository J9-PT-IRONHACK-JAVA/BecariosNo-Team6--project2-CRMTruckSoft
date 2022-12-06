package com.ironhack.team6crm.service;

import com.ironhack.team6crm.model.Industry;
import com.ironhack.team6crm.model.Product;
import com.ironhack.team6crm.model.Status;
import com.ironhack.team6crm.repository.AccountRepository;
import com.ironhack.team6crm.repository.ContactRepository;
import com.ironhack.team6crm.repository.LeadRepository;
import com.ironhack.team6crm.repository.OpportunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuUpdate {

    private final LeadRepository leadRepository;
    private final OpportunityRepository opportunityRepository;
    private final AccountRepository accountRepository;
    private final ContactRepository contactRepository;
    private final SalesRepService salesRepService;

    public  void updateMenu(String[] options, String[] optionsOriginalCase) throws Exception {
        var fieldData = optionsOriginalCase[4];
        if (optionsOriginalCase.length >5) {
            for (int i = 5; i < optionsOriginalCase.length; i++) {
                fieldData = fieldData.concat(" " + optionsOriginalCase[i]);
            }
        }

        switch (options[1]){
            case "lead":{
                var leadToUpdate = leadRepository.findById(Long.valueOf(options[2])).orElseThrow(()-> new Exception("Lead not found in DB"));
                switch (options[3]){
                    case "name":{
                        leadToUpdate.setName(fieldData);
                        leadRepository.save(leadToUpdate);
                        break;
                    }
                    case "phonenumber":{
                        leadToUpdate.setPhoneNumber(fieldData);
                        leadRepository.save(leadToUpdate);
                        break;
                    }
                    case "companyname":{
                        leadToUpdate.setCompanyName(fieldData);
                        leadRepository.save(leadToUpdate);
                        break;
                    }
                    case "email":{
                        leadToUpdate.setEmail(fieldData);
                        leadRepository.save(leadToUpdate);
                        break;
                    }
                    case "sales rep":{
                        var correctSalesRep = salesRepService.findByNameIgnoreCase(fieldData).orElseThrow(()-> new Exception("SalesRep not found in DB"));
                        leadToUpdate.setSalesRep(correctSalesRep);
                        leadRepository.save(leadToUpdate);
                        break;
                    }
                    default :{
                        System.out.println("Invalid command!");
                    }
                }
                break;
            }
            case "opportunity":{
                var oppToUpdate = opportunityRepository.findById(Long.valueOf(options[2])).orElseThrow(()-> new Exception("Lead not found in DB"));
                switch (options[3]) {
                    case "product": {
                        oppToUpdate.setProduct(Product.valueOf(fieldData.toUpperCase()));
                        opportunityRepository.save(oppToUpdate);
                        break;
                    }
                    case "quantity": {
                        oppToUpdate.setQuantity(Integer.parseInt(fieldData));
                        opportunityRepository.save(oppToUpdate);
                        break;
                    }
                    case "decisionmaker": {
                        var correctContact = contactRepository.findByNameIgnoreCase(fieldData).orElseThrow(()-> new Exception("Contact not found in DB"));
                        oppToUpdate.setDecisionMaker(correctContact);
                        opportunityRepository.save(oppToUpdate);
                        break;
                    }
                    case "salesrep": {
                        var correctSalesRep = salesRepService.findByNameIgnoreCase(fieldData).orElseThrow(()-> new Exception("SalesRep not found in DB"));
                        oppToUpdate.setSalesRep(correctSalesRep);
                        opportunityRepository.save(oppToUpdate);
                        break;
                    }
                    case "status": {
                        oppToUpdate.setStatus(Status.valueOf(fieldData.toUpperCase()));
                        opportunityRepository.save(oppToUpdate);
                        break;
                    }
                    default :{
                        System.out.println("Invalid command!");
                    }
                }
                break;
            }
            case "account":{
                var accountToUpdate = accountRepository.findById(Long.valueOf(options[2])).orElseThrow(()-> new Exception("Lead not found in DB"));
                switch (options[3]) {
                    case "companyname": {
                        accountToUpdate.setCompanyName(fieldData);
                        accountRepository.save(accountToUpdate);
                        break;
                    }
                    case "industry": {
                        accountToUpdate.setIndustry(Industry.valueOf(fieldData));
                        accountRepository.save(accountToUpdate);
                        break;
                    }
                    case "employeecount": {
                        accountToUpdate.setEmployeeCount(Integer.parseInt(fieldData));
                        accountRepository.save(accountToUpdate);
                        break;
                    }
                    case "city": {
                        accountToUpdate.setCity(fieldData);
                        accountRepository.save(accountToUpdate);
                        break;
                    }
                    case "country": {
                        accountToUpdate.setCountry(fieldData);
                        accountRepository.save(accountToUpdate);
                        break;
                    }
                    case "salesrep": {
                        var correctSalesRep = salesRepService.findByNameIgnoreCase(fieldData).orElseThrow(() -> new Exception("SalesRep not found in DB"));
                        accountToUpdate.setSalesRep(correctSalesRep);
                        accountRepository.save(accountToUpdate);
                        break;
                    }
                    default :{
                        System.out.println("Invalid command!");
                    }
                }
                break;
            }
            case "contact":{
                var contactToUpdate = contactRepository.findById(Long.valueOf(options[2])).orElseThrow(()-> new Exception("Lead not found in DB"));
                switch (options[3]) {
                    case "name": {
                        contactToUpdate.setName(fieldData);
                        contactRepository.save(contactToUpdate);
                        break;
                    }
                    case "phonenumber":{
                        contactToUpdate.setPhoneNumber(fieldData);
                        contactRepository.save(contactToUpdate);
                        break;
                    }
                    case "email":{
                        contactToUpdate.setEmail(fieldData);
                        contactRepository.save(contactToUpdate);
                        break;
                    }
                    case "sales rep":{
                        var correctSalesRep = salesRepService.findByNameIgnoreCase(fieldData).orElseThrow(()-> new Exception("SalesRep not found in DB"));
                        contactToUpdate.setSalesRep(correctSalesRep);
                        contactRepository.save(contactToUpdate);
                        break;
                    }
                    default :{
                        System.out.println("Invalid command!");
                    }
                }
                break;
            }
            default:{
                System.out.println("Please more info!");
            }
        }
    }


}
