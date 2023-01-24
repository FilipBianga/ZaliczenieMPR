package com.example.zaliczeniempr.model;

public class Client {
    private String id;
    private int saldo;

    public Client(String id, int saldo) {
        this.id = id;
        this.saldo = saldo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "idUser: " + id + " " + "saldo: " + saldo;
    }
}
