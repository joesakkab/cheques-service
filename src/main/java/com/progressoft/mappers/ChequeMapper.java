package com.progressoft.mappers;

import com.progressoft.dtos.cheques.*;
import com.progressoft.entities.Cheque;
import com.progressoft.entities.ChequeStatus;
import org.mapstruct.*;

import java.time.LocalDate;

@Mapper(imports = {LocalDate.class, ChequeStatus.class}, componentModel = "spring")
public abstract class ChequeMapper {

    @ToEntity
    public abstract Cheque map(ChequePostDto dto);
    public abstract Cheque map(ChequeSearchDto dto);

    public abstract ChequeGetDto toChequeGetDto(Cheque entity);

    @Mapping(target = "pdc", ignore = true)
    @Mapping(target = "onus", ignore = true)
    @Mapping(target = "createdDate", ignore = true)

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    public abstract void updateChequeFromChequeDto(ChequePutDto dto, @MappingTarget Cheque entity);

    //Todo, make sure that the update mapping is good. Make sure that the generated class
    // has good logic.
    @AfterMapping
    protected void setOtherAttributes(@MappingTarget Cheque entity) {
        entity.calculateOnus();
        entity.calculatePdc();
    }

}

