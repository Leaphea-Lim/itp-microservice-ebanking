package co.istad.account_query.applicationservice.dto;

import java.util.UUID;

public record AccountQueryResponse(
        UUID accountId,
        String accountNo
) {
}
