package com.lizana.microservicebankaccount.infrastructure.outputadapter;

import com.lizana.microservicebankaccount.domain.dtos.AccountCreationRequest;
import com.lizana.microservicebankaccount.domain.dtos.BankAccountDto;
import com.lizana.microservicebankaccount.infrastructure.inputport.BanckAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping("/account")
public class AccountController {

  @Autowired
  private BanckAccountService banckAccountService;

  @GetMapping
  @ResponseBody
  public Mono<BankAccountDto> cuentasBancariasAccountIdGet
          (@RequestParam String accountId) {
    Mono<BankAccountDto> bankAccount = banckAccountService.getInfoBanckAccount(accountId);

    return ResponseEntity.status(HttpStatus.GONE).body(bankAccount).getBody();
  }


  @PostMapping
  @ResponseBody
  public Mono<BankAccountDto> cuentasBancariasPost(@RequestBody BankAccountDto bankAccountDto)  {



    return banckAccountService.createdBanckAccount(bankAccountDto);


  }
}
