package co.istad.account.dataaccess.init;

import co.istad.account.dataaccess.entity.AccountTypeEntity;
import co.istad.account.dataaccess.repository.AccountTypeRepository;
import co.istad.common.domain.valueObject.AccountTypeCode;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class AccountTypeInit {

    private final AccountTypeRepository accountTypeRepository;

    @PostConstruct
    public void initAccountType() {
        if (accountTypeRepository.count() == 0) {
            Arrays.stream(AccountTypeCode.values()).forEach(code -> {
                AccountTypeEntity entity = new AccountTypeEntity();
                entity.setAccountTypeId(UUID.randomUUID());
                entity.setCode(code);
                accountTypeRepository.save(entity);
            });
            log.info("Account types initialized successfully");
        }
    }
}