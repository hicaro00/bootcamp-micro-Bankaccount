package com.lizana.microservicebankaccount.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositAmountDto {


        private String depositoId;
        private BigDecimal amount;
        private Date depositDate;

}
