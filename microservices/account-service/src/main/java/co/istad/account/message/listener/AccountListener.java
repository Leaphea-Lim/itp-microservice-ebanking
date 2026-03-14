package co.istad.account.message.listener;

import co.istad.account.applicationservice.ports.input.message.listener.AccountCommandListener;
import co.istad.account.applicationservice.ports.output.repository.AccountCommandRepository;
import co.istad.account.dataaccess.entity.AccountEntity;
import co.istad.account.dataaccess.entity.AccountTypeEntity;
import co.istad.account.dataaccess.entity.CustomerEntity;
//import co.istad.account.dataaccess.repository.AccountRepository;
import co.istad.account.dataaccess.repository.AccountTypeRepository;
import co.istad.account.dataaccess.repository.CustomerRepository;
import co.istad.account.applicationservice.mapper.AccountApplicationMapper;

import co.istad.account.domain.event.AccountFrozenEvent;
import co.istad.account.domain.event.MoneyDepositedEvent;
import co.istad.account.domain.event.MoneyWithdrawnEvent;
import co.istad.account.infrastructor.client.CustomerClient;
import co.istad.account.infrastructor.client.dto.CustomerResponse;
import co.istad.common.domain.event.AccountCreatedEvent;
import co.istad.common.domain.valueObject.AccountStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
@Slf4j
@ProcessingGroup("account-group")
public class AccountListener implements AccountCommandListener {

//    private final AccountRepository accountRepository;
    //calling through the port instead of jpa
    private final AccountCommandRepository accountCommandRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final AccountApplicationMapper accountApplicationMapper;
    private final CustomerClient customerClient;
    private final CustomerRepository customerRepository;

    //create account
    @Override
    @EventHandler
    public void onAccountCreatedEvent(AccountCreatedEvent accountCreatedEvent){
        log.info("on AccountCreatedEvent: {} ", accountCreatedEvent);

        //fetch customer from customer service and save locally
        CustomerResponse customer = customerClient
                .getCustomerById(accountCreatedEvent.customerId().getValue());

        //check if customer already exists locally before saving
        if (!customerRepository.existsById(customer.customerId())) {
            CustomerEntity customerEntity = new CustomerEntity();
            customerEntity.setCustomerId(customer.customerId());
            customerEntity.setCustomerName(
                    customer.name().givenName() + " " + customer.name().familyName());
            customerEntity.setPhoneNumber(customer.phoneNumber());

            customerRepository.save(customerEntity);
            log.info("Customer saved locally: {}", customer.customerId());
        }

        AccountEntity accountEntity = accountApplicationMapper.accountCreateEventToAccountEntity(accountCreatedEvent);

        AccountTypeEntity accountTypeEntity = accountTypeRepository
                .findByCode(accountCreatedEvent.accountTypeCode())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Account Type Not Found"));


        accountEntity.setAccountType(accountTypeEntity);
        accountEntity.setMoney(accountCreatedEvent.initialBalance());
        accountEntity.setAccountStatus(AccountStatus.ACTIVE);
//        accountRepository.save(accountEntity);
        accountCommandRepository.save(accountEntity);
        log.info("Account Saved: {}", accountEntity.getAccountId());



    }

    //deposit
    @Override
    @EventHandler
    public void onMoneyDepositedEvent(MoneyDepositedEvent moneyDepositedEvent){
        log.info("on MoneyDepositEvent: {}", moneyDepositedEvent);

        AccountEntity accountEntity = accountCommandRepository
                .findByAccountId(moneyDepositedEvent
                        .accountId()
                        .value())
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found."));

        accountEntity.setMoney(moneyDepositedEvent.newBalance());
//        accountRepository.save(accountEntity);
          accountCommandRepository.save(accountEntity);
        log.info("Account Balance Updated: {}", moneyDepositedEvent.newBalance());
    }

    //withdraw
    @Override
    @EventHandler
    public void onMoneyWithdrawnEvent(MoneyWithdrawnEvent event) {
        log.info("on MoneyWithdrawnEvent: {}", event);

        AccountEntity accountEntity = accountCommandRepository
                .findByAccountId(event.accountId().value())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Account not found"));

        accountEntity.setMoney(event.newBalance());
//        accountRepository.save(accountEntity);
        accountCommandRepository.save(accountEntity);
        log.info("Account balance updated after withdraw: {}", event.newBalance());
    }

    //freeze account
    @Override
    @EventHandler
    public void onAccountFrozenEvent(AccountFrozenEvent event) {
        log.info("on AccountFrozenEvent: {}", event);

        AccountEntity accountEntity = accountCommandRepository
                .findByAccountId(event.accountId().value())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Account not found"));

        accountEntity.setAccountStatus(event.newStatus());
//        accountRepository.save(accountEntity);
        accountCommandRepository.save(accountEntity);
        log.info("Account status updated to: {}", event.newStatus());
    }
}

