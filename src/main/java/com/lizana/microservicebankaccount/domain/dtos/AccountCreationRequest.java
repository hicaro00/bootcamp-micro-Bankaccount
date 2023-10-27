package com.lizana.microservicebankaccount.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreationRequest {


    private String customerid;//id del documento customer en mongo db
    private String accountType;//Ahorro||Cuenta Corriente|| plazo fijo
    private String docnumber; //numero del ruc o dni
    private String name;



}
