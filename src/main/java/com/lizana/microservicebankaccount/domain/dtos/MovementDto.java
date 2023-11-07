package com.lizana.microservicebankaccount.domain.dtos;

import com.lizana.microservicebankaccount.application.customexeption.CustomExeption;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovementDto {

  private String movementId; //id de la operacion
  private String movementType; //transferencia || deposito || pago de servico ||retiro 
  private String originMovement; //desde donde se iso el retiro cajero|| o se hiso un pago hacia donde
  private String destinationMovement;//destino del movimeinto
  private BigDecimal amount; //monto del movimiento
  private LocalDate dateOfMovement; // fecha y hora
  private String descriptionOptional; //Descripción o Motivo del Retiro
  private String transactionStatus;  //process || rechargers || success
  private String authorizationCode; //Código de Autorización en caso sea un monto alto
  private String auditInformation; //Información de Auditoría datos id del firmantoe en caso sea busines
  private String uniqueExternalReference; //Referencia Única Externa en caso sea ventanilla o cajeros
  private double commissionAndRateInformation; //Información de Comisiones y Tarifas:
  private String errorCodesRejection;  //Códigos de Error o Motivos de Rechazo


  public MovementDto(String s) {
    throw new CustomExeption("Ocurrió un error personalizado");
  }

}
