package com.parquesoftti.tc.service.impl;

import com.parquesoftti.tc.model.CreditCard;
import com.parquesoftti.tc.model.Transaction;
import com.parquesoftti.tc.repository.TransactionRepository;
import com.parquesoftti.tc.service.CreditCardService;
import com.parquesoftti.tc.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final CreditCardService creditCardService;

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Transaction payment(Transaction transaction) {
        validateTransactionPayment(transaction);
        CreditCard creditCard = creditCardService.getCardById(transaction.getCreditCard().getId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "No se ha encontrado la Tarjeta de crédito con id " + transaction.getCreditCard().getId()));

        transaction.setCreditCard(creditCard);
        transaction.setStatus("Pagada");
        transaction.setDate(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Transaction reversePayment(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID debe ser positivo y no nulo.");
        }
        Transaction existingTransaction = findTransactionById(id);

        existingTransaction.setStatus("Reversada");
        existingTransaction.setDate(LocalDateTime.now());
        return transactionRepository.save(existingTransaction);
    }

    @Override
    @Transactional(readOnly = true)
    public Transaction findTransactionById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No se ha encontrado la Transacción con id " + id));
    }

    /**
     * Valida los datos de una Transaccion tipo Payment
     *
     * @param transaction La transacción tipo pago a realizar
     */
    private void validateTransactionPayment(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("La Transacción no puede ser nula.");
        }

        if (transaction.getAmount() == null || transaction.getAmount().equals(BigDecimal.ZERO)
                || transaction.getAmount().equals(BigDecimal.ZERO)
                || transaction.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El monto de la transacción no puede ser nulo ni cero (0).");
        }

        if (transaction.getCreditCard() == null) {
            throw new IllegalArgumentException("La tarjeta de crédito no debe ser nula.");
        }

        if (transaction.getCreditCard().getId() == null || transaction.getCreditCard().getId().equals(0L)) {
            throw new IllegalArgumentException("El Id de la Tarjeta de crédito no debe ser nulo ni cero (0).");
        }

    }
}
