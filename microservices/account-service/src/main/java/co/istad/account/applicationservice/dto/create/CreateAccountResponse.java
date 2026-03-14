package co.istad.account.applicationservice.dto.create;

import lombok.Builder;

@Builder
public record CreateAccountResponse(
        String accountId,
        String accountNumber,
        String accountHolder,
//        CustomerId customerId,
//        AccountTypeCode accountTypeCode,
//        BranchId branchId,
//        Money initialBalance,
//        AccountStatus accountStatus,
//        ZonedDateTime createdAt,
//        String createdBy,
        String message
) {
}
