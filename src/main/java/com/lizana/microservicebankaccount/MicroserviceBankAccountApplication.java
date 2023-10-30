package com.lizana.microservicebankaccount;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableAutoConfiguration
@EnableEurekaClient
@Slf4j
public class MicroserviceBankAccountApplication {

	@Bean
	public WebClient webClient(){
		return WebClient.builder().build();
	}



	public static void main(String[] args) {
		SpringApplication.run(MicroserviceBankAccountApplication.class, args);
	}

}
