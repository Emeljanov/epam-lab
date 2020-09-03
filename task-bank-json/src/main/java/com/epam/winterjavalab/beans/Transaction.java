package com.epam.winterjavalab.beans;
import java.time.LocalDate;

public class Transaction {
    private int id;
    private LocalDate date;
    private int creditId;
    private int money;

    public Transaction() {
    }

    public Transaction(int id, LocalDate date, int creditId, int money) {
        this.id = id;
        this.date = date;
        this.creditId = creditId;
        this.money = money;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCreditId(int creditId) {
        this.creditId = creditId;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getCreditId() {
        return creditId;
    }

    public int getMoney() {
        return money;
    }

}
