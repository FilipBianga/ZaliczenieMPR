package com.example.zaliczeniempr.storage;

import com.example.zaliczeniempr.model.Client;
import com.example.zaliczeniempr.model.OrderTransaction;
import com.example.zaliczeniempr.model.PaymentTransaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BankStorage {

    private final List<OrderTransaction> orderTransactionList = new ArrayList<>();
    private final List<PaymentTransaction> paymentTransactionList = new ArrayList<>();

    public BankStorage() {
    }

    public OrderTransaction addNewOrderTransaction(OrderTransaction orderTransaction) {
        orderTransactionList.add(orderTransaction);
        return orderTransaction;
    }

    public PaymentTransaction addNewPaymentTransaction(PaymentTransaction paymentTransaction) {
        paymentTransactionList.add(paymentTransaction);
        return paymentTransaction;
    }

}
