package com.ironhack.team6crm.utils;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        System.out.println(ConsoleColors.BLUE+
                "Press ENTER to continue..."+
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
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

}
