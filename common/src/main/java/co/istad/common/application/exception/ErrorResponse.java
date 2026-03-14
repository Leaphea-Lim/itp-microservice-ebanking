package co.istad.common.application.exception;

import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record ErrorResponse(
        String status,
        String code,
        String message,
        ZonedDateTime timestamp
) {
}
