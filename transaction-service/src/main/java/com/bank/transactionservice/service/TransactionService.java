package com.bank.transactionservice.service;

import com.bank.transactionservice.dto.TransferRequest;
import com.bank.transactionservice.entity.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction transfer(TransferRequest request);

    List<Transaction> getAllTransactions();

}