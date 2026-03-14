package co.istad.account.domain.event;

import co.istad.common.domain.valueObject.AccountId;
import co.istad.common.domain.valueObject.AccountStatus;
import co.istad.common.domain.valueObject.CustomerId;
import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record AccountFrozenEvent(
        AccountId accountId,
        CustomerId customerId,
        AccountStatus previousStatus,
        AccountStatus newStatus,
        String reason,
        String requestedBy,
        ZonedDateTime createdAt
) {
}
