package com.lizana.microservicebankaccount.application.factory.impl;

import com.lizana.microservicebankaccount.application.factory.BankAccountFactory;
import com.lizana.microservicebankaccount.application.utils.AccountNumberGenerator;
import com.lizana.microservicebankaccount.domain.dtos.BankAccountDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
@Component
public class CuentaCorrienteAccountFactory implements BankAccountFactory {


  @Override
  public BankAccountDto createAccount(BankAccountDto dto) {
    dto.setAccountType("CURRENTACCOUNT");
    dto.setMonthlyMovementLimit(20);
    dto.setAccountNumber(AccountNumberGenerator.generateAccountNumber());
    dto.setMaintenanceFee(50.00);
    dto.setBalance(BigDecimal.ZERO);
    dto.setMaturityDate(null);
    dto.setTransactionFee(0.01);
    dto.setOpeningDate(LocalDate.now());
    dto.setTemporaryBlock(false);
    dto.setAccountStatus("normal"); //normal o pyme
    dto.setAvailableBalance(BigDecimal.ZERO);

    return dto;

  }
}
