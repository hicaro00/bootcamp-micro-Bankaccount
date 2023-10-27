package com.lizana.microservicebankaccount.application.factory;

import com.lizana.microservicebankaccount.domain.dtos.BankAccountDto;

public interface BankAccountFactory {
  public BankAccountDto createAccount(BankAccountDto dto);
}
