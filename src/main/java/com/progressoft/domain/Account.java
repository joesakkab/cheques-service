package com.progressoft.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @NotBlank(message = "AccountEntity bank code must not be blank")
    @Length(min = 2, max = 2, message = "Bank code must be of length 2")
    @Pattern(regexp = "^\\d+$", message = "ILLEGAL CHAR: Bank code must be numeric")
    private String bankCode;

    @NotBlank(message = "account branch code must not be blank")
    @Length(min = 4, max = 4, message = "branch code must be of length 4")
    @Pattern(regexp = "^\\d+$", message = "ILLEGAL CHAR: Branch code must be numeric")
    private String branchCode;

    @NotBlank(message = "account number must not be blank")
    @Length(min = 8, max = 8, message = "account number must be of length 8")
    @Pattern(regexp = "^\\d+$", message = "ILLEGAL CHAR: AccountEntity number must be numeric")
    private String accountNumber;
}
