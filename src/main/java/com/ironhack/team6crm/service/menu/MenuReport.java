package com.ironhack.team6crm.service.menu;

import com.ironhack.team6crm.repository.AccountRepository;
import com.ironhack.team6crm.repository.LeadRepository;
import com.ironhack.team6crm.repository.OpportunityRepository;
import com.ironhack.team6crm.utils.ConsoleColors;
import com.ironhack.team6crm.utils.UtilPrints;
import com.ironhack.team6crm.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuReport {

    private final LeadRepository leadRepository;
    private final OpportunityRepository opportunityRepository;
    private final AccountRepository accountRepository;

    private final Utils utils;
    private final UtilPrints utilPrints;

    public void reportsMenu(String[] options) {

        switch (options[1]){
            case "lead":{
                var target = "Lead";
                if (options[3].equals("salesrep")) {
                    var segment = "SalesRep";
                    printReportTitle(target, segment);
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
                        printReportTitle(target, segment);
                        var results = opportunityRepository.countBySalesRep();
                        printReportResult(results);
                        break;
                    }
                    case "product": {
                        var segment = "Product";
                        printReportTitle(target, segment);
                        var results = opportunityRepository.countByProduct();
                        printReportResult(results);
                        break;
                    }
                    case "city": {
                        var segment = "City";
                        printReportTitle(target, segment);
                        var results = opportunityRepository.countByCity();
                        printReportResult(results);
                        break;
                    }
                    case "country": {
                        var segment = "Country";
                        printReportTitle(target, segment);
                        var results = opportunityRepository.countByCountry();
                        printReportResult(results);
                        break;
                    }
                    case "industry": {
                        var segment = "Industry";
                        printReportTitle(target, segment);
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
                        printReportTitle(target, segment);
                        var results = opportunityRepository.countCloseWonBySalesRep();
                        printReportResult(results);
                        break;
                    }
                    case "product": {
                        var segment = "Product";
                        printReportTitle(target, segment);
                        var results = opportunityRepository.countCloseWonByProduct();
                        printReportResult(results);
                        break;
                    }
                    case "city": {
                        var segment = "City";
                        printReportTitle(target, segment);
                        var results = opportunityRepository.countCloseWonByCity();
                        printReportResult(results);
                        break;
                    }
                    case "country": {
                        var segment = "Country";
                        printReportTitle(target, segment);
                        var results = opportunityRepository.countCloseWonByCountry();
                        printReportResult(results);
                        break;
                    }
                    case "industry": {
                        var segment = "Industry";
                        printReportTitle(target, segment);
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
                        printReportTitle(target, segment);
                        var results = opportunityRepository.countCloseLostBySalesRep();
                        printReportResult(results);
                        break;
                    }
                    case "product": {
                        var segment = "Product";
                        printReportTitle(target, segment);
                        var results = opportunityRepository.countCloseLostByProduct();
                        printReportResult(results);
                        break;
                    }
                    case "city": {
                        var segment = "City";
                        printReportTitle(target, segment);
                        var results = opportunityRepository.countCloseLostByCity();
                        printReportResult(results);
                        break;
                    }
                    case "country": {
                        var segment = "Country";
                        printReportTitle(target, segment);
                        var results = opportunityRepository.countCloseLostByCountry();
                        printReportResult(results);
                        break;
                    }
                    case "industry": {
                        var segment = "Industry";
                        printReportTitle(target, segment);
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
                        printReportTitle(target, segment);
                        var results = opportunityRepository.countOpenBySalesRep();
                        printReportResult(results);
                        break;
                    }
                    case "product": {
                        var segment = "Product";
                        printReportTitle(target, segment);
                        var results = opportunityRepository.countOpenByProduct();
                        printReportResult(results);
                        break;
                    }
                    case "city": {
                        var segment = "City";
                        printReportTitle(target, segment);
                        var results = opportunityRepository.countOpenByCity();
                        printReportResult(results);
                        break;
                    }
                    case "country": {
                        var segment = "Country";
                        printReportTitle(target, segment);
                        var results = opportunityRepository.countOpenByCountry();
                        printReportResult(results);
                        break;
                    }
                    case "industry": {
                        var segment = "Industry";
                        printReportTitle(target, segment);
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
                                printStatsTitle(segment, target);
                                printStatsResult(results);
                                break;
                            }
                            case "productquantity" :{
                                var target = "Product Quantity";
                                var results = opportunityRepository.averageProductsPerOrder();
                                printStatsTitle(segment, target);
                                printStatsResult(results);
                                break;
                            }
                            case "oppsperaccount" :{
                                var target = "Opportunities per Account";
                                var results = opportunityRepository.averageOppsByAccount();
                                printStatsTitle(segment, target);
                                printStatsResult(results);
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
                                printStatsTitle(segment, target);
                                System.out.println(median + "\n");
                                break;
                            }
                            case "productquantity" :{
                                var target = "Product Quantity";
                                var results = opportunityRepository.listQuantities();
                                median = getMedian(results);
                                printStatsTitle(segment, target);
                                System.out.println(median + "\n");
                                break;
                            }
                            case "oppsperaccount" :{
                                var target = "Opportinities per Account";
                                var results = opportunityRepository.listOppsPerAccount();
                                median = getMedian(results);
                                printStatsTitle(segment, target);
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
                                printStatsTitle(segment, target);
                                printStatsResult(results);
                                break;
                            }
                            case "productquantity" :{
                                var target = "Product Quantity";
                                var results = opportunityRepository.maxProductsPerOrder();
                                printStatsTitle(segment, target);
                                printStatsResult(results);
                                break;
                            }
                            case "oppsperaccount" :{
                                var target = "Opportunities per Account";
                                var results = opportunityRepository.maxOppsByAccount();
                                printStatsTitle(segment, target);
                                printStatsResult(results);
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
                                printStatsTitle(segment, target);
                                printStatsResult(results);
                                break;
                            }
                            case "productquantity" :{
                                var target = "Product Quantity";
                                var results = opportunityRepository.minProductsPerOrder();
                                printStatsTitle(segment, target);
                                printStatsResult(results);
                                break;
                            }
                            case "oppsperaccount" :{
                                var target = "Opportunities per Account";
                                var results = opportunityRepository.minOppsByAccount();
                                printStatsTitle(segment, target);
                                printStatsResult(results);
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
                System.out.println(ConsoleColors.RED_BOLD+
                        "Please put the command complete, for more information type 'help'."+
                        ConsoleColors.RESET);
                utils.promptEnterKey();
                utils.clearScreen();            }
        }
    }


    private void printReportTitle(String target, String segment) {
        utilPrints.printWithColor(String.format("\nREPORT: %s by %s", target, segment), ConsoleColors.CYAN_BOLD);

    }

    private void printStatsTitle(String segment, String target) {
        utilPrints.printWithColor(String.format("\nREPORT: %s by %s \n", segment, target), ConsoleColors.CYAN_BOLD);
    }

    private void printReportResult(List<Object[]> results) {
        utilPrints.printWithColor(String.format("""
                        ===============================================
                        |%-30s |%10s     | 
                        ===============================================
                        """, "Item", "Count"), ConsoleColors.CYAN_BOLD);

        for (Object[] result : results) {
            String name = result[0].toString();
            int count = Integer.parseInt(result[1].toString());
            utilPrints.printWithColor(String.format("|%-30s |%10s     |", name, count), ConsoleColors.CYAN_BOLD);
        }
        utilPrints.printWithColor("===============================================", ConsoleColors.CYAN_BOLD);
        System.out.println("\n");
        utils.promptEnterKey();
    }

    private void printStatsResult(String results) {
        utilPrints.printWithColor(results + "\n", ConsoleColors.CYAN_BOLD);
        utils.promptEnterKey();
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
