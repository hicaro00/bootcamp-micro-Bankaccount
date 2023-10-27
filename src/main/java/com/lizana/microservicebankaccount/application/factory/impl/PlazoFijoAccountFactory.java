package com.lizana.microservicebankaccount.application.factory.impl;

import com.lizana.microservicebankaccount.application.factory.BankAccountFactory;
import com.lizana.microservicebankaccount.application.utils.AccountNumberGenerator;
import com.lizana.microservicebankaccount.domain.dtos.BankAccountDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
@Component
public class PlazoFijoAccountFactory implements BankAccountFactory {
  @Override
  public BankAccountDto createAccount(BankAccountDto dto) {
    dto.setAccountType("FIXEDTERM");
    dto.setMonthlyMovementLimit(2);
    dto.setAccountNumber(AccountNumberGenerator.generateAccountNumber());
    dto.setMaintenanceFee(0);
    dto.setMaturityDate(null);
    dto.setTransactionFee(0.01);
    dto.setOpeningDate(LocalDate.now());
    dto.setTemporaryBlock(false);
    dto.setAccountStatus("normal");
    dto.setAvailableBalance(BigDecimal.ZERO);
    dto.setNumberTransactions(2);

    return dto;
  }
}
