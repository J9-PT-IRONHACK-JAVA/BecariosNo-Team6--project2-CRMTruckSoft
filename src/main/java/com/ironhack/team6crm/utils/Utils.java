package com.ironhack.team6crm.utils;

import com.ironhack.team6crm.model.Account;
import com.ironhack.team6crm.model.Contact;
import com.ironhack.team6crm.model.Lead;
import com.ironhack.team6crm.model.Opportunity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class Utils {

    public boolean isNumeric(String string){
        try {
            Integer.parseInt(string);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public void promptEnterKey() throws IOException {
        System.out.println("Press ENTER to continue...");
        try{
            System.in.read();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void clearScreen(){
        for (int i = 0; i < 15; i++) {
            System.out.println("\n");
        }
    }

}
