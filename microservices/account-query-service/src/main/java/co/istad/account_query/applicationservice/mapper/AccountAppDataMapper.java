package co.istad.account_query.applicationservice.mapper;

import co.istad.account_query.applicationservice.dto.AccountQueryResponse;
import co.istad.account_query.domain.Account;
import co.istad.common.domain.event.AccountCreatedEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountAppDataMapper {


    AccountQueryResponse accountToAccountQueryResponse(Account account);

    @Mapping(source = "accountId.value", target = "accountId")
    @Mapping(source = "customerId.value", target = "customerId")
    @Mapping(source = "branchId.value", target = "branchId")
    Account accountCreatedEventToAccount(AccountCreatedEvent accountCreatedEvent);

}
