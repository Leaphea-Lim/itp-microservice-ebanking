package co.istad.account_query.applicationservice.ports;

import co.istad.account_query.applicationservice.mapper.AccountAppDataMapper;
import co.istad.account_query.applicationservice.ports.input.message.listener.AccountMessageListener;
import co.istad.account_query.applicationservice.ports.output.repository.AccountQueryRepository;
import co.istad.account_query.domain.Account;
import co.istad.common.domain.event.AccountCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AccountMessageListenerImpl implements AccountMessageListener {

    private final AccountQueryRepository accountQueryRepository;
    private final AccountAppDataMapper accountAppDataMapper;

    @Override
    public void onAccountCreatedEvent(AccountCreatedEvent accountCreatedEvent) {

        Account account = accountAppDataMapper.accountCreatedEventToAccount(accountCreatedEvent);
        accountQueryRepository.save(account)
                .doOnSuccess(data -> log.info("Saved account = {} successfully", account))
                .doOnError(error -> log.error("Failed to save account: {}", error.getMessage()))
                .subscribe();
    }
}
