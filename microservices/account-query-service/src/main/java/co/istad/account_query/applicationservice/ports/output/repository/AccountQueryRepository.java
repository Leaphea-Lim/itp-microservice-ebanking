package co.istad.account_query.applicationservice.ports.output.repository;

import co.istad.account_query.domain.Account;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface AccountQueryRepository {


    //Save account
    Mono<Account> save(Account account);
    Mono<Account> findById(UUID accountId);
}
