package com.lizana.microservicebankaccount.domain.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositAmountDto {


        private String movementId;
        private BigDecimal amount;
        private String originMovement;
        private LocalDate dateOfMovement;
        private String authorizationCode;
}
