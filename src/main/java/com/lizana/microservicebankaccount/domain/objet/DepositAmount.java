package com.lizana.microservicebankaccount.domain.objet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositAmount {

    private BigDecimal amount;
    private Date depositDate;
}
