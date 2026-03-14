package co.istad.account.applicationservice.dto.deposit;

import co.istad.common.domain.valueObject.AccountId;
import co.istad.common.domain.valueObject.CustomerId;
import co.istad.common.domain.valueObject.Money;
import co.istad.common.domain.valueObject.TransactionId;
import jakarta.validation.constraints.NotNull;

public record DepositMoneyRequest(
        @NotNull
        AccountId accountId,
        @NotNull
        CustomerId customerId,
        @NotNull
        TransactionId transactionId,
        @NotNull
        Money amount,
        @NotNull
        String remark
) {
}
