package com.lizana.microservicebankaccount.domain.entity;

import com.lizana.microservicebankaccount.domain.objet.DepositAmount;
import com.lizana.microservicebankaccount.domain.objet.WithdrawalAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "bankAccount")
public class BankAccount {
    @Id
    private String accountId;
    private String customerId;
    private String accountType;
    private BigDecimal balance;
    private List<DepositAmount> deposits;
    private List<WithdrawalAmount> withdrawalAmounts;

}
