package com.progressoft.mappers;

import com.progressoft.entities.Account;
import com.progressoft.entities.Cheque;
import com.progressoft.model.AccountDto;
import com.progressoft.model.ChequeDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
    ChequeDto chequeToChequeDto(Cheque cheque);
    Cheque chequeDtoToCheque(ChequeDto chequeDto);
    Account accountDtoToAccount(AccountDto accountDto);
    AccountDto accountToAccountDto(Account account);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateChequeFromChequeDto(ChequeDto givenCheque, @MappingTarget Cheque entity);
}
