package com.progressoft.entities;


import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Cheque")
@DynamicInsert
@DynamicUpdate
@Table(
        name = "cheque",
        uniqueConstraints = {
                @UniqueConstraint(name = "cheque_number_unique", columnNames = "cheque_number")
        }
)
public class Cheque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Basic
    @Column(name = "cheque_amount")
    private BigDecimal amount;

    @Basic
    @Column(name = "cheque_number")
    private String number;

    @Basic
    @Column(name = "cheque_digit")
    private String digit;

    @AttributeOverrides({
            @AttributeOverride(name = "bankCode", column = @Column(name = "payee_bank_code")),
            @AttributeOverride(name = "branchCode", column = @Column(name = "payee_branch_code")),
            @AttributeOverride(name = "accountNumber", column = @Column(name = "payee_account_number"))
    })
    @Embedded
    private Account payeeAccount;


    @AttributeOverrides({
            @AttributeOverride(name = "bankCode", column = @Column(name = "drawer_bank_code")),
            @AttributeOverride(name = "branchCode", column = @Column(name = "drawer_branch_code")),
            @AttributeOverride(name = "accountNumber", column = @Column(name = "drawer_account_number"))
    })
    @Embedded
    private Account drawerAccount;

}
