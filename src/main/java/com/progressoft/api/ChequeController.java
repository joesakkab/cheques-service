package com.progressoft.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.progressoft.model.Cheque;
import com.progressoft.service.ChequeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/cheques")
public class ChequeController {

    @Autowired
    private ChequeService chequeService;


    @PostMapping(path = "/generate-samples")
    public Iterable<Cheque> addCheques() {
        List<Cheque> samples;
        try {
            samples = new ObjectMapper().readValue(Paths.get("src/main/resources/static/cheques-samples.json").toFile(),
                    new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        chequeService.saveAll(samples);
        return chequeService.findAll();
    }



    // Create or add a cheque
    @PostMapping(path = "", consumes = {"application/json"})
    public ResponseEntity<Cheque> addCheque(@RequestBody Cheque cheque) {
        return new ResponseEntity<>(chequeService.saveOrUpdate(cheque), HttpStatus.CREATED);
    }

    // List all the cheques
    @GetMapping("")
    public Iterable<Cheque> getCheques() {
        return chequeService.findAll();
    }

    // Delete a cheque with given ID.
    @DeleteMapping("/{accountId}")
    public ResponseEntity<?> deleteCheque(@PathVariable long accountId) {
        if (chequeService.chequeExistsById(accountId)) {
            chequeService.deleteChequeByID(accountId);
            return new ResponseEntity<String>(
                    "Cheque with id " + accountId + " successfully deleted.", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<String>(
                "Unable to delete cheque with id " + accountId + ".", HttpStatus.NOT_FOUND);

    }

    // Update a cheque with given ID.
    @PutMapping("/{chequeId}")
    public ResponseEntity<Cheque> saveOrUpdateCheque(@PathVariable long chequeId, @RequestBody Cheque givenCheque) {
        Cheque currentCheque = chequeService.findChequeById(chequeId);
        currentCheque.setChequeAmount(givenCheque.getChequeAmount());
        currentCheque.setChequeNumber(givenCheque.getChequeNumber());
        currentCheque.setChequeDigit(givenCheque.getChequeDigit());
        return new ResponseEntity<Cheque>(chequeService.saveOrUpdate(currentCheque), HttpStatus.OK);
    }

    // Get an account with given ID.
    @GetMapping("/{chequeId}")
    public ResponseEntity<Cheque> getAccountById(@PathVariable("chequeId") long chequeId) {
        if (!chequeService.chequeExistsById(chequeId)) {
            return new ResponseEntity<Cheque>(new Cheque(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Cheque>(chequeService.findChequeById(chequeId), HttpStatus.FOUND);
    }
}
