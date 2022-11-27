package com.progressoft.dtos.cheques;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.progressoft.dtos.account.AccountDto;
import com.progressoft.entities.ChequeStatus;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ChequePutDto {

    @JsonProperty("id")
    private Long id;

    @Min(value = 0, message = "Cheque amount should be greater than zero.")
    @Max(value = 1000000, message = "Cheque amount should be less than 1,000,000.")
    @Digits(integer = 6, fraction = 2, message = "Cheque amount value out of bounds (<6 digits>.<2 digits> expected)")
    @JsonProperty("chequeAmount")
    private BigDecimal amount;

    @Pattern(regexp = "^\\d+$", message = "ILLEGAL CHAR: Cheque number must be numeric")
    @JsonProperty("chequeNumber")
    private String number;

    @Pattern(regexp = "^\\d+$", message = "ILLEGAL CHAR: Cheque digit must be numeric")
    @JsonProperty("chequeDigit")
    private String digit;

    @JsonProperty("payeeAccount")
    @Valid
    private AccountDto payeeAccount;

    @JsonProperty("drawerAccount")
    @Valid
    private AccountDto drawerAccount;

    @JsonProperty("createdDate")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate createdDate;

    @JsonProperty("postingDate")
    @FutureOrPresent
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate postingDate;

    @JsonProperty(value = "PDC", access = JsonProperty.Access.READ_ONLY)
    private Boolean pdc;

    @JsonProperty(value = "ONUS", access = JsonProperty.Access.READ_ONLY)
    private Boolean onus;

    @JsonProperty(value = "status", access = JsonProperty.Access.READ_ONLY)
    private ChequeStatus status;

    @JsonCreator
    public ChequePutDto(@JsonProperty("chequeAmount") BigDecimal amount,
                         @JsonProperty("chequeNumber") String number,
                         @JsonProperty("chequeDigit") String digit,
                         @JsonProperty("payeeAccount") AccountDto payeeAccount,
                         @JsonProperty("drawerAccount") AccountDto drawerAccount,
                         @JsonProperty("postingDate") LocalDate postingDate,
                        @JsonProperty("onus") Boolean onus,
                        @JsonProperty("pdc") Boolean pdc,
                        @JsonProperty("status") ChequeStatus status) {
        this.amount = amount;
        this.number = number;
        this.digit = digit;
        this.payeeAccount = payeeAccount;
        this.drawerAccount = drawerAccount;
        this.postingDate = postingDate;
        this.pdc = pdc;
        this.onus = onus;
        this.status = status;
    }

}
