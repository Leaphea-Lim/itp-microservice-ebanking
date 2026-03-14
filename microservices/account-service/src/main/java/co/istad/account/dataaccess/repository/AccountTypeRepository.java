package co.istad.account.dataaccess.repository;

import co.istad.account.dataaccess.entity.AccountTypeEntity;
import co.istad.common.domain.valueObject.AccountTypeCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountTypeEntity, UUID> {
    Optional<AccountTypeEntity> findByCode(AccountTypeCode code);
}
