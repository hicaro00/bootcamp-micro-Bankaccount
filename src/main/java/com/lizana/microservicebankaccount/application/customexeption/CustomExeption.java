package com.lizana.microservicebankaccount.application.customexeption;

public class CustomExeption extends RuntimeException {
  public CustomExeption(String message) {
    super(message);
  }
}
