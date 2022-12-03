package com.progressoft.entities;

import com.progressoft.domain.ChequeStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@ToString
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@Entity(name = "Cheque")
@Table(name = "CHEQUE_TBL")
public class ChequeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Basic
    @Column(name = "cheque_amount")
    private BigDecimal amount;

    @Basic
    @Column(name = "cheque_number")
    private String number;

    @Basic
    @Column(name = "cheque_digit")
    private String digit;

    @AttributeOverrides({
            @AttributeOverride(name = "bankCode", column = @Column(name = "payee_bank_code")),
            @AttributeOverride(name = "branchCode", column = @Column(name = "payee_branch_code")),
            @AttributeOverride(name = "accountNumber", column = @Column(name = "payee_account_number"))
    })
    @Embedded
    private AccountEntity payeeAccount;


    @AttributeOverrides({
            @AttributeOverride(name = "bankCode", column = @Column(name = "drawer_bank_code")),
            @AttributeOverride(name = "branchCode", column = @Column(name = "drawer_branch_code")),
            @AttributeOverride(name = "accountNumber", column = @Column(name = "drawer_account_number"))
    })
    @Embedded
    private AccountEntity drawerAccount;

    @Basic
    @Column(name = "created_date", updatable = false)
    private LocalDate createdDate = LocalDate.now();

    @Basic
    @Column(name = "posting_date")
    private LocalDate postingDate;

    @Basic
    @Column(name = "PDC")
    private Boolean pdc;

    @Basic
    @Column(name = "ONUS")
    private Boolean onus;

    @Basic
    @Column(name = "Status")
    private ChequeStatus status = ChequeStatus.DRAFT;

//    public ChequeEntity(BigDecimal amount,
//                        String number,
//                        String digit,
//                        AccountEntity payeeAccountEntity,
//                        AccountEntity drawerAccountEntity,
//                        LocalDate createdDate,
//                        LocalDate postingDate,
//                        Boolean onus,
//                        Boolean pdc,
//                        ChequeStatus status
//    ) {
//        this.amount = amount;
//        this.number = number;
//        this.digit = digit;
//        this.payeeAccountEntity = payeeAccountEntity;
//        this.drawerAccountEntity = drawerAccountEntity;
//        this.createdDate = createdDate;
//        this.postingDate = postingDate;
//        this.onus = onus;
//        this.pdc = pdc;
//        this.status = status;
//    }


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
