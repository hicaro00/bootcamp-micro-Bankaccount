package com.lizana.microservicebankaccount.infrastructure.outputadapter;

import com.lizana.microservicebankaccount.infrastructure.inputport.BanckAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class BankAccountController {
    @Autowired
    private BanckAccountService banckAccountService;
}
