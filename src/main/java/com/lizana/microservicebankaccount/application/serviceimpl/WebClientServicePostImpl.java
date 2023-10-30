package com.lizana.microservicebankaccount.application.serviceimpl;

import com.lizana.microservicebankaccount.application.customexeption.CustomExeption;
import com.lizana.microservicebankaccount.infrastructure.inputport.WebClientServicePost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class WebClientServicePostImpl implements WebClientServicePost {
@Autowired
 WebClient webClient;


  @Override
  public <T> Mono<T> createNewAccount(T dto, String endpoint, Class<T> responseType) {

      return webClient.post()
              .uri(endpoint)
              .body(Mono.just(dto), dto.getClass())
              .retrieve()
              .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                      Mono.error(new CustomExeption("Error 4xx")))
              .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                      Mono.error(new CustomExeption("Error 5xx")))
              .bodyToMono(responseType);
    }

}
