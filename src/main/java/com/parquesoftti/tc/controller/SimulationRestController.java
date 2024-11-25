package com.parquesoftti.tc.controller;

import com.parquesoftti.tc.model.Transaction;
import com.parquesoftti.tc.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@Slf4j
@RequestMapping("/api/v1/simulation")
public class SimulationRestController {

    private final TransactionService transactionService;

    @PostMapping()
    public ResponseEntity<Transaction> payment(@RequestBody Transaction transaction) {
        return ResponseEntity.ok(transactionService.payment(transaction));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> reversePayment(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.reversePayment(id));
    }

}
