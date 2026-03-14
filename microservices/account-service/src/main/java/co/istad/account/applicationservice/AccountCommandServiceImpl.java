package co.istad.account.applicationservice;

import co.istad.account.applicationservice.dto.create.CreateAccountRequest;
import co.istad.account.applicationservice.dto.create.CreateAccountResponse;
import co.istad.account.applicationservice.dto.deposit.DepositMoneyRequest;
import co.istad.account.applicationservice.dto.deposit.DepositMoneyResponse;
import co.istad.account.applicationservice.dto.freeze.FreezeAccountRequest;
import co.istad.account.applicationservice.dto.freeze.FreezeAccountResponse;
import co.istad.account.applicationservice.dto.withdraw.WithdrawMoneyRequest;
import co.istad.account.applicationservice.dto.withdraw.WithdrawMoneyResponse;
import co.istad.account.applicationservice.mapper.AccountApplicationMapper;
import co.istad.account.applicationservice.ports.input.service.AccountCommandService;
import co.istad.account.domain.command.CreateAccountCommand;
import co.istad.account.domain.command.DepositMoneyCommand;
import co.istad.account.domain.command.FreezeAccountCommand;
import co.istad.account.domain.command.WithdrawMoneyCommand;
import co.istad.common.domain.valueObject.AccountId;
import co.istad.common.domain.valueObject.AccountStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountCommandServiceImpl implements AccountCommandService {

    private final AccountApplicationMapper accountMapper;
    private final CommandGateway commandGateway;

    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest) {
        log.info("createAccountRequest: {}", createAccountRequest);

        AccountId newAccountId = new AccountId(UUID.randomUUID());

        CreateAccountCommand createAccountCommand = accountMapper
                .createAccountRequestToCreateAccountCommand(
                        newAccountId,
                        createAccountRequest
                );

        log.info("CreateAccountCommand: {}", createAccountCommand);

        AccountId accountId = commandGateway.sendAndWait(createAccountCommand);
        log.info("CommandGateway Result: {}", accountId);

        // Build response
        return CreateAccountResponse.builder()
                .accountId(accountId.toString())
                .accountNumber(createAccountCommand.accountNumber())
                .accountHolder(createAccountCommand.accountHolder())
                .message("Account has been created successfully")
                .build();
    }

    @Override
    public DepositMoneyResponse depositMoney(DepositMoneyRequest depositMoneyRequest) {

        DepositMoneyCommand depositMoneyCommand = new DepositMoneyCommand(
                depositMoneyRequest.accountId(),
                depositMoneyRequest.transactionId(),
                depositMoneyRequest.amount(),
                depositMoneyRequest.remark()
        );
        log.info("DepositMoneyCommand: {}", depositMoneyCommand);

        commandGateway.sendAndWait(depositMoneyCommand);

        return DepositMoneyResponse.builder()
                .accountId(depositMoneyCommand.accountId())
                .transactionId(depositMoneyCommand.transactionId())
                .amount(depositMoneyCommand.amount())
                .remark(depositMoneyCommand.remark())
                .message("Money has deposited successfully.")
                .build();
    }

    @Override
    public WithdrawMoneyResponse withdrawMoney(WithdrawMoneyRequest withdrawMoneyRequest) {
        WithdrawMoneyCommand command = new WithdrawMoneyCommand(
                withdrawMoneyRequest.accountId(),
                withdrawMoneyRequest.transactionId(),
                withdrawMoneyRequest.amount(),
                withdrawMoneyRequest.remark()
        );

        log.info("WithdrawMoneyCommand: {}", command);
        commandGateway.sendAndWait(command);

        return WithdrawMoneyResponse.builder()
                .accountId(command.accountId())
                .transactionId(command.transactionId())
                .amount(command.amount())
                .remark(command.remark())
                .createdAt(ZonedDateTime.now())
                .message("Money withdrawn successfully")
                .build();
    }

    @Override
    public FreezeAccountResponse freezeAccount(FreezeAccountRequest freezeAccountRequest) {

        FreezeAccountCommand command = new FreezeAccountCommand(
                freezeAccountRequest.accountId(),
                freezeAccountRequest.remark(),
                freezeAccountRequest.requestedBy()
        );

        log.info("FreezeAccountCommand: {}", command);
        commandGateway.sendAndWait(command);

        return FreezeAccountResponse.builder()
                .accountId(command.accountId())
                .newStatus(AccountStatus.FREEZE)
                .reason(command.remark())
                .requestedBy(command.requestedBy())
                .createdAt(ZonedDateTime.now())
                .message("Account frozen successfully")
                .build();
    }
}