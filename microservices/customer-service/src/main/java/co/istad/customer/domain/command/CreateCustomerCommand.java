package co.istad.customer.domain.command;

import co.istad.common.domain.valueObject.CustomerId;
import co.istad.common.domain.valueObject.CustomerSegmentId;
import co.istad.customer.domain.valueobject.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDate;

public record CreateCustomerCommand(
        @TargetAggregateIdentifier
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
