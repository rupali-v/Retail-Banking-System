package com.bank.accountservice.controller;

import com.bank.accountservice.dto.AccountRequest;
import com.bank.accountservice.entity.Account;
import com.bank.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.bank.accountservice.entity.Account;
import com.bank.accountservice.dto.BalanceUpdateRequest;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public Account createAccount(@Valid @RequestBody AccountRequest request) {
        return accountService.createAccount(request);
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }
    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }



    @PutMapping("/{id}")
    public Account updateAccount(@PathVariable Long id,
                                 @Valid @RequestBody AccountRequest request) {
        return accountService.updateAccount(id, request);
    }
    @DeleteMapping("/{id}")
    public String deleteAccount(@PathVariable Long id) {

        accountService.deleteAccount(id);

        return "Account Deleted Successfully";
    }
    @GetMapping("/test")
    public String test() {
        return "Controller Working";
    }



    @GetMapping("/number/{accountNumber}")
    public Account getByAccountNumber(@PathVariable String accountNumber) {
        return accountService.getByAccountNumber(accountNumber);
    }
    @PutMapping("/{accountNumber}/balance")
    public Account updateBalance(@PathVariable String accountNumber,
                                 @RequestBody BalanceUpdateRequest request) {
        return accountService.updateBalance(accountNumber, request);
    }
}