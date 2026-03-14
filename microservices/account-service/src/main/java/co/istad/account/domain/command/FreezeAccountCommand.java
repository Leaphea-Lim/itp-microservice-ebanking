package co.istad.account.domain.command;

import co.istad.common.domain.valueObject.AccountId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record FreezeAccountCommand(
        @TargetAggregateIdentifier
        AccountId accountId,
        String remark,
        String requestedBy
) {
}
