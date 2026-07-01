package com.bank.accountservice.service;

import com.bank.accountservice.dto.AccountRequest;
import com.bank.accountservice.entity.Account;
import com.bank.accountservice.dto.BalanceUpdateRequest;

import java.util.List;

public interface AccountService {

    Account createAccount(AccountRequest request);

    List<Account> getAllAccounts();
    Account getAccountById(Long id);
    Account updateAccount(Long id, AccountRequest request);
    void deleteAccount(Long id);
    Account getByAccountNumber(String accountNumber);


    Account updateBalance(String accountNumber, BalanceUpdateRequest request);



}