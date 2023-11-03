package com.lizana.microservicebankaccount.domain.entity;

import com.lizana.microservicebankaccount.domain.dtos.DepositAmountDto;
import com.lizana.microservicebankaccount.domain.dtos.Signatory;
import com.lizana.microservicebankaccount.domain.dtos.WithdrawalAmountDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "bankAccount")
public class BankAccount {
  @Id
  private String accountNumber; //numeor de la cuenta
  private String customerId;
  private String accountType;  // ahorro //cuenta corriente // plazo fijo
  private BigDecimal balance;  // saldo de la cuenta
  private int monthlyMovementLimit; //limite de movimientos mensuales
  private double maintenanceFee; //tarifa de mantenimiento
  private Date maturityDate;  //fecha de vencimiento
  private List<Signatory> signatoryes = new ArrayList<>(); // personas firmantes o titular de la cuenta
  private List<DepositAmountDto> deposits = new ArrayList<>();  /// lista de depostos
  private List<WithdrawalAmountDto> withdrawalAmounts = new ArrayList<>(); //lista de retiros
  private double interestRate; //tasa de interes de la cuenta
  private int numberTransactions; //numoero maximo de transacciones
  private double transactionFee; //comision por trnasacciiones
  private LocalDate openingDate; // fecha de apertura de la cuenta
  private List<String> alerts = new ArrayList<>();// "low_balance","large_transaction" tipos de alerta que podria tener o emviarse al clietne
  private boolean temporaryBlock; //true or false  cuenta bloqueada
  private String accountStatus; //vip|normal
  private BigDecimal availableBalance; // saldo disponible podria ser diferente devido a retenciones o bloqueos por deuda
  private List<String> bankReferences = new ArrayList<>();//"loan_account",  "credit_card" referencia a otros productos asociados a la cuenta

  // Descripción de campos:
  // - tipoDeCuenta: Indica el tipo de cuenta.
  // - numeroDeCuenta: Número de cuenta único.
  // - saldo: Saldo actual de la cuenta.
  // - limiteDeMovimientosMensuales: Límite máximo de movimientos mensuales.
  // - comisionDeMantenimiento: Comisión de mantenimiento (0 para cuentas de ahorro).
  // - fechaDeVencimiento: Fecha de vencimiento (null para cuentas de ahorro).
  // - diaEspecificoDeMovimiento: Día específico de movimiento (null para cuentas de ahorro).
  // - TitularDeCuenta: Clase interna que representa al titular de la cuenta.
  // - historialDeTransacciones: Historial de transacciones realizadas en la cuenta.
  // - tasaDeInteres: Tasa de interés (para cuentas de ahorro o plazo fijo).
  // - fechaDeApertura: Fecha de apertura de la cuenta.
  // - alertas: Preferencias de notificación o alertas personalizadas.
  // - bloqueoTemporal: Indica si la cuenta está temporalmente bloqueada.
  // - estadoDeLaCuenta: Estado actual de la cuenta (activo, inactivo, bloqueado, etc.).
  // - saldoDisponible: Saldo disponible para retiros o transacciones.
  // - referenciasBancarias: Referencias a otros productos o cuentas bancarias.
}

