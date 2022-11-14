package com.progressoft.mappers;

import com.progressoft.dtos.AccountDto;
import com.progressoft.dtos.ChequeDto;
import com.progressoft.entities.Account;
import com.progressoft.entities.Cheque;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;

@Mapper(componentModel = "spring")
@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 13.0.2 (Oracle Corporation)"
)
@Component
public class MapperImpl {
    private ModelMapper mapper = new ModelMapper();

    public Cheque toChequeEntity(ChequeDto dto) {
        return mapper.map(dto, Cheque.class);
    }
    public ChequeDto toChequeDto(Cheque entity) {
        return mapper.map(entity, ChequeDto.class);
    }
    public Account toAccountEntity(AccountDto dto) {
        return mapper.map(dto, Account.class);
    }
    public AccountDto toAccountDto(Account dto) {
        return mapper.map(dto, AccountDto.class);
    }
}
