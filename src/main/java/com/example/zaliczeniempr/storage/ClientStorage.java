package com.example.zaliczeniempr.storage;

import com.example.zaliczeniempr.model.Client;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientStorage {

    private final List<Client> clientList = new ArrayList<>();

    public ClientStorage() {
        clientList.add(new Client("1", 100));
        clientList.add(new Client("2", 200));
        clientList.add(new Client("3", 300));
        clientList.add(new Client("4", 400));
        clientList.add(new Client("5", 500));
    }

    public List<Client> getAllClient() {
        return clientList;
    }

    public Client addNewClient(Client client) {
        clientList.add(client);
        return client;
    }

    public List<Client> findById(String id) {
        return clientList
                .stream()
                .filter(storage -> storage.getId().equals(id))
                .findFirst()
                .stream().toList();
    }

}
