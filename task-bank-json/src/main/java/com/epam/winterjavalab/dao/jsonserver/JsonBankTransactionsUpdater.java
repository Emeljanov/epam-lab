package com.epam.winterjavalab.dao.jsonserver;

import com.epam.winterjavalab.beans.*;
import com.epam.winterjavalab.dao.BankTransactionsUpdater;
import com.epam.winterjavalab.dao.jsonserver.adapters.TransactionAdapter;
import com.epam.winterjavalab.service.FileService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonBankTransactionsUpdater implements BankTransactionsUpdater {

    @Override
    public void update( String pathdir, String bankFilePath, Settings settings) {

        FileService fileService = new FileService();
        List<String> files = fileService.getTransactionsFilesList(pathdir,settings);

        for(String fileName : files) {
            String filePath = pathdir + fileName;
            updateTransaction(filePath,bankFilePath);
        }
    }

    @Override
    public void updateTransaction(String transactionsfilePath, String bankFilePath) {
        TransactionsArray tmpTransactions = new TransactionsArray();

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Transaction.class, new TransactionAdapter())
                .create();

        try(JsonReader jsonReader = new JsonReader(new FileReader(transactionsfilePath))) {

            tmpTransactions = gson.fromJson(jsonReader, TransactionsArray.class);

            Bank bank = new JsonBankCreater().createBankFromFile(bankFilePath);

            List<Transaction> newTransactionsList = tmpTransactions.getList();

            bank.addNewTransactions(newTransactionsList);

            JsonBankWriter bankWriter = new JsonBankWriter();

            bankWriter.writeBankToFile(bank,bankFilePath);

            cleanTransactionsFile(gson,transactionsfilePath);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private void cleanTransactionsFile(Gson gson, String transactionsfilePath) {
        try(Writer writer = new FileWriter(transactionsfilePath)) {
            TransactionsArray emptyTrn = new TransactionsArray();
            List<Transaction> transactions = new ArrayList<>();
            emptyTrn.setList(transactions);
            gson.toJson(emptyTrn,writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
