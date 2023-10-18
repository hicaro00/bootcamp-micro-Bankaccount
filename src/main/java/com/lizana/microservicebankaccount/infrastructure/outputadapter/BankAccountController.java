package com.lizana.microservicebankaccount.infrastructure.outputadapter;

import com.lizana.microservicebankaccount.infrastructure.inputport.BanckAccountService;
import com.lizana.microservicebankaccount.infrastructure.outpupport.BankAccountRepo;
import com.lizana.microservicebankaccount.model.CuentasBancariasApiDelegate;
import org.openapitools.model.BankAccount;
import org.openapitools.model.DepositAmount;
import org.openapitools.model.WithdrawalAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


public class BankAccountController implements CuentasBancariasApiDelegate {

    @Autowired
    private BankAccountRepo bankAccountRepo;
    @Override
    public Mono<ResponseEntity<Void>> cuentasBancariasAccountIdDelete(String accountId, ServerWebExchange exchange) {

       return bankAccountRepo.delete(accountId);

    }

    @Override
    public Mono<ResponseEntity<Void>> cuentasBancariasAccountIdDepositosPost(String accountId, Mono<DepositAmount> depositAmount, ServerWebExchange exchange) {
        return CuentasBancariasApiDelegate.super.cuentasBancariasAccountIdDepositosPost(accountId, depositAmount, exchange);
    }

    @Override
    public Mono<ResponseEntity<Void>> cuentasBancariasAccountIdGet(String accountId, ServerWebExchange exchange) {
        return CuentasBancariasApiDelegate.super.cuentasBancariasAccountIdGet(accountId, exchange);
    }

    @Override
    public Mono<ResponseEntity<Void>> cuentasBancariasAccountIdMovimientosGet(String accountId, ServerWebExchange exchange) {
        return CuentasBancariasApiDelegate.super.cuentasBancariasAccountIdMovimientosGet(accountId, exchange);
    }

    @Override
    public Mono<ResponseEntity<Void>> cuentasBancariasAccountIdPut(String accountId, Mono<BankAccount> bankAccount, ServerWebExchange exchange) {
        return CuentasBancariasApiDelegate.super.cuentasBancariasAccountIdPut(accountId, bankAccount, exchange);
    }

    @Override
    public Mono<ResponseEntity<Void>> cuentasBancariasAccountIdRetirosPost(String accountId, Mono<WithdrawalAmount> withdrawalAmount, ServerWebExchange exchange) {
        return CuentasBancariasApiDelegate.super.cuentasBancariasAccountIdRetirosPost(accountId, withdrawalAmount, exchange);
    }

    @Override
    public Mono<ResponseEntity<Void>> cuentasBancariasPost(Mono<BankAccount> bankAccount, ServerWebExchange exchange) {
        return CuentasBancariasApiDelegate.super.cuentasBancariasPost(bankAccount, exchange);
    }
}
