package co.istad.account_query.message.listener.axonkafka;

import co.istad.account_query.applicationservice.ports.input.message.listener.AccountMessageListener;
import co.istad.common.domain.event.AccountCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@ProcessingGroup("account-group")
public class AccountAxonKafkaListener {

    private final AccountMessageListener accountMessageListener;

    @EventHandler
    public void on (AccountCreatedEvent accountCreatedEvent){
        log.info("on account created event: {}", accountCreatedEvent);
        accountMessageListener.onAccountCreatedEvent(accountCreatedEvent);
    }
}
