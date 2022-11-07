package com.progressoft.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
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

}
