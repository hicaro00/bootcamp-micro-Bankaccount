package com.lizana.microservicebankaccount.application.factory.impl;

import com.lizana.microservicebankaccount.application.factory.BankAccountFactory;
import com.lizana.microservicebankaccount.application.utils.AccountNumberGenerator;
import com.lizana.microservicebankaccount.domain.dtos.BankAccountDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
@Component
public class AhorroAccountFactory implements BankAccountFactory {
  @Override
  public BankAccountDto createAccount(BankAccountDto dto) {

    dto.setAccountNumber(AccountNumberGenerator.generateAccountNumber());
    dto.setMonthlyMovementLimit(20);
    dto.setMaintenanceFee(0.0);
    dto.setMaturityDate(null);
    dto.setTransactionFee(0.01);
    dto.setOpeningDate(LocalDate.now());
    dto.setTemporaryBlock(true);
    dto.setAccountStatus("normal");
    dto.setAvailableBalance(BigDecimal.ZERO);
    dto.setNumberTransactions(100);

    return dto;
  }
}
