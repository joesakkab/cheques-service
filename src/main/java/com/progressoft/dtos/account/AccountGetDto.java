package com.progressoft.dtos.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class AccountGetDto implements AccountDto {

    @JsonProperty("bankCode")
    private String bankCode;

    @JsonProperty("branchCode")
    private String branchCode;

    @JsonProperty("accountNumber")
    private String accountNumber;
}
