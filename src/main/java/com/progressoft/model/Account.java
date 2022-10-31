package com.progressoft.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity(name = "Account")
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "id",
            updatable = false
    )
    @JsonProperty("accountId")
    private Long id;
    @Column(
            name = "bank_code"
    )
    private String bankCode;
    @Column(
            name = "branch_code"
    )
    private String branchCode;
    @Column(
            name = "account_number"
    )
    private String accountNumber;
    private String fullNumber;

    @OneToMany(mappedBy = "payeeAccount", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Cheque> payeeCheques;

    @OneToMany(mappedBy = "drawerAccount", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Cheque> drawerCheques;

    public Account(String bankCode, String branchCode, String accountNumber) {
        this.bankCode = bankCode;
        this.branchCode = branchCode;
        this.accountNumber = accountNumber;
        this.fullNumber = "<" + this.branchCode + "=" + this.bankCode + "<" + this.accountNumber;
    }

    public Account() {
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @PrePersist
    public void setFullNumber() {
        this.fullNumber = "<" + this.branchCode + "=" + this.bankCode + "<" + this.accountNumber;
    }

    public String getFullNumber() { return fullNumber; }

    @JsonManagedReference(value = "payee-reference")
    public List<Cheque> getPayeeCheques() {
        return payeeCheques;
    }

    public void setPayeeCheques(Iterable<Cheque> payeeCheques) {
        for (Cheque payeeCheque : payeeCheques) {
            this.payeeCheques.add(payeeCheque);
        }
    }

    @JsonManagedReference(value = "drawer-reference")
    public List<Cheque> getDrawerCheques() {
        return drawerCheques;
    }

    public void setDrawerCheques(Iterable<Cheque> drawerCheques) {
        for (Cheque drawerCheque : drawerCheques) {
            this.drawerCheques.add(drawerCheque);
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + id +
                ", bankCode='" + bankCode + '\'' +
                ", branchCode='" + branchCode + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
