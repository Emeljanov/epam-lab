package com.epam.winterjavalab.beans;

import java.util.List;

public class Bank {
    private List<User> users;
    private List<Credit> credits;
    private List<Transaction> transactions;

    public Bank() {
    }

    public Bank(List<User> users, List<Credit> credits, List<Transaction> transactions) {
        this.users = users;
        this.credits = credits;
        this.transactions = transactions;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setCredits(List<Credit> credits) {
        this.credits = credits;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Credit> getCredits() {
        return credits;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addNewTransactions(List<Transaction> newTransactions) {
        transactions.addAll(newTransactions);
    }
}
