package co.istad.account.dataaccess.entity;


import co.istad.common.domain.valueObject.AccountStatus;
import co.istad.common.domain.valueObject.Money;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "accounts")
public class AccountEntity {

    @Id
    private UUID accountId;

    private UUID customerId;
    private UUID branchId;

    private String accountNumber;
    private String accountHolder;

    @Embedded
    private Money money;

    @ManyToOne
    @JoinColumn(name = "account_type_id")
    private AccountTypeEntity accountType;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
}
