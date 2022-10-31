package com.progressoft.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "cheque_amount",
            nullable = false
    )
    private BigDecimal chequeAmount;
    @Column(
            name = "cheque_number",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String chequeNumber;
    @Column(
            name = "cheque_digit",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String chequeDigit;

    @ManyToOne(optional = false)
    @JoinColumn(name = "payee_account_id", nullable = false)
    private Account payeeAccount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "drawer_account_id", nullable=false)
    private Account drawerAccount;


    public Cheque( BigDecimal chequeAmount,
                   String chequeNumber,
                   String chequeDigit,
                   Account payeeAccount,
                   Account drawerAccount) {
        this.chequeAmount = chequeAmount;
        this.chequeNumber = chequeNumber;
        this.chequeDigit = chequeDigit;
        this.payeeAccount = payeeAccount;
        this.drawerAccount = drawerAccount;
    }

    public Cheque() {}

    public Long getChequeId() {
        return id;
    }

    public void setChequeId(Long id) {
        this.id = id;
    }

    public BigDecimal getChequeAmount() {
        return chequeAmount;
    }

    public void setChequeAmount(BigDecimal amount) {
        this.chequeAmount = amount;
    }

    public String getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(String number) {
        this.chequeNumber = number;
    }

    public String getChequeDigit() {
        return chequeDigit;
    }

    public void setChequeDigit(String digit) {
        this.chequeDigit = digit;
    }

    @JsonBackReference(value = "payee-reference")
    public Account getPayeeAccount() {
        return payeeAccount;
    }

    public void setPayeeAccount(Account payeeAccount) {
        this.payeeAccount = payeeAccount;
    }

    @JsonBackReference(value = "drawer-reference")
    public Account getDrawerAccount() {
        return drawerAccount;
    }

    public void setDrawerAccount(Account drawerAccount) {
        this.drawerAccount = drawerAccount;
    }

    @Override
    public String toString() {
        return "Cheque{" +
                "id=" + id +
                ", amount=" + chequeAmount +
                ", number='" + chequeNumber + '\'' +
                ", digit='" + chequeDigit + '\'' +
                ", payee=" + payeeAccount.getFullNumber() +
                ", drawer=" + drawerAccount.getFullNumber() +
                '}';
    }
}
