package com.bank.transactionservice.service.impl;

import com.bank.transactionservice.dto.TransferRequest;
import com.bank.transactionservice.entity.Transaction;
import com.bank.transactionservice.feign.AccountClient;
import com.bank.transactionservice.repository.TransactionRepository;
import com.bank.transactionservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.bank.transactionservice.dto.BalanceUpdateRequest;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountClient accountClient;


    @Override
    @Transactional
    public Transaction transfer(TransferRequest request) {

        // Debit sender
        BalanceUpdateRequest debit = new BalanceUpdateRequest();
        debit.setAmount(request.getAmount());
        debit.setType("DEBIT");
        accountClient.updateBalance(request.getFromAccount(), debit);

        // Credit receiver
        BalanceUpdateRequest credit = new BalanceUpdateRequest();
        credit.setAmount(request.getAmount());
        credit.setType("CREDIT");
        accountClient.updateBalance(request.getToAccount(), credit);

        // 👇


        // Save transaction
        Transaction transaction = Transaction.builder()
                .fromAccount(request.getFromAccount())
                .toAccount(request.getToAccount())
                .amount(request.getAmount())
                .transactionType("FUND_TRANSFER")
                .status("SUCCESS")
                .transactionDate(LocalDateTime.now())
                .build();
//        throw new RuntimeException("Testing Transaction Rollback");
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
    
}