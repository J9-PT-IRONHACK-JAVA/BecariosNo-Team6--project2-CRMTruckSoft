package com.ironhack.team6crm.service.menu;

import com.ironhack.team6crm.model.*;
import com.ironhack.team6crm.repository.AccountRepository;
import com.ironhack.team6crm.repository.ContactRepository;
import com.ironhack.team6crm.repository.LeadRepository;
import com.ironhack.team6crm.repository.OpportunityRepository;
import com.ironhack.team6crm.utils.ConsoleColors;
import com.ironhack.team6crm.utils.InputData;
import com.ironhack.team6crm.utils.UtilPrints;
import com.ironhack.team6crm.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuConvert {

    private final LeadRepository leadRepository;
    private final OpportunityRepository opportunityRepository;
    private final AccountRepository accountRepository;
    private final ContactRepository contactRepository;
    private final MenuLookup lookUp;
    private final Utils utils;
    private final UtilPrints utilPrints;

    private final InputData inputData;

    public void convertMenu(String[] options) {
        if (options[1].equalsIgnoreCase("lead")) {
            try {
                var leadId = Long.valueOf(options[2]);
                var originLead = leadRepository.findById(leadId).orElseThrow(Exception::new);
                convertLead(originLead);
            } catch (Exception e) {
                utilPrints.printWithColor(options[2] + " is not a valid Lead #Id", ConsoleColors.RED);
            }
        } else utilPrints.printCommandIncomplete();
    }

    public void convertLead (Lead originLead) throws Exception {

        //Create the contact
        var contactFromLead = new Contact(originLead.getName(), originLead.getPhoneNumber(), originLead.getEmail(), Menu.currentUserLogged);
        var storedContactFromLead = contactRepository.save(contactFromLead);

        //Create the Opportunity
        var productSelected = inputData.selectProduct();
        var productUnits = inputData.inputInteger("Quantity:");

        var newOpportunity = new Opportunity(productSelected, productUnits, storedContactFromLead, Menu.currentUserLogged, Status.OPEN);
        var storedOpportunity = opportunityRepository.save(newOpportunity);

        //Create the Account
        utilPrints.printWithColor("\nPlease fill in some information about the Company, for the Account records: ", ConsoleColors.WHITE_BOLD_BRIGHT);
        var employeeCount = inputData.inputInteger("Employee count: ");
        List<String> accountData = inputData.getInputData("City: ", "Country: ");
        var industrySelected = inputData.selectIndustry();
        Account newAccount = new Account(originLead.getCompanyName(), employeeCount, accountData.get(0), accountData.get(1), industrySelected, Menu.currentUserLogged);
        var storedAccount = accountRepository.save(newAccount);

        //Link the Opportunity and Contact to the Account
        storedOpportunity.setAccount(storedAccount);
        opportunityRepository.save(storedOpportunity);
        storedContactFromLead.setAccount(storedAccount);
        contactRepository.save(storedContactFromLead);

        //Delete lead
        leadRepository.delete(originLead);

        //Confirm process and account created with related objects.
        utilPrints.printWithColor("\nLead converted successfully! \n", ConsoleColors.GREEN);

        utils.pause(1000);

        utilPrints.printWithColor("New Account with associated objects:", ConsoleColors.YELLOW);
        lookUp.lookupMenu(new String[]{"lookup", "account", storedAccount.getId().toString()});
    }

}
