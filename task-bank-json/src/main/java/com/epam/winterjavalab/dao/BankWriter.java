package com.epam.winterjavalab.dao;

import com.epam.winterjavalab.beans.Bank;

public interface BankWriter {
    void writeBankToFile(Bank bank, String filePath);
}
