package co.istad.account.rest.controller;

import co.istad.account.applicationservice.ports.input.service.AccountCommandService;
import co.istad.account.applicationservice.dto.create.CreateAccountRequest;
import co.istad.account.applicationservice.dto.create.CreateAccountResponse;
import co.istad.account.applicationservice.dto.deposit.DepositMoneyRequest;
import co.istad.account.applicationservice.dto.deposit.DepositMoneyResponse;
import co.istad.account.applicationservice.dto.freeze.FreezeAccountRequest;
import co.istad.account.applicationservice.dto.freeze.FreezeAccountResponse;
import co.istad.account.applicationservice.dto.withdraw.WithdrawMoneyRequest;
import co.istad.account.applicationservice.dto.withdraw.WithdrawMoneyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/accounts")
public class AccountCommandController {

    private final AccountCommandService accountCommandService;

    //create account
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateAccountResponse createAccount(
           @RequestBody CreateAccountRequest createAccountRequest){
        log.info("Create Account:  {}", createAccountRequest);

        return accountCommandService.createAccount(createAccountRequest);
    }

    //deposit amount
    @PutMapping("/deposit")
    public DepositMoneyResponse depositMoneyResponse(
            @RequestBody DepositMoneyRequest depositMoneyRequest
            ){
        log.info("Deposit amount request: {}", depositMoneyRequest);

        return accountCommandService.depositMoney(depositMoneyRequest);
    }

    //withdraw
    @PutMapping("/withdraw")
    @ResponseStatus(HttpStatus.OK)
    public WithdrawMoneyResponse withdrawMoney(
             @RequestBody WithdrawMoneyRequest withdrawMoneyRequest) {
        log.info("Withdraw amount request: {}", withdrawMoneyRequest);
        return accountCommandService.withdrawMoney(withdrawMoneyRequest);
    }

    //freeze
    @PutMapping("/freeze")
    @ResponseStatus(HttpStatus.OK)
    public FreezeAccountResponse freezeAccount(
            @RequestBody FreezeAccountRequest request) {
        log.info("Freeze account request: {}", request);
        return accountCommandService.freezeAccount(request);
    }


}
