package com.progressoft.service;

import com.progressoft.dtos.cheques.ChequeGetDto;
import com.progressoft.dtos.cheques.ChequePostDto;
import com.progressoft.dtos.cheques.ChequePutDto;
import com.progressoft.entities.Cheque;
import com.progressoft.mappers.MapperImpl;
import com.progressoft.repositories.ChequeRepository;
import org.springframework.data.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ChequeService {
    public ChequeRepository chequeRepo;
    public MapperImpl mapper;

    @Autowired
    public ChequeService (
            ChequeRepository chequeRepo,
            MapperImpl mapper
    ) {
        this.chequeRepo = chequeRepo;
        this.mapper = mapper;
    }

    public void createCheque(ChequePostDto chequePostDto) {
        chequeRepo.save(
                mapper.toChequeEntity(chequePostDto)
        );
    }

    public ChequeGetDto getChequeById(Long id) {
        return mapper.toChequeDto(
                chequeRepo.getReferenceById(id)
        );
    }

    public void updateChequeById(Long id, ChequePutDto dto) {
        if (chequeRepo.existsById(id)) {
            dto.setId(id);
            Cheque chequeResult = mapper.toChequeEntity(dto);
            chequeRepo.save(chequeResult);
        }
    }

    public void deleteChequeByID(Long id) {
        if (chequeRepo.existsById(id)) {
            chequeRepo.deleteById(id);
        } else {
            throw new EntityNotFoundException("Cheque with id " + id + " not found.");
        }
    }

    public Slice<ChequeGetDto> findAllCheques(ChequeGetDto dto, Pageable pageable) {
        Example<Cheque> chequeExample = Example.of(
                mapper.toChequeEntity(dto)
        );
        return chequeRepo.findAll(
                chequeExample,
                pageable
            ).map(mapper::toChequeDto);
    }
}
