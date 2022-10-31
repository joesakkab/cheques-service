package com.progressoft.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.progressoft.model.Account;
import com.progressoft.model.Cheque;
import com.progressoft.service.AccountService;
import com.progressoft.service.ChequeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(path = "/generate-samples")
    public Iterable<Account> addAccounts() {
        List<Account> samples;
        try {
            samples = new ObjectMapper().readValue(Paths.get("src/main/resources/static/accounts-samples.json").toFile(),
                    new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        accountService.saveAll(samples);
        return accountService.findAll();
    }

    // Create or add a cheque
    @PostMapping(path = "", consumes = {"application/json"})
    public ResponseEntity<Account> addCheque(@RequestBody Account account) {
        return new ResponseEntity<>(accountService.saveOrUpdate(account), HttpStatus.CREATED);
    }

    // List all the cheques
    @GetMapping("")
    public Iterable<Account> getAccount() {
        return accountService.findAll();
    }

    // Delete an account with given ID.
    @DeleteMapping("/{accountId}")
    public ResponseEntity<?> deleteCheque(@PathVariable long accountId) {
        if (accountService.accountExistsById(accountId)) {
            accountService.deleteAccountById(accountId);
            return new ResponseEntity<String>(
                    "Account with id " + accountId + " successfully deleted.", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<String>(
                "Unable to delete account with id " + accountId + ".", HttpStatus.NOT_FOUND);

    }

    // Update a cheque with given ID.
    @PutMapping("/{accountId}")
    public ResponseEntity<Account> saveOrUpdateCheque(@PathVariable long accountId, @RequestBody Account givenAccount) {
        Account currentAccount = accountService.findAccountById(accountId);
        currentAccount.setBankCode(givenAccount.getBankCode());
        currentAccount.setBranchCode(givenAccount.getBranchCode());
        currentAccount.setAccountNumber(givenAccount.getAccountNumber());
        currentAccount.setPayeeCheques(givenAccount.getPayeeCheques());
        currentAccount.setDrawerCheques(givenAccount.getDrawerCheques());
        return new ResponseEntity<Account>(accountService.saveOrUpdate(currentAccount), HttpStatus.OK);
    }

    // Get an account with given ID.
    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable("accountId") long accountId) {
        if (!accountService.accountExistsById(accountId)) {
            return new ResponseEntity<Account>(new Account(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Account>(accountService.findAccountById(accountId), HttpStatus.FOUND);
    }
}
