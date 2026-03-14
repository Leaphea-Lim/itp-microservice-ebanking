package co.istad.account.applicationservice.dto.freeze;

import co.istad.common.domain.valueObject.AccountId;
import jakarta.validation.constraints.NotNull;

public record FreezeAccountRequest(
        @NotNull
        AccountId accountId,
        @NotNull
        String remark,
        @NotNull
        String requestedBy

//        AccountStatus previousStatus,
//        AccountStatus newStatus,
//        String reason
) {
}
