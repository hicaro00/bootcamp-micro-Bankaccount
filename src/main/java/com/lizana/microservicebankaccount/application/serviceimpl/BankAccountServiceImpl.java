package com.lizana.microservicebankaccount.application.serviceimpl;

import com.lizana.microservicebankaccount.domain.dtos.BankAccountDto;
import com.lizana.microservicebankaccount.domain.utilsmaper.AccountUtil;
import com.lizana.microservicebankaccount.infrastructure.inputport.BanckAccountService;
import com.lizana.microservicebankaccount.infrastructure.outpupport.BankAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class BankAccountServiceImpl implements BanckAccountService {
    @Autowired
    private BankAccountRepo bankAccountRepo;

    @Override
    public Mono<BankAccountDto> getInfoBanckAccount(String accountId) {
        return bankAccountRepo.findById(accountId).map(AccountUtil::entityToDto);
    }

    @Override
    public Mono<BankAccountDto> putInfoBankAccount(String accountId,Mono<BankAccountDto> bankAccountDto) {

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
    public Mono<BankAccountDto> createdBanckAccount(Mono<BankAccountDto> bankAccountDtoMono ) {

        return bankAccountDtoMono.map(AccountUtil::dtoToEntity)
                .flatMap(bankAccountRepo::insert)
                .map(AccountUtil::entityToDto);
    }
}
