package com.progressoft.service;

import com.progressoft.entities.Cheque;
import com.progressoft.mappers.MapStructMapper;
import com.progressoft.model.ChequeDto;
import com.progressoft.repositories.ChequeRepository;
import org.springframework.data.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChequeService {
    public ChequeRepository chequeRepo;
    public MapStructMapper mapStructMapper;

    @Autowired
    public ChequeService (
            ChequeRepository chequeRepo,
            MapStructMapper mapStructMapper
    ) {
        this.chequeRepo = chequeRepo;
        this.mapStructMapper = mapStructMapper;
    }

    public void createCheque(ChequeDto chequeDto) {
        chequeRepo.save(
                mapStructMapper.chequeDtoToCheque(chequeDto)
        );
    }

    public List<ChequeDto> getAllCheques() {
        List<ChequeDto> chequeDtos = new ArrayList<>();
        for (Cheque cheque : chequeRepo.findAll()) {
            chequeDtos.add(
                    mapStructMapper.chequeToChequeDto(cheque)
            );
        }
        return chequeDtos;
    }

    public ChequeDto getChequeById(Long id) {
        return mapStructMapper.chequeToChequeDto(
                chequeRepo.getReferenceById(id)
        );
    }

    public void updateChequeById(Long id, ChequeDto chequeDto) {
        if (chequeRepo.existsById(id)) {
            chequeDto.setId(id);
            Cheque chequeResult = mapStructMapper.chequeDtoToCheque(chequeDto);
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
                mapStructMapper.chequeDtoToCheque(chequeDto)
        );
        return chequeRepo.findAll(
                chequeExample,
                pageable
            ).map(mapStructMapper::chequeToChequeDto);
    }
}
