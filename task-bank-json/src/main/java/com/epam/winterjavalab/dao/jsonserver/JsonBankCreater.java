package com.epam.winterjavalab.dao.jsonserver;

import com.epam.winterjavalab.beans.Bank;
import com.epam.winterjavalab.beans.Credit;
import com.epam.winterjavalab.beans.Transaction;
import com.epam.winterjavalab.beans.User;
import com.epam.winterjavalab.dao.BankCreater;
import com.epam.winterjavalab.dao.jsonserver.adapters.CreditAdapter;
import com.epam.winterjavalab.dao.jsonserver.adapters.TransactionAdapter;
import com.epam.winterjavalab.dao.jsonserver.adapters.UserAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonBankCreater implements BankCreater {

    @Override
    public Bank createBankFromFile(String filePath) {

        try(JsonReader jsonReader = new JsonReader(new FileReader(filePath))) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapter(User.class, new UserAdapter())
                    .registerTypeAdapter(Credit.class, new CreditAdapter())
                    .registerTypeAdapter(Transaction.class, new TransactionAdapter())
                    .create();

            Bank bank = gson.fromJson(jsonReader, Bank.class);
            return bank;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  null;

    }

}
