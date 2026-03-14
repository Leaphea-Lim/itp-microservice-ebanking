package co.istad.account.domain.event;

import co.istad.common.domain.valueObject.AccountId;
import co.istad.common.domain.valueObject.Money;
import co.istad.common.domain.valueObject.TransactionId;
import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record MoneyDepositedEvent(
        AccountId accountId,
//        CustomerId customerId,
        TransactionId transactionId,
        Money amount,
        Money newBalance,
        String remark,
        ZonedDateTime createdAt
) {
}
