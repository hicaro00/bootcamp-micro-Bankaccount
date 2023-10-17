package com.lizana.microservicebankaccount.application;

import com.lizana.microservicebankaccount.infrastructure.inputport.BanckAccountService;
import com.lizana.microservicebankaccount.infrastructure.outpupport.BankAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountServiceImpl implements BanckAccountService {
    @Autowired
    private BankAccountRepo bankAccountRepo;

}
