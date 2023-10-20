package com.lizana.microservicebankaccount.infrastructure.inputport;

import com.lizana.microservicebankaccount.domain.dtos.BankAccountDto;
import com.lizana.microservicebankaccount.domain.dtos.DepositAmountDto;
import com.lizana.microservicebankaccount.domain.dtos.WithdrawalAmountDto;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TransaccionesService {

    public Mono<BankAccountDto> depositInAccount(String accountId, DepositAmountDto depositAmountDto);
    public Mono<BankAccountDto> WithdrawalAccount(String accountId, WithdrawalAmountDto withdrawalAmountDto);
    public Mono<List<DepositAmountDto>> movementsAccount(String accountId);
}


