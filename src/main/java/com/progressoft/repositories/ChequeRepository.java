package com.progressoft.repositories;

import com.progressoft.entities.Cheque;
import com.progressoft.model.ChequeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChequeRepository extends JpaRepository<Cheque, Long> { }
