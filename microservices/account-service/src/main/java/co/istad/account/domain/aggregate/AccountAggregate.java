package co.istad.account.domain.aggregate;

import co.istad.account.domain.command.CreateAccountCommand;
import co.istad.account.domain.command.DepositMoneyCommand;
import co.istad.account.domain.command.FreezeAccountCommand;
import co.istad.account.domain.command.WithdrawMoneyCommand;
import co.istad.account.domain.event.AccountFrozenEvent;
import co.istad.account.domain.event.MoneyDepositedEvent;
import co.istad.account.domain.event.MoneyWithdrawnEvent;
import co.istad.account.domain.validate.AccountDomainException;
import co.istad.account.domain.validate.AccountValidate;
import co.istad.common.domain.event.AccountCreatedEvent;
import co.istad.common.domain.valueObject.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.ZonedDateTime;

@Aggregate
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@Slf4j
public class AccountAggregate {
    //value object
    @AggregateIdentifier
    private AccountId accountId;
    private String accountNumber;
    private String accountHolder;
    private CustomerId customerId;
    private BranchId branchId;
    private AccountTypeCode accountTypeCode;
    private Money balance;

    private AccountStatus status;
    private ZonedDateTime createdAt;
    private String createBy;
    private ZonedDateTime updatedAt;

    //create account
    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand){
        log.info("Creating account with command: {}", createAccountCommand);

        //validate account number
        AccountValidate.validateAccountNumber(createAccountCommand.accountNumber());

        //validate account type code
        AccountValidate.validateAccountTypeCode(createAccountCommand.accountTypeCode());

        //validate balance
        AccountValidate.validateInitialBalance(createAccountCommand.initialBalance());


        //create event object
        AccountCreatedEvent accountCreatedEvent =
                AccountCreatedEvent.builder()
                        .accountId(createAccountCommand.accountId())
                        .accountNumber(createAccountCommand.accountNumber())
                        .accountHolder(createAccountCommand.accountHolder())
                        .customerId(createAccountCommand.customerId())
                        .accountTypeCode(createAccountCommand.accountTypeCode())
                        .branchId(createAccountCommand.branchId())
                        .initialBalance(createAccountCommand.initialBalance())
                        .createdAt(ZonedDateTime.now())
                        .createdBy("ADMIN")
                        .status(AccountStatus.ACTIVE)
                        .build();

        //apply aggregate lifecycle
        AggregateLifecycle.apply(accountCreatedEvent);

    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent accountCreatedEvent){

        this.accountId = accountCreatedEvent.accountId();
        this.customerId = accountCreatedEvent.customerId();
        this.accountNumber = accountCreatedEvent.accountNumber();
        this.accountHolder = accountCreatedEvent.accountHolder();
        this.branchId = accountCreatedEvent.branchId();
        this.accountTypeCode = accountCreatedEvent.accountTypeCode();
        this.status = accountCreatedEvent.status();
        this.balance = accountCreatedEvent.initialBalance();
        this.createBy = accountCreatedEvent.createdBy();
        this.createdAt = accountCreatedEvent.createdAt();

    }

    //deposit amount
    @CommandHandler
    public void handle(DepositMoneyCommand depositMoneyCommand){
        log.info("Depositing amount with command: {}", depositMoneyCommand);

        //validate amount
        AccountValidate.validateDepositAmount(depositMoneyCommand.amount());

        // validate account status
        if (this.status != AccountStatus.ACTIVE) {
            throw new AccountDomainException("Cannot deposit to non-active account");
        }

        //event object
        MoneyDepositedEvent moneyDepositedEvent = MoneyDepositedEvent.builder()
                .accountId(depositMoneyCommand.accountId())
//                .customerId(this.customerId)
                .transactionId(depositMoneyCommand.transactionId())
                .amount(depositMoneyCommand.amount())
                .newBalance(this.balance.add(depositMoneyCommand.amount())) //calculate new balance
                .remark(depositMoneyCommand.remark())
                .createdAt(ZonedDateTime.now())
                .build();

        AggregateLifecycle.apply(moneyDepositedEvent);
    }

    @EventSourcingHandler
    public void on(MoneyDepositedEvent moneyDepositedEvent){
        this.balance = moneyDepositedEvent.newBalance();
        this.updatedAt = moneyDepositedEvent.createdAt();
    }

    //withdraw amount
    @CommandHandler
    public void handle(WithdrawMoneyCommand withdrawMoneyCommand){
        log.info("Withdrawing amount with command: {}", withdrawMoneyCommand);

        // validate account status
        if (this.status != AccountStatus.ACTIVE) {
            throw new AccountDomainException("Cannot withdraw from non-active account");
        }

        // validate amount
        AccountValidate.validateWithdrawAmount(withdrawMoneyCommand.amount(), this.balance);

        //event
        MoneyWithdrawnEvent moneyWithdrawnEvent = MoneyWithdrawnEvent.builder()
                .accountId(withdrawMoneyCommand.accountId())
//                .customerId(this.customerId)
                .transactionId(withdrawMoneyCommand.transactionId())
                .amount(withdrawMoneyCommand.amount())
                .newBalance(this.balance.subtract(withdrawMoneyCommand.amount()))
                .remark(withdrawMoneyCommand.remark())
                .createdAt(ZonedDateTime.now())
                .build();

        AggregateLifecycle.apply(moneyWithdrawnEvent);
    }

    @EventSourcingHandler
    public void on(MoneyWithdrawnEvent moneyWithdrawnEvent){
        this.balance = moneyWithdrawnEvent.newBalance();
        this.updatedAt = moneyWithdrawnEvent.createdAt();
    }

    //freeze account
    @CommandHandler
    public void handle(FreezeAccountCommand freezeAccountCommand) {
        log.info("Freezing account with command: {}", freezeAccountCommand);

        // validate account status - cannot freeze already frozen or closed account
        if (this.status == AccountStatus.FREEZE) {
            throw new AccountDomainException("Account is already frozen");
        }
        if (this.status == AccountStatus.CLOSED) {
            throw new AccountDomainException("Cannot freeze a closed account");
        }

        AccountFrozenEvent event = AccountFrozenEvent.builder()
                .accountId(freezeAccountCommand.accountId())
                .customerId(this.customerId)
                .previousStatus(this.status)
                .newStatus(AccountStatus.FREEZE)
                .reason(freezeAccountCommand.remark())
                .requestedBy(freezeAccountCommand.requestedBy())
                .createdAt(ZonedDateTime.now())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AccountFrozenEvent event) {
        this.status = event.newStatus();
        this.updatedAt = event.createdAt();
    }
}
