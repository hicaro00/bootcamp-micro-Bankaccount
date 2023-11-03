package com.lizana.microservicebankaccount.application.serviceimpl;

import com.lizana.microservicebankaccount.application.utils.AccountNumberGenerator;
import com.lizana.microservicebankaccount.domain.dtos.BankAccountDto;
import com.lizana.microservicebankaccount.domain.dtos.BankacountsList;
import com.lizana.microservicebankaccount.domain.dtos.CustomerDto;
import com.lizana.microservicebankaccount.domain.dtos.Signatory;
import com.lizana.microservicebankaccount.domain.utilsmaper.AccountUtil;
import com.lizana.microservicebankaccount.infrastructure.inputport.BanckAccountService;
import com.lizana.microservicebankaccount.infrastructure.outpupport.BankAccountRepo;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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
    String accountType = bankAccountDto.getAccountType();
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
                      .uri("http://localhost:8080/customer/updatecustomer/{id}", bankAccountDto1.getCustomerId())
                      .bodyValue(customerDtoNew)
                      .retrieve()
                      .bodyToMono(CustomerDto.class);
            });
  }

  @Override
  public Mono<BankAccountDto> addNewSignature(Signatory signatory, String banckAccountId) {


    return getInfoBanckAccount(banckAccountId)
            .flatMap(bankAccountDto1 -> {
              // Crear una nueva Signatory basada en la existente
              Signatory newSignatory = new Signatory();
              newSignatory.setAddress(signatory.getAddress());
              newSignatory.setName(signatory.getName());
              newSignatory.setIdentifier(signatory.getIdentifier());
              newSignatory.setCustomerId(signatory.getCustomerId());

              // Actualizar la información del BankAccount en la base de datos
              bankAccountDto1.getSignatoryes().clear();
              bankAccountDto1.getSignatoryes().add(newSignatory);

              return addputInfoBankAccount(banckAccountId,Mono.just(bankAccountDto1) )
                      .thenReturn(bankAccountDto1); // Devolver el resultado de la actualización
            });

  }

  public Mono<BankAccountDto> addputInfoBankAccount(String accountId, Mono<BankAccountDto> bankAccountDto) {
    return bankAccountRepo.findById(accountId)
            .flatMap(existingBankAccount -> {
              return bankAccountDto.map(dto -> {
                // Obtener la lista de Signatory existente en la cuenta bancaria existente
                List<Signatory> existingSignatories = existingBankAccount.getSignatoryes();

                // Agregar el nuevo Signatory a la lista de Signatory existente
                existingSignatories.add(dto.getSignatoryes().get(0)); // Suponemos que solo se agrega un Signatory a la vez

                // Actualizar cualquier otro atributo de la cuenta bancaria si es necesario
                if (dto.getAccountType() != null) {
                  existingBankAccount.setAccountType(dto.getAccountType());
                }
                if (dto.getCustomerId() != null) {
                  existingBankAccount.setCustomerId(dto.getCustomerId());
                }

                return existingBankAccount;
              });
            })
            .flatMap(updatedBankAccount -> bankAccountRepo.save(updatedBankAccount))
            .map(AccountUtil::entityToDto);
  }


}
