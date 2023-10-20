package com.lizana.microservicebankaccount.infrastructure.outputadapter;


import com.lizana.microservicebankaccount.domain.dtos.BankAccountDto;
import com.lizana.microservicebankaccount.domain.dtos.DepositAmountDto;
import com.lizana.microservicebankaccount.infrastructure.inputport.TransaccionesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping("/movimientos")
public class MovementsController {

    @Autowired
    private TransaccionesService transaccionesService;

    @PostMapping
    @ResponseBody
    public Mono<ResponseEntity<BankAccountDto>> cuentasBancariasAccountIdDepositosPost
            (@RequestParam String accountId, @RequestBody DepositAmountDto depositAmountDto) {

        return transaccionesService.depositInAccount(accountId,depositAmountDto)
                .map(ResponseEntity::ok);
    }

    /*
    @GetMapping
    @ResponseBody
    public Mono<BankAccountDto> cuentasBancariasAccountIdMovimientosGet() {

        return null;
    }

    @PostMapping
    @ResponseBody
    public Mono<BankAccountDto> cuentasBancariasAccountIdRetirosPost() {

        return null;
    }

*/

}
