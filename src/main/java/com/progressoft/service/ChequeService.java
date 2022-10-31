package com.progressoft.service;

import com.progressoft.model.Cheque;
import com.progressoft.repositories.ChequeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChequeService {

    @Autowired
    public ChequeRepository chequeRepo;

    public Cheque saveOrUpdate(Cheque cheque) {
        return chequeRepo.save(cheque);
    }

    public Cheque findChequeById(Long chequeId) {
        return chequeRepo.getReferenceById(chequeId);
    }

    public void deleteChequeByID(Long chequeId) {
        chequeRepo.deleteById(chequeId);
    }

    public Iterable<Cheque> findAll() {
        return chequeRepo.findAll();
    }

    public void saveAll(Iterable<Cheque> objects) {
        chequeRepo.saveAll(objects);
    }

    public boolean chequeExistsById(Long chequeId) {
        return chequeRepo.existsById(chequeId);
    }
}
