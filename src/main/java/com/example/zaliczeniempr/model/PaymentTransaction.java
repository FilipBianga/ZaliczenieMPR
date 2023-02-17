package com.example.zaliczeniempr.model;

public class PaymentTransaction {
    private Status status;
    private int newSaldo;

    public PaymentTransaction(Status status, int newSaldo) {
        this.status = status;
        this.newSaldo = newSaldo;
    }

    public Status getStatus() {
        return status;
    }

    public int getNewSaldo() {
        return newSaldo;
    }

    @Override
    public String toString() {
        return "Status: " + status + " " + "saldo: " + newSaldo;
    }
}
