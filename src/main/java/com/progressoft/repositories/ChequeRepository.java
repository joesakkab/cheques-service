package com.progressoft.repositories;

import com.progressoft.entities.Cheque;
import com.progressoft.model.ChequeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ChequeRepository extends JpaRepository<Cheque, Long>, CustomChequeRepository {

}
