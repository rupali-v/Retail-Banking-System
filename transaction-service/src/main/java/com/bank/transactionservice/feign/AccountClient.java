package com.bank.transactionservice.feign;

import com.bank.transactionservice.dto.BalanceUpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ACCOUNT-SERVICE")
public interface AccountClient {

    @GetMapping("/accounts/number/{accountNumber}")
    Object getByAccountNumber(@PathVariable String accountNumber);
    @PutMapping("/accounts/{accountNumber}/balance")
    void  updateBalance(@PathVariable String accountNumber,
                          @RequestBody BalanceUpdateRequest request);


}