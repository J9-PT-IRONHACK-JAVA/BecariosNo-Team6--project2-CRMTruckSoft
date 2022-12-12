package com.ironhack.team6crm.utils;

import com.ironhack.team6crm.repository.AccountRepository;
import com.ironhack.team6crm.repository.ContactRepository;
import com.ironhack.team6crm.repository.OpportunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class Utils {

    private final AccountRepository accountRepository;
    private final ContactRepository contactRepository;
    private final OpportunityRepository opportunityRepository;

    public boolean isNumeric(String string){
        try {
            Integer.parseInt(string);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public void promptEnterKey() {
        System.out.println(ConsoleColors.BLUE+
                "\nPress ENTER to continue..."+
                ConsoleColors.RESET);
        try{
            System.in.read();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void clearScreen(){
        for (int i = 0; i < 20; i++) {
            System.out.println("\n");
        }
    }

    public void pause(int milis){
        milis = Math.min(milis,5000);
        try{
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted");
        }
    }
    public final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public void simulateLinks() {
        var loadedAccounts = accountRepository.findAll();
        var loadedContacts = contactRepository.findAll();
        var loadedOpportunities = opportunityRepository.findAll();

        for (int i = 0; i < loadedContacts.size(); i++) {
            loadedContacts.get(i).setAccount(loadedAccounts.get(i));
        }
        contactRepository.saveAll(loadedContacts);

        for (int i = 0; i < loadedOpportunities.size(); i++) {
            loadedOpportunities.get(i).setAccount(loadedAccounts.get(i));
        }
        opportunityRepository.saveAll(loadedOpportunities);
    }

}
