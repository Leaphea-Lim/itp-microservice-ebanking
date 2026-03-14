package co.istad.account.applicationservice.dto.withdraw;

import co.istad.common.domain.valueObject.AccountId;
import co.istad.common.domain.valueObject.Money;
import co.istad.common.domain.valueObject.TransactionId;
import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record WithdrawMoneyResponse(
        AccountId accountId,
        TransactionId transactionId,
        Money amount,
        Money newBalance,
        String remark,
        ZonedDateTime createdAt,
        String message
) {}