package co.istad.account.applicationservice.dto.deposit;

import co.istad.common.domain.valueObject.AccountId;
import co.istad.common.domain.valueObject.Money;
import co.istad.common.domain.valueObject.TransactionId;
import lombok.Builder;

@Builder
public record DepositMoneyResponse(
        AccountId accountId,
//        CustomerId customerId,
        TransactionId transactionId,
        Money amount,
//        Money newBalance,
        String remark,
//        ZonedDateTime createdAt,
        String message

) {
}
