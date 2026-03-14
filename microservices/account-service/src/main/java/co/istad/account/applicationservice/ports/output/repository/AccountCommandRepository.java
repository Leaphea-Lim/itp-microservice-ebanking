package co.istad.account.applicationservice.ports.output.repository;



import co.istad.account.dataaccess.entity.AccountEntity;

import java.util.Optional;
import java.util.UUID;

//this is where database operations
public interface AccountCommandRepository {

    AccountEntity save(AccountEntity accountEntity);
    Optional<AccountEntity> findByAccountId(UUID accountId);

}
