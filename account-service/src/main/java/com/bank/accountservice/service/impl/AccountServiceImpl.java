package com.bank.accountservice.service.impl;

import com.bank.accountservice.dto.AccountRequest;
import com.bank.accountservice.entity.Account;
import com.bank.accountservice.exception.AccountNotFoundException;
import com.bank.accountservice.exception.InsufficientBalanceException;
import com.bank.accountservice.repository.AccountRepository;
import com.bank.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import com.bank.accountservice.dto.BalanceUpdateRequest;
import com.bank.accountservice.entity.Account;

import org.springframework.stereotype.Service;



import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public Account createAccount(AccountRequest request) {

        Account account = Account.builder()
                .accountHolderName(request.getAccountHolderName())
                .accountNumber(request.getAccountNumber())
                .balance(request.getBalance())
                .accountType(request.getAccountType())
                .status(request.getStatus())
                .createdAt(LocalDateTime.now())
                .build();

        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with id : " + id));
    }
    @Override
    public Account updateAccount(Long id, AccountRequest request) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with id : " + id));

        account.setAccountHolderName(request.getAccountHolderName());
        account.setAccountNumber(request.getAccountNumber());

        account.setAccountType(request.getAccountType());
        account.setStatus(request.getStatus());

        return accountRepository.save(account);
    }
    @Override
    public void deleteAccount(Long id) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with id : " + id));

        accountRepository.delete(account);
    }
    @Override

    public Account getByAccountNumber(String accountNumber) {

        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new AccountNotFoundException("Account not found with account number : " + accountNumber));
    }

    @Override


    public Account updateBalance(String accountNumber, BalanceUpdateRequest request) {

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new AccountNotFoundException(
                                "Account not found with account number : " + accountNumber));

        if (request.getType().equalsIgnoreCase("DEBIT")) {

            // Insufficient Balance Check
            if (account.getBalance() < request.getAmount()) {
                throw new InsufficientBalanceException("Insufficient Balance");
            }

            account.setBalance(account.getBalance() - request.getAmount());

        } else if (request.getType().equalsIgnoreCase("CREDIT")) {

            account.setBalance(account.getBalance() + request.getAmount());

        } else {

            throw new RuntimeException("Invalid Transaction Type");
        }

        return accountRepository.save(account);
    }


}