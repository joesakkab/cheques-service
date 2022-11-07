package com.progressoft.service;

import com.progressoft.entities.Cheque;
import com.progressoft.mappers.MapStructMapper;
import com.progressoft.model.ChequeDto;
import com.progressoft.repositories.ChequeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
            Cheque cheque = chequeRepo.getReferenceById(id);
            mapStructMapper.updateChequeFromChequeDto(chequeDto, cheque);
            chequeRepo.save(cheque);
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

    public void saveAll(List<ChequeDto> dtos) {
        for (ChequeDto dto: dtos) {
            chequeRepo.save(
                    mapStructMapper.chequeDtoToCheque(dto)
            );
        }
    }

    public List<ChequeDto> findChequesByAllFields(Long id, BigDecimal amount, String number, String digit) {
        return mapStructMapper.listOfChequesToListOfChequesDtos(
                chequeRepo.findChequesByAllFields(id, amount, number, digit)
        );
    }
}
