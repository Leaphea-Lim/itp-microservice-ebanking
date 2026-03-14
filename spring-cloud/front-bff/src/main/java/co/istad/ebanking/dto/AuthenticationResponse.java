package co.istad.ebanking.dto;

import lombok.Builder;

@Builder
public record AuthenticationResponse(
        Boolean isAuthenticated
) {
}

