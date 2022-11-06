package com.progressoft.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Account {
    private String bankCode;
    private String branchCode;
    private String accountNumber;

    protected Account() {}

    public Account(String bankCode, String branchCode, String accountNumber) {
        this.branchCode = branchCode;
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
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
}
