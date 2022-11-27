package com.progressoft.dtos.cheques;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.progressoft.dtos.account.AccountDto;
import com.progressoft.entities.ChequeStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class ChequeGetDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("chequeAmount")
    private BigDecimal amount;

    @JsonProperty("chequeNumber")
    private String number;

    @JsonProperty("chequeDigit")
    private String digit;

    @JsonProperty("payeeAccount")
    private AccountDto payeeAccount;

    @JsonProperty("drawerAccount")
    private AccountDto drawerAccount;

    @JsonProperty("creationDate")
    private LocalDate createdDate;

    @JsonProperty("postingDate")
    private LocalDate postingDate;

    @JsonProperty("PDC")
    private Boolean pdc;

    @JsonProperty("ONUS")
    private Boolean onus;

    @JsonProperty("Status")
    private ChequeStatus status;
}
