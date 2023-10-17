package com.lizana.microservicebankaccount.domain.utilsmaper;

import com.lizana.microservicebankaccount.domain.dtos.BankAccountDto;
import com.lizana.microservicebankaccount.domain.entity.BankAccount;
import org.springframework.beans.BeanUtils;

public class AccountUtil {

    private AccountUtil(){
    }

    public static BankAccountDto entityToDto(BankAccount bankAccount){
           BankAccountDto bankAccountDto = new BankAccountDto();
           BeanUtils.copyProperties(bankAccount ,bankAccountDto);
           return bankAccountDto;
     }

    public static BankAccount dtoToEntity(BankAccountDto bankAccountDto){
        BankAccount bankAccount = new BankAccount();
        BeanUtils.copyProperties(bankAccountDto ,bankAccount);
        return bankAccount;
        }
}
