package com.progressoft.dtos.cheques;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.progressoft.dtos.account.AccountPostAndPutDto;
import com.progressoft.validations.UniqueChequeNumber;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class ChequePostDto implements ChequeDto {

    @JsonProperty("id")
    private Long id;

    @NotNull(message = "amount must not be empty")
    @Min(value = 0, message = "Cheque amount should be greater than zero.")
    @Max(value = 1000000, message = "Cheque amount should be less than 1,000,000.")
    @Digits(integer = 6, fraction = 2, message = "Cheque amount value out of bounds (<6 digits>.<2 digits> expected)")
    @JsonProperty("chequeAmount")
    private BigDecimal amount;

    @NotBlank(message = " cheque number must not be blank")
    @Pattern(regexp = "^\\d+$", message = "ILLEGAL CHAR: Cheque number must be numeric")
    @UniqueChequeNumber
    @JsonProperty("chequeNumber")
    private String number;

    @NotBlank(message = "cheque digit must not be blank")
    @Pattern(regexp = "^\\d+$", message = "ILLEGAL CHAR: Cheque digit must be numeric")
    @JsonProperty("chequeDigit")
    private String digit;

    @NotNull
    @JsonProperty("payeeAccount")
    @Valid
    private AccountPostAndPutDto payeeAccount;

    @NotNull
    @JsonProperty("drawerAccount")
    @Valid
    private AccountPostAndPutDto drawerAccount;

}
