package com.progressoft.entities;

import lombok.*;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
@Embeddable
public class Account {
    private String bankCode;
    private String branchCode;
    private String accountNumber;
}
