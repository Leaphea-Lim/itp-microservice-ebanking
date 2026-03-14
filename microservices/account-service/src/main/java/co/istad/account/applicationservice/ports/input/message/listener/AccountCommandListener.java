package co.istad.account.applicationservice.ports.input.message.listener;

import co.istad.account.domain.event.AccountFrozenEvent;
import co.istad.account.domain.event.MoneyDepositedEvent;
import co.istad.account.domain.event.MoneyWithdrawnEvent;
import co.istad.common.domain.event.AccountCreatedEvent;

public interface AccountCommandListener {
    void onAccountCreatedEvent(AccountCreatedEvent accountCreatedEvent);
    void onMoneyDepositedEvent(MoneyDepositedEvent moneyDepositedEvent);
    void onMoneyWithdrawnEvent(MoneyWithdrawnEvent moneyWithdrawnEvent);
    void onAccountFrozenEvent(AccountFrozenEvent accountFrozenEvent);
}
