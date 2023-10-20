package com.lizana.microservicebankaccount.domain.dtos;

import com.lizana.microservicebankaccount.domain.objet.DepositAmount;
import com.lizana.microservicebankaccount.domain.objet.WithdrawalAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDto {

        @Id
        private String accountId;
        private String customerId;
        private String accountType;
        private BigDecimal balance;
        private List<DepositAmountDto> deposits;
        private List<WithdrawalAmountDto> withdrawalAmounts;

}
