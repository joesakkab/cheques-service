package com.progressoft.mappers;

import com.progressoft.entities.Account;
import com.progressoft.entities.Cheque;
import com.progressoft.model.AccountDto;
import com.progressoft.model.ChequeDto;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
import java.util.HashSet;
import java.util.Set;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 13.0.2 (Oracle Corporation)"
)
@Component
public class MapStructMapperImpl implements MapStructMapper {
    @Override
    public ChequeDto chequeToChequeDto(Cheque cheque) {
        if (cheque == null) {
            return null;
        }
        ChequeDto chequeDto = new ChequeDto();

        chequeDto.setId(cheque.getId());
        chequeDto.setNumber(cheque.getNumber());
        chequeDto.setAmount(cheque.getAmount());
        chequeDto.setDigit(cheque.getDigit());
        chequeDto.setPayeeAccount(accountToAccountDto(cheque.getPayeeAccount()));
        chequeDto.setDrawerAccount(accountToAccountDto(cheque.getDrawerAccount()));

        return chequeDto;
    }

    @Override
    public Cheque chequeDtoToCheque(ChequeDto chequeDto) {
        if (chequeDto == null) {
            return null;
        }
        Cheque cheque = new Cheque();

        cheque.setId(chequeDto.getId());
        cheque.setAmount(chequeDto.getAmount());
        cheque.setNumber(chequeDto.getNumber());
        cheque.setDigit(chequeDto.getDigit());
        cheque.setPayeeAccount(accountDtoToAccount(chequeDto.getPayeeAccount()));
        cheque.setDrawerAccount(accountDtoToAccount(chequeDto.getDrawerAccount()));

        return cheque;
    }

    @Override
    public Account accountDtoToAccount(AccountDto accountDto) {
        if (accountDto == null) {
            return null;
        }
        return new Account(
                accountDto.getBankCode(),
                accountDto.getBranchCode(),
                accountDto.getAccountNumber()
        );
    }

    @Override
    public AccountDto accountToAccountDto(Account account) {
        if (account == null) {
            return null;
        }
        AccountDto accountDto = new AccountDto();

        accountDto.setBankCode(account.getBankCode());
        accountDto.setBranchCode(account.getBranchCode());
        accountDto.setAccountNumber(account.getAccountNumber());
        return accountDto;
    }

    @Override
    public void updateChequeFromChequeDto(ChequeDto dto,@MappingTarget Cheque entity) {
        entity.setAmount(dto.getAmount());
        entity.setNumber(dto.getNumber());
        entity.setDigit(dto.getDigit());
        entity.setPayeeAccount(accountDtoToAccount(dto.getPayeeAccount()));
        entity.setDrawerAccount(accountDtoToAccount(dto.getDrawerAccount()));
    }

    private Set<ChequeDto> chequeSetTochequeDtoSet(Set<Cheque> set) {
        if (set == null) {
            return null;
        }

        Set<ChequeDto> set1 = new HashSet<ChequeDto>();
        for (Cheque cheque: set) {
            set1.add(chequeToChequeDto(cheque));
        }
        return set1;
    }
}
