package com.epam.winterjavalab;

import com.epam.winterjavalab.beans.Bank;
import com.epam.winterjavalab.beans.Settings;
import com.epam.winterjavalab.dao.BankCreater;
import com.epam.winterjavalab.dao.BankTransactionsUpdater;
import com.epam.winterjavalab.dao.SettingsCreater;
import com.epam.winterjavalab.dao.jsonserver.JsonBankCreater;
import com.epam.winterjavalab.dao.jsonserver.JsonBankTransactionsUpdater;
import com.epam.winterjavalab.dao.jsonserver.JsonSettingsCreater;
import com.epam.winterjavalab.service.PrinterService;

public class BankProcessingCenter {
    public static final String DB_PATH = "data/db.json";
    public static final String DIRECTORY_PATH = "data/";
    public static final String SETTINGS_PATH = "data/settings.json";
    public static void main(String[] args) {

        SettingsCreater settingsCreater = new JsonSettingsCreater();
        Settings settings = settingsCreater.createSettings(SETTINGS_PATH);

        BankTransactionsUpdater bankTransactionsUpdater = new JsonBankTransactionsUpdater();
        bankTransactionsUpdater.update(DIRECTORY_PATH,DB_PATH,settings);

        BankCreater bankCreater = new JsonBankCreater();
        Bank bank = bankCreater.createBankFromFile(DB_PATH);

        PrinterService printerService = new PrinterService();
        printerService.printResult(bank,settings);

    }
}
