package co.istad.account.applicationservice.ports.input.service;

import co.istad.account.applicationservice.dto.create.CreateAccountRequest;
import co.istad.account.applicationservice.dto.create.CreateAccountResponse;
import co.istad.account.applicationservice.dto.deposit.DepositMoneyRequest;
import co.istad.account.applicationservice.dto.deposit.DepositMoneyResponse;
import co.istad.account.applicationservice.dto.freeze.FreezeAccountRequest;
import co.istad.account.applicationservice.dto.freeze.FreezeAccountResponse;
import co.istad.account.applicationservice.dto.withdraw.WithdrawMoneyRequest;
import co.istad.account.applicationservice.dto.withdraw.WithdrawMoneyResponse;


public interface AccountCommandService {
    CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest);
    DepositMoneyResponse depositMoney(DepositMoneyRequest depositMoneyRequest);
    WithdrawMoneyResponse withdrawMoney(WithdrawMoneyRequest request);
    FreezeAccountResponse freezeAccount(FreezeAccountRequest request);
}
