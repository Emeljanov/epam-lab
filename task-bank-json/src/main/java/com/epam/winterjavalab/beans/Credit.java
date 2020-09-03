package com.epam.winterjavalab.beans;

import com.epam.winterjavalab.beans.enums.Period;

import java.time.LocalDate;
import java.util.Date;

public class Credit {

        private int id;
        private int userId;
        private LocalDate date;
        private Period period;
        private int money;
        private double rate;

        public Credit() {
        }

        public Credit (int id, int userId, LocalDate date, Period period, int money, double rate) {
                this.id = id;
                this.userId = userId;
                this.date = date;
                this.period = period;
                this.money = money;
                this.rate = rate;
        }

        public void setId(int id) {
                this.id = id;
        }

        public void setUserId(int userId) {
                this.userId = userId;
        }

        public void setDate(LocalDate date) {
                this.date = date;
        }

        public void setPeriod(Period period) {
                this.period = period;
        }

        public void setMoney(int money) {
                this.money = money;
        }

        public void setRate(double rate) {
                this.rate = rate;
        }

        public int getId() {
                return id;
        }

        public int getUserId() {
                return userId;
        }

        public LocalDate getDate() {
                return date;
        }

        public Period getPeriod() {
                return period;
        }

        public int getMoney() {
                return money;
        }

        public double getRate() {
                return rate;
        }
}
