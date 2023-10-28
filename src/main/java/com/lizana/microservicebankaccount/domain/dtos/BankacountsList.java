package com.lizana.microservicebankaccount.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankacountsList {


  private String accountNumber; //Ahorro||Cuenta Corriente|| plazo fijo
  private String balance;  //bance general de la cuenta
  private String accountType; // tipóde cuenta AHORRO||CUETNA CORREITNE||PLAZO FIJO
  private String accountId; //id del la cuenta creada asociada al usuario


}
