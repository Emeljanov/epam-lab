package com.epam.winterjavalab.beans;

import java.util.List;

public class TransactionsArray {
    private List<Transaction> transactions;

    public TransactionsArray() {
    }
    public TransactionsArray(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getList() {
        return transactions;
    }

    public void setList(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
