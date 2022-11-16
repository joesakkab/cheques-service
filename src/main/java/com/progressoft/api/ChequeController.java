package com.progressoft.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.progressoft.dtos.cheques.ChequeGetDto;
import com.progressoft.dtos.cheques.ChequePostDto;
import com.progressoft.dtos.cheques.ChequePutDto;
import com.progressoft.service.ChequeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/cheques")
public class ChequeController {

    private final ChequeService chequeService;

    @Autowired
    public ChequeController (
            ChequeService chequeService
    ) {
        this.chequeService = chequeService;
    }

    @PostMapping()
    public ResponseEntity<?> create(
            @RequestBody @Valid ChequePostDto chequePostDto
    ) {
            chequeService.createCheque(chequePostDto);
            return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping()
    public ResponseEntity<?> getAll(
            @ModelAttribute("chequeDto") ChequeGetDto dto,
            Pageable pageable
    ) {
        return new ResponseEntity<>(
                chequeService.findAllCheques(dto, pageable),
                HttpStatus.OK
            );

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(
            @PathVariable(value = "id") Long id,
            @RequestBody @Valid ChequePutDto dto
    ) {
            chequeService.updateChequeById(id, dto);
            return new ResponseEntity<>(
                    chequeService.getChequeById(id),
                    HttpStatus.OK
            );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(
            @PathVariable(value = "id") Long id
    ) {
        chequeService.deleteChequeByID(id);
        return new ResponseEntity<>(
                "Cheque with id " + id + " successfully deleted.",
                HttpStatus.OK
        );
    }
}
