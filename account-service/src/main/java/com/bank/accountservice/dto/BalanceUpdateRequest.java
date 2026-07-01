package com.bank.accountservice.dto;

import lombok.Data;

@Data
public class BalanceUpdateRequest {

    private Double amount;
    private String type;   // DEBIT or CREDIT
}