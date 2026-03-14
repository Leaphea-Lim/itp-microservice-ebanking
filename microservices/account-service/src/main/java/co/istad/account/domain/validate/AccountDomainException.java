package co.istad.account.domain.validate;

import co.istad.common.domain.exception.DomainException;

public class AccountDomainException extends DomainException {
    public AccountDomainException(String message) {
        super(message);
    }

    public AccountDomainException(String message, Throwable throwable){
        super(message, throwable);
    }
}
