package com.ironhack.team6crm.service;

import com.ironhack.team6crm.repository.AccountRepository;
import com.ironhack.team6crm.repository.ContactRepository;
import com.ironhack.team6crm.repository.LeadRepository;
import com.ironhack.team6crm.repository.OpportunityRepository;
import com.ironhack.team6crm.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuReport {

    private final LeadRepository leadRepository;
    private final OpportunityRepository opportunityRepository;
    private final AccountRepository accountRepository;
    private final ContactRepository contactRepository;
    private final SalesRepService salesRepService;
    private final Utils utils;

    public void reportsMenu(String[] options){

        switch (options[1]){
            case "lead":{
                var target = "Lead";
                if (options[3].equals("salesrep")) {
                    var segment = "SalesRep";
                    System.out.printf("Report: %s by %s \n",target,segment);
                    var results = leadRepository.countBySalesRep();
                    printReportResult(results);
                } else System.out.println("Invalid command!");
                break;
            }
            case "opportunity":{
                var target = "Opportunity";
                switch (options[3]) {
                    case "salesrep": {
                        var segment = "SalesRep";
                        System.out.printf("Report: %s by %s \n",target,segment);
                        var results = opportunityRepository.countBySalesRep();
                        printReportResult(results);
                        break;
                    }
                    case "product": {
                        var segment = "Product";
                        System.out.printf("Report: %s by %s \n",target,segment);
                        var results = opportunityRepository.countByProduct();
                        printReportResult(results);
                        break;
                    }
                    case "city": {
                        var segment = "City";
                        System.out.printf("Report: %s by %s \n",target,segment);
                        var results = opportunityRepository.countByCity();
                        printReportResult(results);
                        break;
                    }
                    case "country": {
                        var segment = "Country";
                        System.out.printf("Report: %s by %s \n",target,segment);
                        var results = opportunityRepository.countByCountry();
                        printReportResult(results);
                        break;
                    }
                    case "industry": {
                        var segment = "Industry";
                        System.out.printf("Report: %s by %s \n",target,segment);
                        var results = opportunityRepository.countByIndustry();
                        printReportResult(results);
                        break;
                    }
                    default :{
                        System.out.println("Invalid command!");
                    }
                }
                break;
            }
            case "closed_won":{
                var target = "Opportunities Closed-Won";
                switch (options[3]) {
                    case "salesrep": {
                        var segment = "SalesRep";
                        System.out.printf("Report: %s by %s \n",target,segment);
                        var results = opportunityRepository.countCloseWonBySalesRep();
                        printReportResult(results);
                        break;
                    }
                    case "product": {
                        var segment = "Product";
                        System.out.printf("Report: %s by %s \n",target,segment);
                        var results = opportunityRepository.countCloseWonByProduct();
                        printReportResult(results);
                        break;
                    }
                    case "city": {
                        var segment = "City";
                        System.out.printf("Report: %s by %s \n",target,segment);
                        var results = opportunityRepository.countCloseWonByCity();
                        printReportResult(results);
                        break;
                    }
                    case "country": {
                        var segment = "Country";
                        System.out.printf("Report: %s by %s \n",target,segment);
                        var results = opportunityRepository.countCloseWonByCountry();
                        printReportResult(results);
                        break;
                    }
                    case "industry": {
                        var segment = "Industry";
                        System.out.printf("Report: %s by %s \n",target,segment);
                        var results = opportunityRepository.countCloseWonByIndustry();
                        printReportResult(results);
                        break;
                    }
                    default :{
                        System.out.println("Invalid command!");
                    }
                }
                break;
            }
            case "closed_lost":{
                var target = "Opportunities Closed-Lost";
                switch (options[3]) {
                    case "salesrep": {
                        var segment = "SalesRep";
                        System.out.printf("Report: %s by %s \n", target, segment);
                        var results = opportunityRepository.countCloseLostBySalesRep();
                        printReportResult(results);
                        break;
                    }
                    case "product": {
                        var segment = "Product";
                        System.out.printf("Report: %s by %s \n", target, segment);
                        var results = opportunityRepository.countCloseLostByProduct();
                        printReportResult(results);
                        break;
                    }
                    case "city": {
                        var segment = "City";
                        System.out.printf("Report: %s by %s \n", target, segment);
                        var results = opportunityRepository.countCloseLostByCity();
                        printReportResult(results);
                        break;
                    }
                    case "country": {
                        var segment = "Country";
                        System.out.printf("Report: %s by %s \n", target, segment);
                        var results = opportunityRepository.countCloseLostByCountry();
                        printReportResult(results);
                        break;
                    }
                    case "industry": {
                        var segment = "Industry";
                        System.out.printf("Report: %s by %s \n", target, segment);
                        var results = opportunityRepository.countCloseLostByIndustry();
                        printReportResult(results);
                        break;
                    }
                    default: {
                        System.out.println("Invalid command!");
                    }
                }
                break;
            }
            case "open":{
                var target = "Opportunities Open";
                switch (options[3]) {
                    case "salesrep": {
                        var segment = "SalesRep";
                        System.out.printf("Report: %s by %s \n", target, segment);
                        var results = opportunityRepository.countOpenBySalesRep();
                        printReportResult(results);
                        break;
                    }
                    case "product": {
                        var segment = "Product";
                        System.out.printf("Report: %s by %s \n", target, segment);
                        var results = opportunityRepository.countOpenByProduct();
                        printReportResult(results);
                        break;
                    }
                    case "city": {
                        var segment = "City";
                        System.out.printf("Report: %s by %s \n", target, segment);
                        var results = opportunityRepository.countOpenByCity();
                        printReportResult(results);
                        break;
                    }
                    case "country": {
                        var segment = "Country";
                        System.out.printf("Report: %s by %s \n", target, segment);
                        var results = opportunityRepository.countOpenByCountry();
                        printReportResult(results);
                        break;
                    }
                    case "industry": {
                        var segment = "Industry";
                        System.out.printf("Report: %s by %s \n", target, segment);
                        var results = opportunityRepository.countOpenByIndustry();
                        printReportResult(results);
                        break;
                    }
                    default: {
                        System.out.println("Invalid command!");
                    }
                }
                break;
            }
            case "stats":{
                switch (options[2]) {
                    case "mean": {
                        var segment = "Mean";
                        switch (options[3]) {
                            case "employeecount" :{
                                var target = "Employee Count";
                                var results = accountRepository.averageEmployeeCount();
                                System.out.printf("Report: %s %s \n", segment, target);
                                System.out.println(results + "\n");
                                break;
                            }
                            case "productquantity" :{
                                var target = "Product Quantity";
                                var results = opportunityRepository.averageProductsPerOrder();
                                System.out.printf("Report: %s %s \n", segment, target);
                                System.out.println(results + "\n");
                                break;
                            }
                            case "oppsperaccount" :{
                                var target = "Opportunities per Account";
                                var results = opportunityRepository.averageOppsByAccount();
                                System.out.printf("Report: %s %s \n", segment, target);
                                System.out.println(results + "\n");
                                break;
                            }
                            default: {
                                System.out.println("Invalid command!");
                            }
                        }
                        break;
                    }
                    case "median": {
                        var segment = "Median";
                        var median = 0;
                        switch (options[3]) {
                            case "employeecount" :{
                                var target = "Employee Count";
                                var results = accountRepository.listEmployeeCount();
                                median = getMedian(results);
                                System.out.printf("Report: %s %s \n", segment, target);
                                System.out.println(median + "\n");
                                break;
                            }
                            case "productquantity" :{
                                var target = "Product Quantity";
                                var results = opportunityRepository.listQuantities();
                                median = getMedian(results);
                                System.out.printf("Report: %s %s \n", segment, target);
                                System.out.println(median + "\n");
                                break;
                            }
                            case "oppsperaccount" :{
                                var target = "Opportinities per Account";
                                var results = opportunityRepository.listOppsPerAccount();
                                median = getMedian(results);
                                System.out.printf("Report: %s %s \n", segment, target);
                                System.out.println(median + "\n");
                                break;
                            }
                            default: {
                                System.out.println("Invalid command!");
                            }
                        }
                        break;
                    }
                    case "max": {
                        var segment = "Max";
                        switch (options[3]) {
                            case "employeecount" :{
                                var target = "Employee Count";
                                var results = accountRepository.maxEmployeeCount();
                                System.out.printf("Report: %s %s \n", segment, target);
                                System.out.println(results + "\n");
                                break;
                            }
                            case "productquantity" :{
                                var target = "Product Quantity";
                                var results = opportunityRepository.maxProductsPerOrder();
                                System.out.printf("Report: %s %s \n", segment, target);
                                System.out.println(results + "\n");
                                break;
                            }
                            case "oppsperaccount" :{
                                var target = "Opportunities per Account";
                                var results = opportunityRepository.maxOppsByAccount();
                                System.out.printf("Report: %s %s \n", segment, target);
                                System.out.println(results + "\n");
                                break;
                            }
                            default: {
                                System.out.println("Invalid command!");
                            }
                        }
                        break;
                    }
                    case "min": {
                        var segment = "Min";
                        switch (options[3]) {
                            case "employeecount" :{
                                var target = "Employee Count";
                                var results = accountRepository.minEmployeeCount();
                                System.out.printf("Report: %s %s \n", segment, target);
                                System.out.println(results + "\n");
                                break;
                            }
                            case "productquantity" :{
                                var target = "Product Quantity";
                                var results = opportunityRepository.minProductsPerOrder();
                                System.out.printf("Report: %s %s \n", segment, target);
                                System.out.println(results + "\n");
                                break;
                            }
                            case "oppsperaccount" :{
                                var target = "Opportunities per Account";
                                var results = opportunityRepository.minOppsByAccount();
                                System.out.printf("Report: %s %s \n", segment, target);
                                System.out.println(results + "\n");
                                break;
                            }
                            default: {
                                System.out.println("Invalid command!");
                            }
                        }
                        break;
                    }
                }
                break;
            }
            default:{
                System.out.println("Invalid command!");
            }
        }
    }


    private void printReportResult(List<Object[]> results) {
        utils.clearScreen();
        System.out.printf("\n%-30s %s \n", "Item", "Count");
        System.out.println("------------------------------------------");
        for (Object[] result : results) {
            String name = result[0].toString();
            int count = Integer.parseInt(result[1].toString());
            System.out.printf("%-30s %s \n", name, count);
            //System.out.println(name + ": " + count);
        }
        System.out.println("\n");
    }

    private int getMedian(List<Integer> results) {
        int median;
        var rs = results.size();
        if (rs%2 == 1){
            median = results.get((rs+1)/2-1);
        } else {
            median = (results.get(rs/2-1)+ results.get(rs/2))/2;
        }
        return median;
    }
}
