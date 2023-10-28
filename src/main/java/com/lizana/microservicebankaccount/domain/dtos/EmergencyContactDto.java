package com.lizana.microservicebankaccount.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmergencyContactDto {

    private String name;
    private String relationship;
    private String phoneNumber;
}
