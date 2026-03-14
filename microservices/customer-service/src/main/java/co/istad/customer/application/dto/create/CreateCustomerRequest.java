package co.istad.customer.application.dto.create;

import co.istad.common.domain.valueObject.CustomerSegmentId;
import co.istad.customer.domain.valueobject.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateCustomerRequest(

        @NotNull
        CustomerName name,

        @NotNull
        CustomerEmail email,

        @NotNull
        LocalDate dob,

        @NotNull
        CustomerGender gender,

        @NotBlank
        String phoneNumber,

        @NotNull
        Kyc kyc,

        @NotNull
        Address address,

        @NotNull
        Contact contact,

        @NotNull
        CustomerSegmentId customerSegmentId
) {
}
