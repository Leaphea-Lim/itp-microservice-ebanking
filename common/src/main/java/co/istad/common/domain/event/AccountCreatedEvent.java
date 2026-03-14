package co.istad.common.domain.event;

import co.istad.common.domain.valueObject.*;
import lombok.Builder;
import java.time.ZonedDateTime;

@Builder
public record AccountCreatedEvent(
        AccountId accountId,
        String accountNumber,
        String accountHolder,
        CustomerId customerId,
        AccountTypeCode accountTypeCode,
        AccountStatus status,
        BranchId branchId,
        Money initialBalance,
        ZonedDateTime createdAt,
        String createdBy
) {
}

