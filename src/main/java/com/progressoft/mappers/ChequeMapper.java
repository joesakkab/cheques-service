package com.progressoft.mappers;

import com.progressoft.domain.Cheque;
import com.progressoft.dtos.cheques.*;
import com.progressoft.entities.ChequeEntity;
import com.progressoft.domain.ChequeStatus;
import org.mapstruct.*;

import java.time.LocalDate;

@Mapper(imports = {LocalDate.class, ChequeStatus.class}, componentModel = "spring")
public abstract class ChequeMapper {

    public abstract Cheque map(ChequeRequest dto);
    public abstract ChequeEntity toEntity(Cheque obj);

    @InheritInverseConfiguration(name = "toEntity")
    public abstract Cheque toCheque(ChequeEntity entity);
    public abstract ChequeEntity map(ChequeSearchDto dto);
    public abstract ChequeResponse map(ChequeEntity entity);
    public abstract void submit(@MappingTarget ChequeEntity entity, Cheque obj);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    public abstract void updateChequeFromChequeDto(ChequeRequest dto, @MappingTarget ChequeEntity entity);


}

