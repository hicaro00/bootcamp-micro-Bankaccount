package com.lizana.microservicebankaccount.application.serviceimpl;

import com.lizana.microservicebankaccount.application.utils.AccountNumberGenerator;
import com.lizana.microservicebankaccount.domain.dtos.BankAccountDto;
import com.lizana.microservicebankaccount.domain.dtos.BankacountsList;
import com.lizana.microservicebankaccount.domain.dtos.CustomerDto;
import com.lizana.microservicebankaccount.domain.utilsmaper.AccountUtil;
import com.lizana.microservicebankaccount.infrastructure.inputport.BanckAccountService;
import com.lizana.microservicebankaccount.infrastructure.outpupport.BankAccountRepo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

@Service
@Slf4j
public class BankAccountServiceImpl implements BanckAccountService {
  @Autowired
  private BankAccountRepo bankAccountRepo;
  @Autowired
  private WebClient webClient;

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


    String customerId = bankAccountDto.getCustomerId();
    String accountType = bankAccountDto.getAccountType(); // Obtener el tipo de cuenta desde accountCreationRequest
    BankAccountDto newAccount = new BankAccountDto();

    if ("SAVINGS".equals(accountType)) {
      newAccount.setCustomerId(customerId);
      newAccount.setAccountType("SAVINGS");
      newAccount.setAccountNumber(AccountNumberGenerator.generateAccountNumber());
      newAccount.setMonthlyMovementLimit(20);
      newAccount.setMaintenanceFee(0.0);
      newAccount.setMaturityDate(null);
      newAccount.setBalance(BigDecimal.ZERO);
      newAccount.setTransactionFee(0.01);
      newAccount.setOpeningDate(LocalDate.now());
      newAccount.setTemporaryBlock(true);
      newAccount.setAccountStatus("personal-vip");
      newAccount.setAvailableBalance(BigDecimal.ZERO);
      newAccount.setNumberTransactions(100);
      newAccount.setSignatory(new ArrayList<>(bankAccountDto.getSignatory()));
    } else if ("CURRENTACCOUNT".equals(accountType)) {
      newAccount.setCustomerId(customerId);
      newAccount.setAccountType("CURRENTACCOUNT");
      newAccount.setMonthlyMovementLimit(20);
      newAccount.setAccountNumber(AccountNumberGenerator.generateAccountNumber());
      newAccount.setMaintenanceFee(50.00);
      newAccount.setBalance(BigDecimal.ZERO);
      newAccount.setMaturityDate(null);
      newAccount.setTransactionFee(0.01);
      newAccount.setOpeningDate(LocalDate.now());
      newAccount.setTemporaryBlock(false);
      newAccount.setAccountStatus("empresarial-pyme"); //normal o pyme
      newAccount.setAvailableBalance(BigDecimal.ZERO);
      newAccount.setSignatory(new ArrayList<>(bankAccountDto.getSignatory()));
    } else if ("FIXEDTERM".equals(accountType)) {
      newAccount.setCustomerId(customerId);
      newAccount.setAccountType("FIXEDTERM");
      newAccount.setMonthlyMovementLimit(2);
      newAccount.setAccountNumber(AccountNumberGenerator.generateAccountNumber());
      newAccount.setMaintenanceFee(0);
      newAccount.setMaturityDate(null);
      newAccount.setBalance(BigDecimal.ZERO);
      newAccount.setTransactionFee(0.01);
      newAccount.setOpeningDate(LocalDate.now());
      newAccount.setTemporaryBlock(false);
      newAccount.setAccountStatus("personal");
      newAccount.setAvailableBalance(BigDecimal.ZERO);
      newAccount.setNumberTransactions(2);
      newAccount.setSignatory(new ArrayList<>(bankAccountDto.getSignatory()));
    } else {
      Logger logger = LoggerFactory.getLogger(getClass());
      logger.error("Tipo de cuenta no soportado: {}", accountType);
      return Mono.error(new IllegalArgumentException("Tipo de cuenta no soportado: " + accountType));
    }


    return bankAccountRepo.insert(AccountUtil.dtoToEntity(newAccount))
            .map(AccountUtil::entityToDto);

  }

  @Override
  public Mono<CustomerDto> createdAndAssociateBankAccount(BankAccountDto bankAccountDto) {
    return createdBanckAccount(bankAccountDto)
            .flatMap(bankAccountDto1 -> {

              BankacountsList bankacountsList = new BankacountsList();
              bankacountsList.setBalance(String.valueOf(bankAccountDto1.getBalance()));
              bankacountsList.setAccountNumber(bankAccountDto1.getAccountNumber());
              bankacountsList.setAccountType(bankAccountDto1.getAccountType());


              CustomerDto customerDtoNew = new CustomerDto();
              customerDtoNew.setBankAccounts(Collections.singletonList(bankacountsList));


              return webClient.put()
                      .uri("http://localhost:8085/customer/updatecustomer/{id}", bankAccountDto1.getCustomerId())
                      .bodyValue(customerDtoNew)
                      .retrieve()
                      .bodyToMono(CustomerDto.class);
            });
  }
}
