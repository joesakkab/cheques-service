package com.progressoft.repositories;

import com.progressoft.entities.ChequeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChequeRepository extends JpaRepository<ChequeEntity, Long> {
    Boolean existsChequeByNumber(String number);
}
