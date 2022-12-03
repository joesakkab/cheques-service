package com.progressoft.dtos.cheques;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.progressoft.dtos.account.AccountDto;
import com.progressoft.domain.ChequeStatus;
import com.progressoft.validations.UniqueChequeNumber;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChequeSearchDto {

    @JsonProperty(value = "id")
    private Long id;

    @Min(value = 0, message = "ChequeEntity amount should be greater than zero.")
    @Max(value = 1000000, message = "ChequeEntity amount should be less than 1,000,000.")
    @Digits(integer = 6, fraction = 2, message = "ChequeEntity amount value out of bounds (<6 digits>.<2 digits> expected)")
    @JsonProperty("amount")
    private BigDecimal amount;

    @Pattern(regexp = "^\\d+$", message = "ILLEGAL CHAR: ChequeEntity number must be numeric")
    @UniqueChequeNumber
    @JsonProperty("number")
    private String number;

    @Pattern(regexp = "^\\d+$", message = "ILLEGAL CHAR: ChequeEntity digit must be numeric")
    @JsonProperty("digit")
    private String digit;

    @JsonProperty("payeeAccount")
    @Valid
    private AccountDto payeeAccount;

    @JsonProperty("drawerAccount")
    @Valid
    private AccountDto drawerAccount;

    @Setter(AccessLevel.NONE)
    @JsonProperty("createdDate")
    private LocalDate createdDate;

    @JsonProperty("postingDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDate postingDate;

    @JsonProperty(value = "PDC")
    private Boolean pdc;

    @JsonProperty(value = "ONUS")
    private Boolean onus;

    @JsonProperty(value = "status")
    private ChequeStatus status;

}
