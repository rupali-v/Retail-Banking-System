package com.bank.transactionservice.dto;

import lombok.Data;

@Data
public class BalanceUpdateRequest {

    private Double amount;
    private String type;
}