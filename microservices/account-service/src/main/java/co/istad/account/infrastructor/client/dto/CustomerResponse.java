package co.istad.account.infrastructor.client.dto;

import java.util.UUID;

public record CustomerResponse(
        UUID customerId,
        Name name,
        String phoneNumber
) {
    public record Name(
            String familyName,
            String givenName
    ) {}
}