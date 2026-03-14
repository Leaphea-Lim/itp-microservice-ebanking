package co.istad.account.applicationservice.dto.freeze;

import co.istad.common.domain.valueObject.AccountId;
import co.istad.common.domain.valueObject.AccountStatus;
import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record FreezeAccountResponse(
        AccountId accountId,
        AccountStatus previousStatus,
        AccountStatus newStatus,
        String reason,
        String requestedBy,
        ZonedDateTime createdAt,
        String message
) {}