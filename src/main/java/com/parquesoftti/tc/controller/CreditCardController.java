package com.parquesoftti.tc.controller;

import com.parquesoftti.tc.model.CreditCard;
import com.parquesoftti.tc.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@Slf4j
@RequestMapping("/api/v1/creditcard")
public class CreditCardController {

    private final CreditCardService creditCardService;

    @GetMapping()
    public ResponseEntity<List<CreditCard>> getCards(){
        return ResponseEntity.ok().body(creditCardService.getCreditCards());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditCard> getCardById(
            @PathVariable Long id){
        return ResponseEntity.ok().body(creditCardService.getCardById(id).get());
    }

    @PostMapping()
    public ResponseEntity<CreditCard> saveCreditCard(@RequestBody CreditCard creditCard) {
        return ResponseEntity.ok().body(creditCardService.saveCreditCard(creditCard));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditCard> updateCreditCard(@RequestBody CreditCard creditCard,@PathVariable Long id) {
        return ResponseEntity.ok().body(creditCardService.updateCreditCard(creditCard, id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCard( @PathVariable Long id){
        creditCardService.deleteCard(id);
        return ResponseEntity.ok().body("");
    }
    @GetMapping("/cardnumber/{creditcard}")
    public ResponseEntity<CreditCard> getCreditCardsByCardNumber(
            @PathVariable String creditcard){
        return ResponseEntity.ok().body(creditCardService.getCreditCardsByCardNumber(creditcard));
    }
}
