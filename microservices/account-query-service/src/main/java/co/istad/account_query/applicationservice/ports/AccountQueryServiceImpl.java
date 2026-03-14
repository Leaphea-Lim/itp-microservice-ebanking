package co.istad.account_query.applicationservice.ports;

import co.istad.account_query.applicationservice.dto.AccountQueryResponse;
import co.istad.account_query.applicationservice.mapper.AccountAppDataMapper;
import co.istad.account_query.applicationservice.ports.input.service.AccountQueryService;
import co.istad.account_query.applicationservice.ports.output.repository.AccountQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountQueryServiceImpl implements AccountQueryService {

    private final AccountQueryRepository accountQueryRepository;
    private final AccountAppDataMapper accountAppDataMapper;

    @Override
    public Mono<AccountQueryResponse> getByAccountId(UUID accountId) {

        return accountQueryRepository
                .findById(accountId)
                .map(accountAppDataMapper::accountToAccountQueryResponse);
    }
}
