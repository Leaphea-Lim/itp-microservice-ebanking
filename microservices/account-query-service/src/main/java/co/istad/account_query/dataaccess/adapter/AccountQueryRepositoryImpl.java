package co.istad.account_query.dataaccess.adapter;

import co.istad.account_query.applicationservice.ports.output.repository.AccountQueryRepository;
import co.istad.account_query.dataaccess.entity.AccountEntity;
import co.istad.account_query.dataaccess.mapper.AccountDataAccessMapper;
import co.istad.account_query.dataaccess.repository.AccountQueryReactiveRepository;
import co.istad.account_query.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AccountQueryRepositoryImpl implements AccountQueryRepository {

    private final AccountQueryReactiveRepository accountQueryReactiveRepository;
    private final AccountDataAccessMapper accountDataAccessMapper;

    @Override
    public Mono<Account> save(Account account) {

        AccountEntity accountEntity =
                accountDataAccessMapper.accountToAccountEntity(account);

        return accountQueryReactiveRepository
                .save(accountEntity)
                .map(accountDataAccessMapper::accountEntityToAccount);
    }

    @Override
    public Mono<Account> findById(UUID accountId) {
        return accountQueryReactiveRepository
                .findById(accountId)
                .map(accountDataAccessMapper::accountEntityToAccount);
    }
}
