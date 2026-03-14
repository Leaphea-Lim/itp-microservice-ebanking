package co.istad.account.domain.command;

import co.istad.common.domain.valueObject.AccountId;
import co.istad.common.domain.valueObject.Money;
import co.istad.common.domain.valueObject.TransactionId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record WithdrawMoneyCommand(
        @TargetAggregateIdentifier
        AccountId accountId,
//        CustomerId customerId,
        TransactionId transactionId,
        Money amount,
        String remark

        ) {

}
