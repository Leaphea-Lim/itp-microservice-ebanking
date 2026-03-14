package co.istad.customer.application.dto.update;

import jakarta.validation.constraints.NotBlank;

public record ChangePhoneNumberRequest(
        @NotBlank
        String phoneNumber

) {

}
