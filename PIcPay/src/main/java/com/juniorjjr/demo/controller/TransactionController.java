package com.juniorjjr.demo.controller;

import com.juniorjjr.demo.domain.dto.TransactionDTO;
import com.juniorjjr.demo.domain.transaction.Transaction;
import com.juniorjjr.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {


    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction>newTransaction(@RequestBody TransactionDTO transactionDTO) throws Exception {
        Transaction  newTransacion = this.transactionService.createTransacion(transactionDTO);
        return new ResponseEntity<>(newTransacion, HttpStatus.CREATED);

    }
}
