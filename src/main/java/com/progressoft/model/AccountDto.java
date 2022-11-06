package com.progressoft.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class AccountDto {

    @NotNull
    @JsonProperty("bankCode")
    private String bankCode;

    @NotNull
    @JsonProperty("branchCode")
    private String branchCode;

    @NotNull
    @JsonProperty("accountNumber")
    private String accountNumber;

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
