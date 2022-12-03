package com.progressoft.service;

import com.progressoft.domain.Cheque;
import com.progressoft.dtos.cheques.ChequeResponse;
import com.progressoft.dtos.cheques.ChequeRequest;
import com.progressoft.dtos.cheques.ChequeSearchDto;
import com.progressoft.entities.ChequeEntity;
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

    public ChequeResponse createCheque(ChequeRequest req) {
        Cheque obj = mapper.map(req);
        System.out.println(obj);
        obj.setDefaults();
        System.out.println(obj);
        ChequeEntity entity = mapper.toEntity(obj);
        System.out.println(entity);
        chequeRepo.save(entity);
        return getChequeById(entity.getId());
    }

    public ChequeResponse getChequeById(Long id) {
        return mapper.map(
                chequeRepo.getReferenceById(id)
        );
    }

    public void updateChequeById(Long id, ChequeRequest req) {
        if (chequeRepo.existsById(id)) {
            ChequeEntity entity = chequeRepo.getReferenceById(id);
            mapper.updateChequeFromChequeDto(req, entity);
            entity.updateFields();
            chequeRepo.save(entity);
        }
    }

    public void deleteChequeByID(Long id) {
        if (chequeRepo.existsById(id)) {
            chequeRepo.deleteById(id);
        } else {
            throw new EntityNotFoundException("ChequeEntity with id " + id + " not found.");
        }
    }

    public Slice<ChequeResponse> findAllCheques(ChequeSearchDto dto, Pageable pageable) {
        System.out.println("The cheque received was : \n" + dto.toString());
        Example<ChequeEntity> chequeExample = Example.of(
                mapper.map(dto)
        );
        System.out.println("Example cheques is calculated.");
        System.out.println("Example is: \n" + chequeExample);
       chequeRepo.findAll(chequeExample, pageable);
        return chequeRepo.findAll(
                chequeExample,
                pageable
            ).map(mapper::map);
    }

    public void submitById(Long id) {
        ChequeEntity entity = chequeRepo.getReferenceById(id);
        Cheque obj = mapper.toCheque(entity);
        obj.calculateStatus();
        mapper.submit(entity, obj);
        chequeRepo.save(entity);
    }
}
