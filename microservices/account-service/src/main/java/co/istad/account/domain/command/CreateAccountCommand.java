package co.istad.account.domain.command;

import co.istad.common.domain.valueObject.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;


public record CreateAccountCommand(
        @TargetAggregateIdentifier
        AccountId accountId,
        String accountNumber,
        String accountHolder,
        CustomerId customerId,
        AccountTypeCode accountTypeCode,
        BranchId branchId,
        Money initialBalance

) {
}
