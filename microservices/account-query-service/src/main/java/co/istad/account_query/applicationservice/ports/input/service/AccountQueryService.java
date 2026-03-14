package co.istad.account_query.applicationservice.ports.input.service;

import co.istad.account_query.applicationservice.dto.AccountQueryResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface AccountQueryService {

    Mono<AccountQueryResponse> getByAccountId(UUID accountId);
}
