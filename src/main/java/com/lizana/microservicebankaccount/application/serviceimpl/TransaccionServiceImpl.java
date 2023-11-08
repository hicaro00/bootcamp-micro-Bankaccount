package com.lizana.microservicebankaccount.application.serviceimpl;

import com.lizana.microservicebankaccount.domain.dtos.BankAccountDto;
import com.lizana.microservicebankaccount.domain.dtos.DepositAmountDto;
import com.lizana.microservicebankaccount.domain.dtos.MovementDto;
import com.lizana.microservicebankaccount.domain.dtos.WithdrawalAmountDto;
import com.lizana.microservicebankaccount.domain.entity.BankAccount;
import com.lizana.microservicebankaccount.domain.utilsmaper.AccountUtil;
import com.lizana.microservicebankaccount.infrastructure.inputport.TransaccionesService;
import com.lizana.microservicebankaccount.infrastructure.outpupport.BankAccountRepo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TransaccionServiceImpl implements TransaccionesService {
  @Autowired
  BankAccountRepo bankAccountRepo;

  @Override
  public Mono<BankAccountDto> depositInAccount(MovementDto movementDto) {
    Mono<BankAccount> bankAccountDtoMono =
        bankAccountRepo.findById(movementDto.getDestinationMovement());
    return bankAccountDtoMono.flatMap(
        existingAccount -> {
          BigDecimal newAmount = existingAccount.getBalance().add(movementDto.getAmount());

          //primero creamos una copia del documento recuperado monogoDb
          List<DepositAmountDto> updatedListDeposit =
              new ArrayList<>(existingAccount.getDeposits());
          // agregamos el deposito a la lista
          DepositAmountDto depositAmountDto = new DepositAmountDto();
          depositAmountDto.setMovementId(movementDto.getMovementId());
          depositAmountDto.setAmount(movementDto.getAmount());
          depositAmountDto.setOriginMovement(movementDto.getOriginMovement());
          depositAmountDto.setAuthorizationCode(movementDto.getAuthorizationCode());
          depositAmountDto.setDateOfMovement(movementDto.getDateOfMovement());

          updatedListDeposit.add(depositAmountDto);
          // Actualizar la lista en el documento usando $set
          existingAccount.setDeposits(updatedListDeposit);
          existingAccount.setBalance(newAmount);

          // Realizar la actualización en la base de datos
          return bankAccountRepo.save(existingAccount)
              .map(AccountUtil::entityToDto);
        }
    );


  }

  @Override
  public Mono<BankAccountDto> withdrawalAccount(String accountId,
                                                WithdrawalAmountDto withdrawalAmountDto) {
    return bankAccountRepo.findById(accountId).flatMap(cuentaExistente -> {
      List<WithdrawalAmountDto> withdrawalAmountDtos =
          new ArrayList<>(cuentaExistente.getWithdrawalAmounts());
      withdrawalAmountDtos.add(withdrawalAmountDto);
      cuentaExistente.setWithdrawalAmounts(withdrawalAmountDtos);
      return bankAccountRepo.save(cuentaExistente).map(AccountUtil::entityToDto);
    });
  }

  @Override
  public Mono<List<DepositAmountDto>> movementsAccount(String accountId) {
    //primero obtenemos la cuenta por el id
    return bankAccountRepo.findById(accountId)
        .map(BankAccount::getDeposits)
        .switchIfEmpty(Mono.just(Collections.emptyList()));
  }
}
