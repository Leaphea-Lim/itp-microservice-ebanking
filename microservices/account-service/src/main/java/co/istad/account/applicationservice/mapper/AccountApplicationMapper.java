package co.istad.account.applicationservice.mapper;

import co.istad.account.dataaccess.entity.AccountEntity;
import co.istad.account.applicationservice.dto.create.CreateAccountRequest;
import co.istad.account.domain.command.CreateAccountCommand;
import co.istad.common.domain.event.AccountCreatedEvent;
import co.istad.common.domain.valueObject.AccountId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountApplicationMapper {
    CreateAccountCommand createAccountRequestToCreateAccountCommand(
            AccountId accountId,
            CreateAccountRequest createAccountRequest);

    @Mapping(source = "accountId.value", target = "accountId")
    @Mapping(source = "customerId.value", target = "customerId")
    @Mapping(source = "branchId.value", target = "branchId")
    @Mapping(source = "initialBalance", target = "money")
    @Mapping(target = "accountType", ignore = true)
    @Mapping(target = "accountStatus", ignore = true)
    AccountEntity accountCreateEventToAccountEntity(
            AccountCreatedEvent accountCreatedEvent);
}
