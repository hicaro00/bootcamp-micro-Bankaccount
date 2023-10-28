package com.lizana.microservicebankaccount.domain.objet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientStatus {
    private boolean accountHolder;
    private boolean signatory;
}
