package com.progressoft.entities;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "Cheque")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDigit() {
        return digit;
    }

    public void setDigit(String digit) {
        this.digit = digit;
    }

    public Account getPayeeAccount() {
        return payeeAccount;
    }

    public void setPayeeAccount(Account payee) {
        this.payeeAccount = payee;
    }

    public Account getDrawerAccount() {
        return drawerAccount;
    }

    public void setDrawerAccount(Account drawer) {
        this.drawerAccount = drawer;
    }
}
