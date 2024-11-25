package com.parquesoftti.tc.service;

import com.parquesoftti.tc.model.Transaction;

public interface TransactionService {

    Transaction payment(Transaction transaction);

    Transaction reversePayment(Long id);

    Transaction findTransactionById(Long id);

}
