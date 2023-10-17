package com.lizana.microservicebankaccount.infrastructure.outpupport;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepo extends ReactiveMongoRepository {
}
