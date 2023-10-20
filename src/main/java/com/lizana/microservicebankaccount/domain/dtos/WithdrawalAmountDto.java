package com.lizana.microservicebankaccount.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawalAmountDto {


        private String withdrawalId;
        private BigDecimal amount;
        private Date dateWithdrawal;
}
