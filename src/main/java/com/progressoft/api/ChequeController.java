package com.progressoft.api;

import com.progressoft.dtos.cheques.ChequePostDto;
import com.progressoft.dtos.cheques.ChequePutDto;
import com.progressoft.dtos.cheques.ChequeSearchDto;
import com.progressoft.service.ChequeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
        System.out.println(chequePostDto);
            chequeService.createCheque(chequePostDto);
            return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping()
    public ResponseEntity<?> getAll(
//            @RequestBody @Valid ChequeSearchDto dto,
            @ModelAttribute("chequeDto") ChequeSearchDto dto,
            Pageable pageable
    ) {
        System.out.println(dto);
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

    @GetMapping("/{id}/submit")
    public ResponseEntity<?> submitById(
            @PathVariable(value = "id") Long id
    ) {
        chequeService.submitById(id);
        return new ResponseEntity<>(
                "Cheque with id " + id + " successfully submitted.",
                HttpStatus.OK
        );
    }
}
