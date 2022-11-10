package com.progressoft.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @NotBlank(message = "account bank code can't be blank")
    private String bankCode;

    @NotBlank(message = "account branch code can't be blank")
    private String branchCode;

    @NotBlank(message = "account number can't be blank")
    private String accountNumber;
}
