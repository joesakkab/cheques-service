package com.progressoft.entities;

import lombok.*;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class AccountEntity {
    private String bankCode;
    private String branchCode;
    private String accountNumber;
}
