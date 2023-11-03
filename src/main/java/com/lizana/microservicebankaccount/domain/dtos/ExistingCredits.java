package com.lizana.microservicebankaccount.domain.dtos;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExistingCredits {
  private String creditId;
  private String creditCardId;
  private String bankAccountID;
  private BigDecimal availableCredit;

}
