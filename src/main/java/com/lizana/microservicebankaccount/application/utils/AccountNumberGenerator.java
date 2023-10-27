package com.lizana.microservicebankaccount.application.utils;

import java.util.Random;

public class AccountNumberGenerator {

  private static final String ACCOUNT_PREFIX = "159"; //prefijo asigando al banco brando

  public static String generateAccountNumber() {
    // Genera un número de cuenta aleatorio de 11 dígitos
    Random random = new Random();
    int accountNumberSuffix = random.nextInt(900000000) + 100000000; // Rango: 100000000 a 999999999
    return ACCOUNT_PREFIX + String.valueOf(accountNumberSuffix);
  }
}
