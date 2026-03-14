package co.istad.customer.application.dto.create;

import co.istad.common.domain.valueObject.CustomerSegmentId;
import co.istad.customer.domain.valueobject.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CreateCustomerResponse(

        @NotNull
        UUID customerId,

        @NotBlank
        String message

) {
}
