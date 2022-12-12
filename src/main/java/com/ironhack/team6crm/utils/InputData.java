package com.ironhack.team6crm.utils;

import com.ironhack.team6crm.model.*;
import com.ironhack.team6crm.service.SalesRepService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
@Service
@RequiredArgsConstructor
public class InputData {

    private final UtilPrints utilPrints;
    private final Utils utils;
    private final Scanner scanner = new Scanner(System.in);
    private final SalesRepService salesRepService;

    public List<String> getInputData(String... questions) {
        List<String> inputData = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        for(String question: questions) {
            System.out.println(question);
            inputData.add(scanner.nextLine());
        }
        return inputData;
    }

    public String inputString(String request){
        System.out.println(request);
        return scanner.nextLine();
    }

    public Integer inputInteger(String request){
        Integer processedInteger = 0;
        do {
            System.out.println("\n" + request);
            var input = scanner.next().trim();
            try {
                processedInteger = Integer.parseInt(input);
            } catch (IllegalArgumentException e) {
                utilPrints.printWithColor("Please type a number", ConsoleColors.RED);
            }
        } while(processedInteger <= 0);
        return processedInteger;
    }

    public Integer inputIntegerWithRange(String request, int lowerNumber, int higherNumber){
        Integer processedInteger = 0;
        var flag = false;
        do {
            System.out.println("\n" + request);
            var input = scanner.next().trim();
            try {
                processedInteger = Integer.parseInt(input);
                flag = true;
            } catch (IllegalArgumentException e) {
                utilPrints.printWithColor("Please type a valid number", ConsoleColors.RED);
            }
            if(processedInteger < lowerNumber || processedInteger > higherNumber) flag=false;
        } while(!flag);
        return processedInteger;
    }

    public String inputEmail(String request){
        String input = "";
        do {
            System.out.println(request);
            input = scanner.next();
            if (utils.validateEmail(input));
            else utilPrints.printWithColor("Please type a valid email", ConsoleColors.RED);
        } while (!utils.validateEmail(input));
        return input;
    }

    public Product selectProduct() {
        System.out.println("\nWhich product is the client interested in?\n");
        for (Product prod : Product.values()){
            System.out.printf("%s - %s\n", prod.ordinal() , prod.name());
        }
        var productSelected = inputIntegerWithRange("Please pick your selection by number:", 0, Product.values().length);
        return Product.values()[productSelected];
    }

    public Industry selectIndustry() {
        System.out.println("\nWhich is the client's industry?\n");
        for (Industry ind : Industry.values()){
            System.out.printf("%s - %s\n", ind.ordinal() , ind.name());
        }
        var industrySelected = inputIntegerWithRange("Please pick your selection by number:", 0, Industry.values().length);
        return Industry.values()[industrySelected];

    }

    public Status selectStatus() {
        System.out.println("\nChoose a Status:\n");
        for (Status ind : Status.values()){
            System.out.printf("%s - %s\n", ind.ordinal() , ind.name());
        }
        var statusSelected = inputIntegerWithRange("Please pick your selection by number:", 0, Status.values().length);
        return Status.values()[statusSelected];

    }

    public SalesRep retrieveSalesRepByName(String name) {
        SalesRep correctSalesRep = null;
        var input = "";
        do {
            try {
                correctSalesRep = salesRepService.findByNameIgnoreCase(name).orElseThrow(Exception::new);
            } catch (Exception e) {
                utilPrints.printWithColor(name + "in not a valid SalesRep name", ConsoleColors.RED);
                var srList = salesRepService.findAll();
                System.out.println("\nChoose a SalesRep:\n");
                for (SalesRep sr : srList){
                    System.out.printf("%s - %s\n", sr.getId() , sr.getName());
                }
                correctSalesRep = srList.get(inputIntegerWithRange("Please pick your selection by number:", 0, srList.size()));
            }
        } while(correctSalesRep==null);
        return correctSalesRep;
    }

}
