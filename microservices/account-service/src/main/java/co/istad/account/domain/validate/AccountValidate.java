package co.istad.account.domain.validate;

import co.istad.common.domain.valueObject.AccountTypeCode;
import co.istad.common.domain.valueObject.Currency;
import co.istad.common.domain.valueObject.Money;

import java.math.BigDecimal;

public class AccountValidate {

    public static void validateAccountNumber(String accountNumber){
        if(accountNumber == null){
            throw new AccountDomainException("Account number cannot be null.");
        }

        if(!accountNumber.matches("^\\d{9}$")){
            throw new AccountDomainException("Account number is invalid.");
        }
    }

    public static void validateAccountTypeCode(AccountTypeCode accountTypeCode){
        if(accountTypeCode != AccountTypeCode.SAVING){
            throw new AccountDomainException("Account type can only be SAVING.");
        }
    }

    public static void validateInitialBalance(Money initialBalance){
        if (initialBalance.currency() == Currency.USD){
            Money minInitialBalance = new Money(BigDecimal.valueOf(10), Currency.USD);
            if(initialBalance.isLessThan(minInitialBalance)){
                throw new AccountDomainException("Create new account is required 10$.");
            }
        }else if(initialBalance.currency() == Currency.KHR){
            Money minInitialBalance = new Money(BigDecimal.valueOf(40000), Currency.KHR);
            if (initialBalance.isLessThan(minInitialBalance)) {
                throw new AccountDomainException("Create new account is required 4000KHR. ");
            }
        }
    }

    public static void validateDepositAmount(Money amount){
        if(amount.amount().compareTo(BigDecimal.ZERO) <= 0){
            throw new AccountDomainException("Deposit must be greater than 0.");
        }
    }

    public static void validateWithdrawAmount(Money amount, Money balance) {
        if (amount.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new AccountDomainException("Withdraw amount must be greater than 0");
        }
        if (amount.isGreaterThan(balance)) {
            throw new AccountDomainException("Insufficient balance");
        }
    }

}
