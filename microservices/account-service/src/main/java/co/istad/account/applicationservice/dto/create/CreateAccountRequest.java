package co.istad.account.applicationservice.dto.create;

import co.istad.common.domain.valueObject.*;
import jakarta.validation.constraints.NotNull;

public record CreateAccountRequest(

        @NotNull
        String accountNumber,
        @NotNull
        String accountHolder,
        @NotNull
        CustomerId customerId,
        @NotNull
        AccountTypeCode accountTypeCode,
        @NotNull
        BranchId branchId,
        @NotNull
        Money initialBalance,
        @NotNull
        AccountStatus status
//        @NotNull
//        String createBy
) {
}
