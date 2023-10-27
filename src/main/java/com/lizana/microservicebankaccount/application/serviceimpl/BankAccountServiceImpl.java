package com.lizana.microservicebankaccount.application.serviceimpl;

import com.lizana.microservicebankaccount.application.factory.BankAccountFactory;
import com.lizana.microservicebankaccount.application.factory.impl.AhorroAccountFactory;
import com.lizana.microservicebankaccount.application.factory.impl.CuentaCorrienteAccountFactory;
import com.lizana.microservicebankaccount.application.factory.impl.PlazoFijoAccountFactory;
import com.lizana.microservicebankaccount.application.utils.AccountNumberGenerator;
import com.lizana.microservicebankaccount.domain.dtos.BankAccountDto;
import com.lizana.microservicebankaccount.domain.utilsmaper.AccountUtil;
import com.lizana.microservicebankaccount.infrastructure.inputport.BanckAccountService;
import com.lizana.microservicebankaccount.infrastructure.outpupport.BankAccountRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class BankAccountServiceImpl implements BanckAccountService {
  @Autowired
  private BankAccountRepo bankAccountRepo;

  @Autowired
  private List<BankAccountFactory> factories;

  @Override
  public Mono<BankAccountDto> getInfoBanckAccount(String accountId) {
    return bankAccountRepo.findById(accountId).map(AccountUtil::entityToDto);
  }

  @Override
  public Mono<BankAccountDto> putInfoBankAccount(String accountId, Mono<BankAccountDto> bankAccountDto) {

    return bankAccountRepo.findById(accountId)
            .flatMap(existingAccount -> bankAccountDto
                    .map(dto -> {
                      if (dto.getAccountType() != null) {
                        existingAccount.setAccountType(dto.getAccountType());
                      }
                      if (dto.getCustomerId() != null) {
                        existingAccount.setCustomerId(dto.getCustomerId());
                      }
                      return existingAccount;
                    })
                    .flatMap(updatedAccount -> bankAccountRepo.save(updatedAccount))
                    .map(AccountUtil::entityToDto));

  }

  @Override
  public Mono<Void> deleteBankAccount(String accountId) {

    return bankAccountRepo.deleteById(accountId);
  }

  @Override
  public Mono<BankAccountDto> createdBanckAccount(BankAccountDto bankAccountDto) {
    String accountType = bankAccountDto.getAccountType(); // Obtener el tipo de cuenta desde accountCreationRequest

    Map<String, BankAccountFactory> factoryMap = new HashMap<>();
    factoryMap.put("SAVINGS", new AhorroAccountFactory());
    factoryMap.put("CURRENTACCOUNT", new CuentaCorrienteAccountFactory());
    factoryMap.put("FIXEDTERM", new PlazoFijoAccountFactory());

  //  BankAccountFactory selectedFactory = factoryMap.get(accountType);



    if ("SAVINGS".equals(accountType)) {
    BankAccountDto newAccount = new BankAccountDto();
    newAccount.setAccountType("SAVINGS");
    newAccount.setAccountNumber(AccountNumberGenerator.generateAccountNumber());
    newAccount.setMonthlyMovementLimit(20);
    newAccount.setMaintenanceFee(0.0);
    newAccount.setMaturityDate(null);
    newAccount.setTransactionFee(0.01);
    newAccount.setOpeningDate(LocalDate.now());
    newAccount.setTemporaryBlock(true);
    newAccount.setAccountStatus("normal");
    newAccount.setAvailableBalance(BigDecimal.ZERO);
    newAccount.setNumberTransactions(100);
    newAccount.setSignatory(new ArrayList<>(bankAccountDto.getSignatory()));
    } else if ("CURRENTACCOUNT".equals(accountType)) {
      BankAccountDto newAccount = new BankAccountDto();
      newAccount.setAccountType("CURRENTACCOUNT");
      newAccount.setMonthlyMovementLimit(20);
      newAccount.setAccountNumber(AccountNumberGenerator.generateAccountNumber());
      newAccount.setMaintenanceFee(50.00);
      newAccount.setBalance(BigDecimal.ZERO);
      newAccount.setMaturityDate(null);
      newAccount.setTransactionFee(0.01);
      newAccount.setOpeningDate(LocalDate.now());
      newAccount.setTemporaryBlock(false);
      newAccount.setAccountStatus("normal"); //normal o pyme
      newAccount.setAvailableBalance(BigDecimal.ZERO);
      newAccount.setSignatory(new ArrayList<>(bankAccountDto.getSignatory()));
    }if ("FIXEDTERM".equals(accountType)){
      BankAccountDto newAccount = new BankAccountDto();
      newAccount.setAccountType("FIXEDTERM");
      newAccount.setMonthlyMovementLimit(2);
      newAccount.setAccountNumber(AccountNumberGenerator.generateAccountNumber());
      newAccount.setMaintenanceFee(0);
      newAccount.setMaturityDate(null);
      newAccount.setTransactionFee(0.01);
      newAccount.setOpeningDate(LocalDate.now());
      newAccount.setTemporaryBlock(false);
      newAccount.setAccountStatus("normal");
      newAccount.setAvailableBalance(BigDecimal.ZERO);
      newAccount.setNumberTransactions(2);
      newAccount.setSignatory(new ArrayList<>(bankAccountDto.getSignatory()));
    }  else {
    System.out.println("Tipo de cuenta no soportado: " + accountType);
    return Mono.error(new IllegalArgumentException("Tipo de cuenta no soportado: " + accountType));
    }


    // Realiza las operaciones necesarias y devuelve un Mono<AccountCreationRequest> en lugar de Mono<BankAccountDto>
      return bankAccountRepo.insert(AccountUtil.dtoToEntity(newAccount))
              .map(AccountUtil::entityToDto)
              .map(dto -> {
                // Puedes realizar las transformaciones necesarias para actualizar el objeto AccountCreationRequest
                return bankAccountDto;
              });

  }


}