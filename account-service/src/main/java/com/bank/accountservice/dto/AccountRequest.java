package com.bank.accountservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AccountRequest {

    @NotBlank(message = "Account Holder Name is required")
    private String accountHolderName;

    @NotBlank(message = "Account Number is required")
    private String accountNumber;

    @NotNull(message = "Balance is required")
    @Positive(message = "Balance must be greater than zero")
    private Double balance;

    @NotBlank(message = "Account Type is required")
    private String accountType;

    @NotBlank(message = "Status is required")
    private String status;
}