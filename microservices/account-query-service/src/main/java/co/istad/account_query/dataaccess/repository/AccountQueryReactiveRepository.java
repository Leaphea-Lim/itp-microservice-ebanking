package co.istad.account_query.dataaccess.repository;


import co.istad.account_query.dataaccess.entity.AccountEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface AccountQueryReactiveRepository extends R2dbcRepository<AccountEntity, UUID> {
}
