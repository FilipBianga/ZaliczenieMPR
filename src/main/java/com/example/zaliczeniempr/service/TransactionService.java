package com.example.zaliczeniempr.service;

import com.example.zaliczeniempr.model.Client;
import com.example.zaliczeniempr.model.OrderTransaction;
import com.example.zaliczeniempr.storage.ClientStorage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionService {

    private final ClientStorage clientStorage;

    public TransactionService(ClientStorage clientStorage) {
        this.clientStorage = clientStorage;
    }

    public Client addNewClient(String id, int value) {
        return clientStorage.addNewClient(new Client(id, value));
    }

    public OrderTransaction addNewOrderTransaction(String id, int value) {
        List<Client> allClient = clientStorage.getAllClient();
        return new OrderTransaction(allClient.stream().filter(x -> x.getId().equals(id)).findFirst().map(y -> {
            y.setSaldo(value);
            return y;
        }).stream().collect(Collectors.toList()), value);
    }
}
