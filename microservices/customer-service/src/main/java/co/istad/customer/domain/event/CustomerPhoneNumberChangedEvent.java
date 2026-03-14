package co.istad.customer.domain.event;

import co.istad.common.domain.valueObject.CustomerId;
import lombok.Builder;

@Builder
public record CustomerPhoneNumberChangedEvent(
    CustomerId customerId,
    String phoneNumber
) {
}
