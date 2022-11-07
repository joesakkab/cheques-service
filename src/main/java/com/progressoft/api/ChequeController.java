package com.progressoft.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.progressoft.model.ChequeDto;
import com.progressoft.service.ChequeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
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
        chequeService.saveAll(samples);
        return chequeService.getAllCheques();
    }

    @PostMapping()
    public ResponseEntity<Void> create(
            @Valid @RequestBody ChequeDto chequeDto
    ) {
        chequeService.createCheque(chequeDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ChequeDto> getById(
            @PathVariable(value = "id") Long id
    ) {
        try {
            return new ResponseEntity<>(
                    chequeService.getChequeById(id),
                    HttpStatus.OK
            );
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(
                    new ChequeDto(),
                    HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping()
    public ResponseEntity<List<ChequeDto>> getByIdAndAmountAndNumberAndDigit(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) BigDecimal amount,
            @RequestParam(required = false) String number,
            @RequestParam(required = false) String digit
    ) {
        return new ResponseEntity<>(
                chequeService.findChequesByAllFields(id, amount, number, digit),
                HttpStatus.OK
        );

    }

    @PutMapping("/{id}")
    public ResponseEntity<ChequeDto> updateById(
            @PathVariable(value = "id") Long id,
            @Valid @RequestBody ChequeDto givenChequeDto
    ) {
        try {
            chequeService.updateChequeById(id, givenChequeDto);
            return getById(id);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(
                    new ChequeDto(),
                    HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(
            @PathVariable(value = "id") Long id
    ) {
        try {
            return new ResponseEntity<>(
                    chequeService.deleteChequeByID(id),
                    HttpStatus.NO_CONTENT
            );
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(
                    "No cheque with id " + id + " found.",
                    HttpStatus.NOT_FOUND);
        }
    }
}
