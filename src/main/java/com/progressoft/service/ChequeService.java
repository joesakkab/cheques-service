package com.progressoft.service;

import com.progressoft.entities.Cheque;
import com.progressoft.dtos.ChequeDto;
import com.progressoft.mappers.MapperImpl;
import com.progressoft.repositories.ChequeRepository;
import org.springframework.data.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public void createCheque(ChequeDto chequeDto) {
        chequeRepo.save(
                mapper.toChequeEntity(chequeDto)
        );
    }

    public List<ChequeDto> getAllCheques() {
        List<ChequeDto> chequeDtos = new ArrayList<>();
        for (Cheque cheque : chequeRepo.findAll()) {
            chequeDtos.add(
                    mapper.toChequeDto(cheque)
            );
        }
        return chequeDtos;
    }

    public ChequeDto getChequeById(Long id) {
        return mapper.toChequeDto(
                chequeRepo.getReferenceById(id)
        );
    }

    public void updateChequeById(Long id, ChequeDto chequeDto) {
        if (chequeRepo.existsById(id)) {
            chequeDto.setId(id);
            Cheque chequeResult = mapper.toChequeEntity(chequeDto);
            chequeRepo.save(chequeResult);
        }
    }

    public String deleteChequeByID(Long id) {
        if (chequeRepo.existsById(id)) {
            chequeRepo.deleteById(id);
            return "Cheque with id " + id + " was successfully deleted.";
        } else {
            return "Cheque with id " + id + " not found.";
        }

    }

    public Slice<ChequeDto> findAllCheques(ChequeDto chequeDto, Pageable pageable) {
        Example<Cheque> chequeExample = Example.of(
                mapper.toChequeEntity(chequeDto)
        );
        return chequeRepo.findAll(
                chequeExample,
                pageable
            ).map(mapper::toChequeDto);
    }
}
