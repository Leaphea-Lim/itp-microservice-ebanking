package co.istad.customer.domain.event;

import co.istad.common.domain.valueObject.CustomerId;
import co.istad.common.domain.valueObject.CustomerSegmentId;
import co.istad.customer.domain.valueobject.*;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CustomerCreatedEvent(
        CustomerId customerId,
        CustomerName name,
        CustomerEmail email,
        CustomerGender gender,
        String phoneNumber,
        LocalDate dob,
        Kyc kyc,
        Address address,
        Contact contact,
        CustomerSegmentId customerSegmentId
) {
}
