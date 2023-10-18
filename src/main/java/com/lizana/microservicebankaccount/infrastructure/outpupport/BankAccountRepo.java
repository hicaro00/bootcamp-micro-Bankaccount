package com.lizana.microservicebankaccount.infrastructure.outpupport;

import com.lizana.microservicebankaccount.domain.entity.BankAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepo extends ReactiveMongoRepository<BankAccount,String> {
}
