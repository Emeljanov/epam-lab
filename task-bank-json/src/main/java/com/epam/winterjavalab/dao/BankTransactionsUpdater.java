package com.epam.winterjavalab.dao;

import com.epam.winterjavalab.beans.Settings;

import java.util.List;

public interface BankTransactionsUpdater {
    void update(String pathdir, String bankFilePath, Settings settings);
    void updateTransaction(String transactionsFilePath, String bankFilePath);
}
