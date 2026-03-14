package co.istad.account.rest.exception;

import co.istad.account.domain.validate.AccountDomainException;
import co.istad.common.application.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class AccountException {
    @ExceptionHandler(AccountDomainException.class)
    public ResponseEntity<?> handleAccountDomainException(AccountDomainException e){
        return new ResponseEntity<>(ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .message(e.getMessage())
                .timestamp(ZonedDateTime.now())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
