package com.progressoft.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.progressoft.dtos.account.AccountDto;
import com.progressoft.validations.UniqueChequeNumber;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Cheque {

    @NotNull(message = "amount must not be empty")
    @Min(value = 0, message = "ChequeEntity amount should be greater than zero.")
    @Max(value = 1000000, message = "ChequeEntity amount should be less than 1,000,000.")
    @Digits(integer = 6, fraction = 2, message = "ChequeEntity amount value out of bounds (<6 digits>.<2 digits> expected)")
    private BigDecimal amount;

    @NotBlank(message = " cheque number must not be blank")
    @Pattern(regexp = "^\\d+$", message = "ILLEGAL CHAR: ChequeEntity number must be numeric")
    @UniqueChequeNumber
    private String number;

    @NotBlank(message = "cheque digit must not be blank")
    @Pattern(regexp = "^\\d+$", message = "ILLEGAL CHAR: ChequeEntity digit must be numeric")
    private String digit;

    @NotNull
    @Valid
    private Account payeeAccount;

    @NotNull
    @Valid
    private Account drawerAccount;

    private LocalDate createdDate;

    @FutureOrPresent
    private LocalDate postingDate;

    private Boolean pdc;

    private Boolean onus;

    private ChequeStatus status;

    public void setDefaults() {
        this.createdDate = LocalDate.now();
        this.status = ChequeStatus.DRAFT;
        if (this.postingDate == null) { this.postingDate = LocalDate.now(); }
        this.onus =  payeeAccount.getBankCode().equals(drawerAccount.getBankCode());
        this.pdc = postingDate.isAfter(createdDate);
    }

    public void updateFields() {
        calculateOnus();
        calculatePdc();
    }
    private void calculateOnus() {
        if (payeeAccount != null & drawerAccount != null) {
            this.onus =  payeeAccount.getBankCode().equals(drawerAccount.getBankCode());
        }
    }

    private void calculatePdc() {
        if (postingDate != null && createdDate != null) {
            this.pdc = postingDate.isAfter(createdDate);
        }
    }

    public void calculateStatus() {
        if (this.pdc != null) {
            this.status = (this.pdc) ? ChequeStatus.PDC_SUBMITTED : ChequeStatus.SUBMITTED;
        }
    }
}
