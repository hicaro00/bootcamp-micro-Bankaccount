package com.lizana.microservicebankaccount.infrastructure.inputport;

import reactor.core.publisher.Mono;

public interface WebClientServicePost {

  <T> Mono<T> createNewAccount(T dto, String endpoint, Class<T> responseType);
}
