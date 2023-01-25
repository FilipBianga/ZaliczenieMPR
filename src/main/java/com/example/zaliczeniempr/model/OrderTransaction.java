package com.example.zaliczeniempr.model;

import com.example.zaliczeniempr.storage.ClientStorage;

import java.util.List;

public class OrderTransaction {
    private Status status;
    private int newSaldo;

    public OrderTransaction(Status status, int newSaldo) {
        this.status = status;
        this.newSaldo = newSaldo;
    }

    @Override
    public String toString() {
        return "Status: " + status + " " + "saldo: " + newSaldo;
    }
}
