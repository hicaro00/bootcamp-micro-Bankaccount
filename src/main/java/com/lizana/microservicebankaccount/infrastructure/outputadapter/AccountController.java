package com.lizana.microservicebankaccount.infrastructure.outputadapter;

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
@RequestMapping("/Account")
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
/*
    @PutMapping
    @ResponseBody
    public Mono<BankAccountDto> cuentasBancariasAccountIdPut() {
        return null;
    }

*/
    @PostMapping
    @ResponseBody
    public Mono<ResponseEntity<BankAccountDto>> cuentasBancariasPost(@RequestBody Mono<BankAccountDto> bankAccountDtoMono) {



        return banckAccountService.createdBanckAccount(bankAccountDtoMono)
                .map(ResponseEntity::ok);
    }




}
