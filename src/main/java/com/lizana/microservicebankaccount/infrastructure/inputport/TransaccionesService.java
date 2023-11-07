package com.lizana.microservicebankaccount.infrastructure.inputport;

import com.lizana.microservicebankaccount.domain.dtos.BankAccountDto;
import com.lizana.microservicebankaccount.domain.dtos.DepositAmountDto;
import com.lizana.microservicebankaccount.domain.dtos.MovementDto;
import com.lizana.microservicebankaccount.domain.dtos.WithdrawalAmountDto;
import java.util.List;
import reactor.core.publisher.Mono;

public interface TransaccionesService {

    public Mono<BankAccountDto> depositInAccount(MovementDto movementDto);
    public Mono<BankAccountDto> WithdrawalAccount(String accountId,
                                                  WithdrawalAmountDto withdrawalAmountDto);
    public Mono<List<DepositAmountDto>> movementsAccount(String accountId);
}


