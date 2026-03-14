package co.istad.common.domain.valueObject;

import co.istad.common.domain.exception.DomainException;

import java.math.BigDecimal;


public record Money(
        BigDecimal amount,
        Currency currency
) {

    public boolean isLessThan(Money otherAmount){
        checkSameCurrency(otherAmount.currency);
        return this.amount.compareTo(otherAmount.amount) < 0;
    }

    public boolean isLessThanOrEqual(Money otherAmount){
        checkSameCurrency(otherAmount.currency);
        return this.amount.compareTo(otherAmount.amount) <= 0;
    }

    public boolean isGreaterThan(Money otherAmount){
        checkSameCurrency(otherAmount.currency);
        return this.amount.compareTo(otherAmount.amount) > 0;
    }

    public boolean isGreaterThanOrEqual(Money otherAmount){
        checkSameCurrency(otherAmount.currency);
        return this.amount.compareTo(otherAmount.amount) >= 0;
    }

    public void checkSameCurrency(Currency otherCurrency){
       if (this.currency != otherCurrency){
           throw new DomainException("Currency does not match.");
       }
    }

    public Money add(Money other){
        if (this.currency != other.currency){
            throw new DomainException("Cannot add amount with different currencies.");
        }

        return new Money(this.amount.add(other.amount), this.currency);
    }

    public Money subtract(Money other) {
        if (this.currency != other.currency) {
            throw new DomainException("Cannot subtract amount with different currencies.");
        }
        return new Money(this.amount.subtract(other.amount), this.currency);
    }

}
