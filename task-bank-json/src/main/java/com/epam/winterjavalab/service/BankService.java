package com.epam.winterjavalab.service;

import com.epam.winterjavalab.beans.*;
import com.epam.winterjavalab.beans.enums.ShowType;
import com.epam.winterjavalab.constants.Constants;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BankService {
    private static final String DONE = "DONE";
    private static final String IN_PROGRESS = "IN PROGRESS";
    public BankService() {
    }


    public List<Credit> getCreditsBySettings(Bank bank, Settings settings) {

        Optional<ShowFor> showFor = Optional.ofNullable(settings.getShowFor());

        List<Credit> result = showFor.isPresent() ? getCreditByDateAndShowFor(bank,settings) : getAllCreditByDate(bank,settings);

        return result;

    }

    public List<Transaction> getTransactionsBySettings(Bank bank,List<Credit> credits, Settings settings) {
        List<Transaction> result = new ArrayList<>();

        for(Credit credit : credits) {
            List<Transaction> resultTmp =
                    bank.getTransactions()
                    .stream()
                    .filter(transaction -> transaction.getCreditId() == credit.getId())
                    .filter(transaction -> isInDate(transaction.getDate(),settings))
                    .collect(Collectors.toList());
            result.addAll(resultTmp);
        }
        return result;

    }
    public User getUserByCredit(Credit credit, List<User> users) {
        return users
                .stream()
                .filter(user -> credit.getUserId() == user.getId())
                .findFirst().orElse(new User());
    }
    public int getCreditDebt(Bank bank, Credit credit, Settings settings) {
        LocalDate dateTo = settings.getDateTo();
        List<Transaction> transactions = bank.getTransactions()
                .stream()
                .filter(transaction -> transaction.getCreditId() == credit.getId())
                .filter(transaction -> transaction.getDate().isEqual(dateTo) || transaction.getDate().isBefore(dateTo))
                .collect(Collectors.toList());

        int result = credit.getMoney();

        for(Transaction transaction : transactions ) {
            result = result - transaction.getMoney();
        }

        return result < 0 ? 0 : result;
    }
    public String getCreditStatus(Bank bank, Credit credit, Settings settings) {

        int debt = getCreditDebt(bank,credit,settings);

        return debt != 0 ? IN_PROGRESS : DONE ;
    }

    public boolean haveCreditUpToDate(Bank bank, User user, Settings settings) {
        boolean result = bank.getCredits()
                .stream()
                .anyMatch(credit -> (isInDate(credit.getDate(),settings) && credit.getUserId() == user.getId()));
        return  result;
    }

    private List<Credit> getAllCreditByDate(Bank bank, Settings settings) {
        List<Credit> result = bank.getCredits()
                .stream()
                .filter(credit -> isInDate(credit.getDate(),settings))
                .collect(Collectors.toList());

        return result;
    }
    private List<Credit> getCreditByDateAndShowFor(Bank bank, Settings settings) {
        List<Credit> creditsByDate = getAllCreditByDate(bank,settings);
        List<Credit> result = new ArrayList<>();
        ShowFor showFor = settings.getShowFor();
        if(showFor.getType().equals(ShowType.ID)) {
            List<String> idUsers = showFor.getUsers();
            for(String idString : idUsers) {
                int id = Integer.valueOf(idString);
                List<Credit> tmp = creditsByDate
                        .stream()
                        .filter(credit -> credit.getUserId() == id)
                        .collect(Collectors.toList());
                result.addAll(tmp);
            }
        }
        else {
            List<String> names = showFor.getUsers();
            for(String fullName : names) {
                String[] fullNameArray = fullName.split(Constants.SPACE);
                String name = fullNameArray[0];
                String secondName = fullNameArray[1];

                Optional<User> usertmp = bank.getUsers()
                        .stream()
                        .filter(user -> user.getName().equals(name) && user.getSecondName().equals(secondName))
                        .findFirst();
                List<Credit> tmp = creditsByDate
                        .stream()
                        .filter(credit -> usertmp.get().getId() == credit.getUserId())
                        .collect(Collectors.toList());
                result.addAll(tmp);
            }

        }
        return result;
    }

    public boolean isInDate(LocalDate date, Settings settings) {
        LocalDate dateFrom = settings.getDateFrom();
        LocalDate dateTo = settings.getDateTo();
        boolean result
                = date.isEqual(dateFrom) || date.isEqual(dateTo) || (date.isAfter(dateFrom) && date.isBefore(dateTo));

        return result;
    }

}
