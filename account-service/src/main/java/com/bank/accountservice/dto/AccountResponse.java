package com.bank.accountservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {

    private String accountHolderName;
    private String accountNumber;
    private Double balance;
    private String accountType;
    private String status;
}