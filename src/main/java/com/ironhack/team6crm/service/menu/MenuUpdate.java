package com.ironhack.team6crm.service.menu;

import com.ironhack.team6crm.model.*;
import com.ironhack.team6crm.repository.AccountRepository;
import com.ironhack.team6crm.repository.ContactRepository;
import com.ironhack.team6crm.repository.LeadRepository;
import com.ironhack.team6crm.repository.OpportunityRepository;
import com.ironhack.team6crm.service.SalesRepService;
import com.ironhack.team6crm.utils.ConsoleColors;
import com.ironhack.team6crm.utils.InputData;
import com.ironhack.team6crm.utils.UtilPrints;
import com.ironhack.team6crm.utils.Utils;
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
    private final MenuLookup lookUp;
    private final UtilPrints utilPrints;
    private final Utils utils;
    private final InputData inputData;

    public void updateMenu(String[] options, String[] optionsOriginalCase) throws Exception {
        var fieldData = optionsOriginalCase[4];
        if (optionsOriginalCase.length >5) {
            for (int i = 5; i < optionsOriginalCase.length; i++) {
                fieldData = fieldData.concat(" " + optionsOriginalCase[i]);
            }
        }

        switch (options[1]){
            case "lead":{
                Lead leadToUpdate;
                try {
                    leadToUpdate = leadRepository.findById(Long.valueOf(options[2])).orElseThrow(Exception::new);
                } catch (Exception e) {
                    utilPrints.printWithColor(options[2] + " is not a valid Lead #Id", ConsoleColors.RED);
                    utils.promptEnterKey();
                    break;
                }
                var updateOK = true;
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
                        if (utils.validateEmail(fieldData)) {
                            leadToUpdate.setEmail(fieldData);
                        } else {
                            utilPrints.printWithColor("Please type a valid email", ConsoleColors.RED);
                            leadToUpdate.setEmail(inputData.inputEmail("New email:"));
                        }
                        leadRepository.save(leadToUpdate);
                        break;
                    }
                    case "salesrep":{
                        var correctSalesRep = inputData.retrieveSalesRepByName(fieldData);
                        leadToUpdate.setSalesRep(correctSalesRep);
                        leadRepository.save(leadToUpdate);
                        break;
                    }
                    default :{
                        updateOK = false;
                        utilPrints.printInvalidCommand();
                    }
                }
                if (updateOK) lookUp.lookupMenu(new String[]{"lookup", "lead",leadToUpdate.getId().toString()});
                break;
            }
            case "opportunity":{
                Opportunity oppToUpdate;
                try {
                    oppToUpdate = opportunityRepository.findById(Long.valueOf(options[2])).orElseThrow(Exception::new);
                } catch (Exception e) {
                    utilPrints.printWithColor(options[2] + " is not a valid Opportunity #Id", ConsoleColors.RED);
                    utils.promptEnterKey();
                    break;
                }
                var updateOK = true;
                switch (options[3]) {
                    case "product": {
                        try {
                            oppToUpdate.setProduct(Product.valueOf(fieldData.toUpperCase()));
                        } catch(Exception e) {
                            utilPrints.printWithColor("Please choose a valid Product", ConsoleColors.RED);
                            oppToUpdate.setProduct(inputData.selectProduct());
                        }
                        opportunityRepository.save(oppToUpdate);
                        break;
                    }
                    case "quantity": {
                        try {
                            oppToUpdate.setQuantity(Integer.parseInt(fieldData));
                        } catch(Exception e) {
                            utilPrints.printWithColor("Please type a valid number", ConsoleColors.RED);
                            oppToUpdate.setQuantity(inputData.inputInteger("New Quantity:"));
                        }
                        opportunityRepository.save(oppToUpdate);
                        break;
                    }
                    case "decisionmaker": {
                        var correctContact = contactRepository.findByNameIgnoreCase(fieldData);
                        if (correctContact.isPresent()) {
                            oppToUpdate.setDecisionMaker(correctContact.get());
                            opportunityRepository.save(oppToUpdate);
                        } else {
                            System.out.println("Contact not found in DB. Please check name spelling and try again");
                            updateOK = false;
                            utils.promptEnterKey();
                        }
                        break;
                    }
                    case "salesrep":{
                        var correctSalesRep = inputData.retrieveSalesRepByName(fieldData);
                        oppToUpdate.setSalesRep(correctSalesRep);
                        opportunityRepository.save(oppToUpdate);
                        break;
                    }
                    case "status": {
                        try {
                            oppToUpdate.setStatus(Status.valueOf(fieldData.toUpperCase()));
                        } catch(Exception e) {
                            utilPrints.printWithColor("Please select a valid status", ConsoleColors.RED);
                            oppToUpdate.setStatus(inputData.selectStatus());
                        }
                        opportunityRepository.save(oppToUpdate);
                        break;
                    }
                    default :{
                        updateOK = false;
                        utilPrints.printInvalidCommand();
                    }
                }
                if (updateOK) lookUp.lookupMenu(new String[]{"lookup", "opportunity",oppToUpdate.getId().toString()});
                break;
            }
            case "account":{
                Account accountToUpdate;
                try {
                    accountToUpdate = accountRepository.findById(Long.valueOf(options[2])).orElseThrow(Exception::new);
                } catch (Exception e) {
                    utilPrints.printWithColor(options[2] + " is not a valid Account #Id", ConsoleColors.RED);
                    utils.promptEnterKey();
                    break;
                }
                var updateOK = true;

                switch (options[3]) {
                    case "companyname": {
                        accountToUpdate.setCompanyName(fieldData);
                        accountRepository.save(accountToUpdate);
                        break;
                    }
                    case "industry": {
                        try {
                            accountToUpdate.setIndustry(Industry.valueOf(fieldData.toUpperCase()));
                        } catch(Exception e) {
                            utilPrints.printWithColor("Please choose a valid industry:", ConsoleColors.RED);
                            accountToUpdate.setIndustry(inputData.selectIndustry());
                        }
                        accountRepository.save(accountToUpdate);
                        break;
                    }
                    case "employeecount": {
                        try {
                            accountToUpdate.setEmployeeCount(Integer.parseInt(fieldData));
                        } catch(Exception e) {
                            utilPrints.printWithColor("Please type a valid number", ConsoleColors.RED);
                            accountToUpdate.setEmployeeCount(inputData.inputInteger("New employee count"));
                        }
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
                    case "salesrep":{
                        var correctSalesRep = inputData.retrieveSalesRepByName(fieldData);
                        accountToUpdate.setSalesRep(correctSalesRep);
                        accountRepository.save(accountToUpdate);
                        break;
                    }
                    default :{
                        updateOK = false;
                        utilPrints.printInvalidCommand();
                    }
                }
                if (updateOK) lookUp.lookupMenu(new String[]{"lookup", "account",accountToUpdate.getId().toString()});
                break;
            }
            case "contact":{
                Contact contactToUpdate;
                try {
                    contactToUpdate = contactRepository.findById(Long.valueOf(options[2])).orElseThrow(Exception::new);
                } catch (Exception e) {
                    utilPrints.printWithColor(options[2] + " is not a valid Contact #Id", ConsoleColors.RED);
                    utils.promptEnterKey();
                    break;
                }
                var updateOK = true;

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
                        if (utils.validateEmail(fieldData)) {
                            contactToUpdate.setEmail(fieldData);
                        } else {
                            utilPrints.printWithColor("Please type a valid email", ConsoleColors.RED);
                            contactToUpdate.setEmail(inputData.inputEmail("New email:"));
                        }
                        contactRepository.save(contactToUpdate);
                        break;
                    }
                    case "salesrep":{
                        var correctSalesRep = inputData.retrieveSalesRepByName(fieldData);
                        contactToUpdate.setSalesRep(correctSalesRep);
                        contactRepository.save(contactToUpdate);
                        break;
                    }
                    default :{
                        updateOK = false;
                        utilPrints.printInvalidCommand();
                    }
                }
                if (updateOK) lookUp.lookupMenu(new String[]{"lookup", "contact",contactToUpdate.getId().toString()});
                break;
            }
            default:{
                utilPrints.printInvalidCommand();
            }
        }
    }

}