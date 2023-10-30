package com.lizana.microservicebankaccount.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Signatory {

    private String customerId;
    private String name;
    private String address;
    private String identifier;



}
