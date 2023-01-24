package com.example.zaliczeniempr.model;

import com.example.zaliczeniempr.storage.ClientStorage;

import java.util.List;

public class OrderTransaction {
    private ClientStorage clientStorage;
    private int newSaldo;


    public <R> OrderTransaction(R collect, int value) {

    }

    @Override
    public String toString() {
        return "idUser: " + clientStorage + " " + "saldo: " + newSaldo;
    }
}
