package com.progressoft.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
//import lombok.Getter;
//import lombok.Setter;

import java.math.BigDecimal;

//@Getter
//@Setter
public class ChequeDto {
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("chequeAmount")
    private BigDecimal amount;

    @NotNull
    @JsonProperty("chequeNumber")
    private String number;

    @NotNull
    @JsonProperty("chequeDigit")
    private String digit;

    @NotNull
    @JsonProperty("payeeAccount")
    private AccountDto payeeAccount;

    @NotNull
    @JsonProperty("drawerAccount")
    private AccountDto drawerAccount;

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

    public AccountDto getPayeeAccount() {
        return payeeAccount;
    }

    public void setPayeeAccount(AccountDto payeeAccount) {
        this.payeeAccount = payeeAccount;
    }

    public AccountDto getDrawerAccount() {
        return drawerAccount;
    }

    public void setDrawerAccount(AccountDto drawerAccount) {
        this.drawerAccount = drawerAccount;
    }
}
