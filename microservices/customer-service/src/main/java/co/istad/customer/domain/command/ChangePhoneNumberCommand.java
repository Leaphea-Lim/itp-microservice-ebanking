package co.istad.customer.domain.command;

import co.istad.common.domain.valueObject.CustomerId;
import lombok.Builder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
public record ChangePhoneNumberCommand(

        @TargetAggregateIdentifier
        CustomerId customerId,
        String phoneNumber
) {

}
