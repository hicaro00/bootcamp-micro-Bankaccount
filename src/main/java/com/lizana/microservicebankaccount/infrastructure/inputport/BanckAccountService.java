package com.lizana.microservicebankaccount.infrastructure.inputport;

import com.lizana.microservicebankaccount.domain.dtos.BankAccountDto;
import com.lizana.microservicebankaccount.domain.dtos.CustomerDto;
import com.lizana.microservicebankaccount.domain.dtos.Signatory;
import reactor.core.publisher.Mono;

public interface BanckAccountService {


Mono<BankAccountDto> getInfoBanckAccount(String accountId);

   Mono<BankAccountDto> putInfoBankAccount(String accountId, Mono<BankAccountDto> bankAccountDto);

   Mono<Void> deleteBankAccount(String accountId);

   Mono<BankAccountDto> createdBanckAccount(BankAccountDto bankAccountDto);

  Mono<CustomerDto> createdAndAssociateBankAccount(BankAccountDto bankAccountDto);

  Mono<BankAccountDto> addNewSignature(Signatory signatory, String banckAccountId);

}
