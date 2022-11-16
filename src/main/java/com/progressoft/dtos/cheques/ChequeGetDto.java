package com.progressoft.dtos.cheques;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.progressoft.dtos.account.AccountGetDto;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class ChequeGetDto implements ChequeDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("chequeAmount")
    private BigDecimal amount;

    @JsonProperty("chequeNumber")
    private String number;

    @JsonProperty("chequeDigit")
    private String digit;

    @JsonProperty("payeeAccount")
    private AccountGetDto payeeAccount;

    @JsonProperty("drawerAccount")
    private AccountGetDto drawerAccount;

}
