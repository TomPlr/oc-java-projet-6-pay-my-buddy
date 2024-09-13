package com.paymybuddy.controller;

import com.paymybuddy.dto.TransactionFormDto;
import com.paymybuddy.model.TransactionModel;
import com.paymybuddy.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionModel> create(@RequestBody TransactionFormDto transactionFormDto) throws Exception {
        return new ResponseEntity<>(transactionService.save(transactionFormDto), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TransactionModel>> findAllByUsername() {
        return new ResponseEntity<>(transactionService.findAll(), HttpStatus.OK);
    }

}
