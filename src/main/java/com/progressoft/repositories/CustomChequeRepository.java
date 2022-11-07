package com.progressoft.repositories;

import com.progressoft.entities.Cheque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CustomChequeRepository {
    List<Cheque> findChequesByAllFields(
            Long id,
            BigDecimal amount,
            String number,
            String digit
    );
}
