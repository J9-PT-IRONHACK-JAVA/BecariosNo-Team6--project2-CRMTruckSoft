package com.ironhack.team6crm.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputData {
    public static List<String> getInputData(String... questions) {
        Scanner scanner = new Scanner(System.in);
        List<String> inputData = new ArrayList<>();
        for(String question: questions) {
            System.out.println(question);
            inputData.add(scanner.nextLine());
        }
        return inputData;
    }
}
