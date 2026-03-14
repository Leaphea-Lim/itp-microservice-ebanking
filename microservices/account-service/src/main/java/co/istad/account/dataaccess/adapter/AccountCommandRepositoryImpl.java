package co.istad.account.dataaccess.adapter;

import co.istad.account.applicationservice.ports.output.repository.AccountCommandRepository;
import co.istad.account.dataaccess.entity.AccountEntity;
import co.istad.account.dataaccess.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
//implement frm output port
public class AccountCommandRepositoryImpl implements AccountCommandRepository {

    private final AccountRepository accountRepository;

    @Override
    public AccountEntity save(AccountEntity accountEntity) {

        return accountRepository.save(accountEntity);
    }

    @Override
    public Optional<AccountEntity> findByAccountId(UUID accountId) {

        return accountRepository.findByAccountId(accountId);
    }
}
