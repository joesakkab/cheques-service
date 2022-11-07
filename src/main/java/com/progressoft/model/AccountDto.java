package com.progressoft.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
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
}
