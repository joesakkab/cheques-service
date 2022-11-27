package com.progressoft.mappers;

import org.mapstruct.Mapping;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
@Mapping(target = "id", ignore = true)
@Mapping(target = "createdDate", expression = "java(LocalDate.now())")
@Mapping(target = "onus", ignore = true)
@Mapping(target = "pdc", ignore = true)
@Mapping(target = "status", expression = "java(ChequeStatus.DRAFT)")
//@Mapping(target = "status", expression = "java(Cheque.calculateOnus())")
public @interface ToEntity {
}
