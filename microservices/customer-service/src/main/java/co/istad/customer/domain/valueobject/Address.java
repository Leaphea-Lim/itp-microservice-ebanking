package co.istad.customer.domain.valueobject;

import java.util.UUID;

public record Address(
        UUID addressId,
        String line,
        String city,
        String country,
        String zipCode
) {
}
