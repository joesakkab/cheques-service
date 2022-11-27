package com.progressoft.service;

import com.progressoft.dtos.cheques.ChequeGetDto;
import com.progressoft.dtos.cheques.ChequePostDto;
import com.progressoft.dtos.cheques.ChequePutDto;
import com.progressoft.dtos.cheques.ChequeSearchDto;
import com.progressoft.entities.Cheque;
import com.progressoft.mappers.ChequeMapper;
import com.progressoft.repositories.ChequeRepository;
import org.springframework.data.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;

@Service
@Validated
public class ChequeService {
    public ChequeRepository chequeRepo;
    public ChequeMapper mapper;

    @Autowired
    public ChequeService (
            ChequeRepository chequeRepo,
            ChequeMapper mapper
    ) {
        this.chequeRepo = chequeRepo;
        this.mapper = mapper;
    }

    public void createCheque(ChequePostDto chequePostDto) {
        System.out.println(chequePostDto);
        Cheque entity = mapper.map(chequePostDto);
        System.out.println(entity.toString());
        chequeRepo.save(
                mapper.map(chequePostDto)
        );
    }

    public ChequeGetDto getChequeById(Long id) {
        return mapper.toChequeGetDto(
                chequeRepo.getReferenceById(id)
        );
    }

    public void updateChequeById(Long id, ChequePutDto dto) {
        if (chequeRepo.existsById(id)) {
            Cheque entity = chequeRepo.getReferenceById(id);
            mapper.updateChequeFromChequeDto(dto, entity);
            chequeRepo.save(entity);
        }
    }

    public void deleteChequeByID(Long id) {
        if (chequeRepo.existsById(id)) {
            chequeRepo.deleteById(id);
        } else {
            throw new EntityNotFoundException("Cheque with id " + id + " not found.");
        }
    }

    public Slice<ChequeGetDto> findAllCheques( ChequeSearchDto dto, Pageable pageable) {
        System.out.println("The cheque received was : \n" + dto.toString());
        Example<Cheque> chequeExample = Example.of(
                mapper.map(dto)
        );
        System.out.println("Example cheques is calculated.");
        System.out.println("Example is: \n" + chequeExample);
       chequeRepo.findAll(chequeExample, pageable);
        return chequeRepo.findAll(
                chequeExample,
                pageable
            ).map(mapper::toChequeGetDto);
    }

    public void submitById(Long id) {
        Cheque entity = chequeRepo.getReferenceById(id);
        entity.calculateStatus();
        chequeRepo.save(entity);
    }
}
