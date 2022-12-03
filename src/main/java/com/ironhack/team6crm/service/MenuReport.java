package com.ironhack.team6crm.service;

import com.ironhack.team6crm.repository.AccountRepository;
import com.ironhack.team6crm.repository.ContactRepository;
import com.ironhack.team6crm.repository.LeadRepository;
import com.ironhack.team6crm.repository.OpportunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MenuReport {

    private final LeadRepository leadRepository;
    private final OpportunityRepository opportunityRepository;
    private final AccountRepository accountRepository;
    private final ContactRepository contactRepository;

    public void reportsMenu(String[] options){

        switch (options[1]){
            case "lead":{
                var target = "Lead";
                if (options[3].equals("salesrep")) {
                    var segment = "SalesRep";
                    System.out.printf("Report: %s by %s",target,segment);
                    var results = leadRepository.countBySalesRep();
                    printReportResult(results);
                } else System.out.println("Invalid input");
                break;
            }
            case "opportunity":{
                var target = "Opportunity";
                switch (options[3]) {
                    case "salesrep": {
                        var segment = "SalesRep";
                        System.out.printf("Report: %s by %s",target,segment);
                        var results = opportunityRepository.countBySalesRep();
                        printReportResult(results);
                        break;
                    }
                    case "product": {
                        var segment = "Product";
                        System.out.printf("Report: %s by %s",target,segment);
                        var results = opportunityRepository.countByProduct();
                        printReportResult(results);
                        break;
                    }
                    case "city": {
                        var segment = "City";
                        System.out.printf("Report: %s by %s",target,segment);
                        var results = opportunityRepository.countByCity();
                        printReportResult(results);
                        break;
                    }
                    case "country": {
                        var segment = "Country";
                        System.out.printf("Report: %s by %s",target,segment);
                        var results = opportunityRepository.countByCountry();
                        printReportResult(results);
                        break;
                    }
                    case "industry": {
                        var segment = "Industry";
                        System.out.printf("Report: %s by %s",target,segment);
                        var results = opportunityRepository.countByIndustry();
                        printReportResult(results);
                        break;
                    }
                }
                break;
            }
            case "close-won":{
                var target = "Opportunities Close-Won";
                switch (options[3]) {
                    case "salesrep": {
                        var segment = "SalesRep";
                        System.out.printf("Report: %s by %s",target,segment);
                        var results = opportunityRepository.countCloseWonBySalesRep();
                        printReportResult(results);
                        break;
                    }
                    case "product": {
                        var segment = "Product";
                        System.out.printf("Report: %s by %s",target,segment);
                        var results = opportunityRepository.countCloseWonByProduct();
                        printReportResult(results);
                        break;
                    }
                    case "city": {
                    }
                    case "country": {
                    }
                    case "industry": {
                    }
                }
                break;
            }
            case "close-lost":{
                System.out.println("Report close-lost");
                break;
            }
            case "open":{
                System.out.println("Report open");
                break;
            }
            case "stats":{
                System.out.println("Report stats");
                break;
            }
            default:{
                System.out.println("Need More actions!");
            }
        }
    }

    private static void printReportResult(List<Object[]> results) {
        for (Object[] result : results) {
            String name = (String) result[0];
            int count = ((Number) result[1]).intValue();
            System.out.println(name + ": " + count);
        }
    }
}
