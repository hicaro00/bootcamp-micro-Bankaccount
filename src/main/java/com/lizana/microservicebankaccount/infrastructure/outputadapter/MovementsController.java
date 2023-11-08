package com.lizana.microservicebankaccount.infrastructure.outputadapter;


import com.lizana.microservicebankaccount.domain.dtos.BankAccountDto;
import com.lizana.microservicebankaccount.domain.dtos.MovementDto;
import com.lizana.microservicebankaccount.infrastructure.inputport.TransaccionesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping("/movimientos")
public class MovementsController {

    @Autowired
    private TransaccionesService transaccionesService;

    @PostMapping("/deposit")
    @ResponseBody
    public Mono<ResponseEntity<BankAccountDto>> transferdeposit
            (@RequestBody MovementDto movementDto) {

        return transaccionesService.depositInAccount(movementDto)
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
