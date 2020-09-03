package com.epam.winterjavalab.service;

import com.epam.winterjavalab.beans.*;
import com.epam.winterjavalab.beans.enums.SortType;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PrinterService {
    private final static String STRING_FORMAT = "%11s | %11s |%25s|%25s|%12s| %7s | %15s |";
    private final static String NULL = "null";


    public void printResult(Bank bank, Settings settings) {
        BankService bankService = new BankService();
        List<Credit> credits = sortCredit(bankService.getCreditsBySettings(bank,settings),bank.getUsers(),settings);
        List<Transaction> transactions = bankService.getTransactionsBySettings(bank,credits,settings);
        printHeader();
        credits
                .forEach( credit -> {
                    User user = bankService.getUserByCredit(credit, bank.getUsers());
                    long numberTransactions = transactions
                            .stream()
                            .filter(x -> x.getCreditId() == credit.getId()).count();
                    int debt = bankService.getCreditDebt(bank,credit,settings);
                    System.out.printf(STRING_FORMAT,credit.getId(),user.getId(),user.getFullName(),numberTransactions,debt
                    ,credit.getPeriod(),bankService.getCreditStatus(bank,credit,settings));
                    System.out.println();
                });

        settings.getShowFor().getUsersObj(bank)
                .stream()
                .filter(user -> !bankService.haveCreditUpToDate(bank,user,settings))
                .forEach(user -> {
                    System.out.printf(STRING_FORMAT,NULL,user.getId(),user.getFullName(),NULL,NULL,NULL,NULL);
                    System.out.println();
                });

    }

    public List<Credit> sortCredit(List<Credit> creditList, List<User> users , Settings settings) {
        BankService bankService = new BankService();
        SortType sortBy = settings.getSortType();
        List<Credit> sortedList;
        if(sortBy == SortType.AGE) {
            sortedList = creditList
                    .stream()
                    .sorted(Comparator.comparing( x -> bankService.getUserByCredit(x,users).getBirthday()))
                    .collect(Collectors.toList());
        }
        else if(sortBy == SortType.NAME) {
            sortedList = creditList
                    .stream()
                    .sorted(Comparator.comparing(x -> bankService.getUserByCredit(x,users).getSecondName()))
                    .collect(Collectors.toList());
        }
        else {
            sortedList = creditList
                    .stream()
                    .sorted(Comparator.comparing(Credit::getMoney))
                    .collect(Collectors.toList());
        }
        return sortedList;
    }
    private static void printHeader() {
        final String HEADER_CREDIT_ID = "ID Credit";
        final String HEADER_USER_ID = "ID User";
        final String HEADER_FULL_NAME = "Full Name";
        final String HEADER_NUMBER_TRANSACTION = "Number Transactions";
        final String HEADER_DEBT = "DEBT";
        final String HEADER_PERIOD = "Period";
        final String HEADER_STATUS_DEBT = "Status DEBT";

        System.out.printf(STRING_FORMAT, HEADER_CREDIT_ID, HEADER_USER_ID, HEADER_FULL_NAME, HEADER_NUMBER_TRANSACTION,
                HEADER_DEBT, HEADER_PERIOD, HEADER_STATUS_DEBT);
        System.out.println();
    }

}
