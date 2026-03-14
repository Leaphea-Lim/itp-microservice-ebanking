package co.istad.account_query.dataaccess.mapper;

import co.istad.account_query.dataaccess.entity.AccountEntity;
import co.istad.account_query.domain.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface AccountDataAccessMapper {

    @Mapping(source = "money.amount", target = "balance")
    @Mapping(source = "money.currency", target = "currency")
//    @Mapping(source = "accountTypeId", target = "accountTypeCode")
    AccountEntity accountToAccountEntity(Account account);

//    @Mapping(source = "money.amount", target = "balance")
//    @Mapping(source = "money.currency", target = "currency")
//    @Mapping(source = "accountTypeCode", target = "accountTypeId")
   Account accountEntityToAccount(AccountEntity accountEntity);


}
