package com.epam.winterjavalab.dao.jsonserver;

import com.epam.winterjavalab.beans.Bank;
import com.epam.winterjavalab.beans.Credit;
import com.epam.winterjavalab.beans.Transaction;
import com.epam.winterjavalab.beans.User;
import com.epam.winterjavalab.dao.BankWriter;
import com.epam.winterjavalab.dao.jsonserver.adapters.CreditAdapter;
import com.epam.winterjavalab.dao.jsonserver.adapters.TransactionAdapter;
import com.epam.winterjavalab.dao.jsonserver.adapters.UserAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class JsonBankWriter implements BankWriter {
    public JsonBankWriter() {
    }

    @Override
    public void writeBankToFile(Bank bank, String filePath) {
        try(Writer writer = new FileWriter(filePath)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapter(User.class, new UserAdapter())
                    .registerTypeAdapter(Credit.class, new CreditAdapter())
                    .registerTypeAdapter(Transaction.class, new TransactionAdapter())
                    .create();
            gson.toJson(bank,writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
