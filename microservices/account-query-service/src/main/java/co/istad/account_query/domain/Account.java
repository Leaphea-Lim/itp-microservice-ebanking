package co.istad.account_query.domain;

import co.istad.common.domain.valueObject.AccountStatus;
import co.istad.common.domain.valueObject.Money;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
public class Account {
    private UUID accountId;

    private UUID customerId;
    private UUID branchId;

    private String accountNumber;
    private String accountHolder;

    private Money money;

    private UUID accountTypeId;
    private AccountStatus status;

    private ZonedDateTime createdAt;
    private String createdBy;
    private ZonedDateTime updatedAt;
    private String updatedBy;
}
