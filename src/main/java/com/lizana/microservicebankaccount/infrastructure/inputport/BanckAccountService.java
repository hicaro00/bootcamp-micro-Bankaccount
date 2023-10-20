package com.lizana.microservicebankaccount.infrastructure.inputport;

import com.lizana.microservicebankaccount.domain.dtos.BankAccountDto;
import reactor.core.publisher.Mono;

public interface BanckAccountService {


    public Mono<BankAccountDto> getInfoBanckAccount(String accountId);
    public Mono<BankAccountDto> putInfoBankAccount(String accountId, Mono<BankAccountDto> bankAccountDto);
    public Mono<Void> deleteBankAccount(String accountId);
    public Mono<BankAccountDto> createdBanckAccount(Mono<BankAccountDto> bankAccountDtoMono);



}
