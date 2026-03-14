package co.istad.account_query.applicationservice.ports.input.message.listener;

import co.istad.common.domain.event.AccountCreatedEvent;

public interface AccountMessageListener {

    void onAccountCreatedEvent(AccountCreatedEvent accountCreatedEvent);

}
