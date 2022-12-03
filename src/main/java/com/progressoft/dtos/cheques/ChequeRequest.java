package com.progressoft.dtos.cheques;

import com.fasterxml.jackson.annotation.*;
import com.progressoft.dtos.account.AccountDto;
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
public class ChequeRequest {

    @NotNull(message = "amount must not be empty")
    @Min(value = 0, message = "ChequeEntity amount should be greater than zero.")
    @Max(value = 1000000, message = "ChequeEntity amount should be less than 1,000,000.")
    @Digits(integer = 6, fraction = 2, message = "ChequeEntity amount value out of bounds (<6 digits>.<2 digits> expected)")
    @JsonAlias("chequeAmount")
    private BigDecimal amount;

    @NotBlank(message = " cheque number must not be blank")
    @Pattern(regexp = "^\\d+$", message = "ILLEGAL CHAR: ChequeEntity number must be numeric")
    @UniqueChequeNumber
    @JsonAlias("chequeNumber")
    private String number;

    @NotBlank(message = "cheque digit must not be blank")
    @Pattern(regexp = "^\\d+$", message = "ILLEGAL CHAR: ChequeEntity digit must be numeric")
    @JsonAlias("chequeDigit")
    private String digit;

    @NotNull
    @JsonAlias("payee")
    @Valid
    private AccountDto payeeAccount;

    @NotNull
    @JsonAlias("drawer")
    @Valid
    private AccountDto drawerAccount;

    @JsonProperty("postingDate")
    @JsonFormat(pattern = "yyyy/MM/dd")
    @FutureOrPresent
    private LocalDate postingDate;

}
