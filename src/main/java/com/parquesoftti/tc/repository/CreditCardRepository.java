package com.parquesoftti.tc.repository;

import com.parquesoftti.tc.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

Optional<CreditCard> findCreditCardByCardNumber(String creditCard);
}
