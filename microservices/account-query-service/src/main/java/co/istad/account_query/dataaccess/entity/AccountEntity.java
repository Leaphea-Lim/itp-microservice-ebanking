package co.istad.account_query.dataaccess.entity;

import co.istad.common.domain.valueObject.AccountStatus;
import co.istad.common.domain.valueObject.AccountTypeCode;
import co.istad.common.domain.valueObject.Currency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "accounts")
public class AccountEntity {

    @Id
    private UUID accountId;

    private UUID customerId;

    @Column("account_type_code")
    private AccountTypeCode accountTypeCode;

    private UUID branchId;

    private String accountNumber;
    private String accountHolder;

    @Column("amount")
    private BigDecimal balance;
    private Currency currency;


    private UUID accountTypeId;

    @Column("account_status")
    private AccountStatus status;

    private ZonedDateTime createdAt;
    private String createdBy;
    private ZonedDateTime updatedAt;
    private String updatedBy;

//    @Override
//    public @Nullable UUID getId() {
//        return this.accountId;
//    }
//
//    @Override
//    public boolean isNew() {
//        return true;
//    }

    @Version
    private Long version;
}
