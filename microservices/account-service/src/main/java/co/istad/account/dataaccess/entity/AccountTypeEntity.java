package co.istad.account.dataaccess.entity;

import co.istad.common.domain.valueObject.AccountTypeCode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "account_types")
public class AccountTypeEntity {

    @Id
    private UUID accountTypeId;

    @Enumerated(EnumType.STRING)
    private AccountTypeCode code;

    @OneToMany(mappedBy = "accountType", cascade = CascadeType.ALL)
    private List<AccountEntity> accounts; //one type has many account

}
