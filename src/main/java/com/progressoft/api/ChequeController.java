package com.progressoft.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.progressoft.dtos.ChequeDto;
import com.progressoft.service.ChequeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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

    @PostMapping(path = "/generate-samples")
    public Iterable<ChequeDto> addCheques() {
        List<ChequeDto> samples;
        try {
            samples = new ObjectMapper().readValue(Paths.get("src/main/resources/static/cheques-samples.json").toFile(),
                    new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        for (ChequeDto dto: samples) {
//            create(dto);
//        }
        return chequeService.getAllCheques();
    }

    @PostMapping()
    public ResponseEntity<?> create(
            @RequestBody @Valid ChequeDto chequeDto
    ) {
//        if (result.hasErrors()) {
//            return new ResponseEntity<>("Error in creating cheque", HttpStatus.BAD_REQUEST);
//        }
//        try {
            chequeService.createCheque(chequeDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
//        } catch (DataIntegrityViolationException e) {
//            return new ResponseEntity<>(
//                    e.getCause() + "\nCheque number " + chequeDto.getNumber() + " must be unique",
//                    HttpStatus.FORBIDDEN
//            );
//        }

    }

    @GetMapping()
    public ResponseEntity<?> getAll(
            @ModelAttribute("chequeDto") ChequeDto chequeDto,
            Pageable pageable
    ) {
        return new ResponseEntity<>(
                chequeService.findAllCheques(chequeDto, pageable),
                HttpStatus.OK
            );

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(
            @PathVariable(value = "id") Long id,
            @RequestBody @Valid ChequeDto givenChequeDto
    ) {
        try {
            chequeService.updateChequeById(id, givenChequeDto);
            return new ResponseEntity<>(
                    chequeService.getChequeById(id),
                    HttpStatus.OK
            );
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(
                    e.getCause() + "\nCheque with id " + id + " is not found.",
                    HttpStatus.NOT_FOUND);
//        } catch (DataIntegrityViolationException e) {
//            return new ResponseEntity<>(
//                    e.getCause() + "\nCheque number " + givenChequeDto.getNumber() + " must be unique",
//                    HttpStatus.FORBIDDEN
//            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(
            @PathVariable(value = "id") Long id
    ) {
        try {
            return new ResponseEntity<>(
                    chequeService.deleteChequeByID(id),
                    HttpStatus.OK
            );
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(
                    "No cheque with id " + id + " found.",
                    HttpStatus.NOT_FOUND);
        }
    }
}
