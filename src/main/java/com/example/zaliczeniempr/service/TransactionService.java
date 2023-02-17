package com.example.zaliczeniempr.service;

import com.example.zaliczeniempr.model.Client;
import com.example.zaliczeniempr.model.OrderTransaction;
import com.example.zaliczeniempr.model.PaymentTransaction;
import com.example.zaliczeniempr.model.Status;
import com.example.zaliczeniempr.storage.ClientStorage;
import org.springframework.stereotype.Component;


@Component
public class TransactionService {

    private final ClientStorage clientStorage;

    public TransactionService(ClientStorage clientStorage) {
        this.clientStorage = clientStorage;
    }

    public Client addNewClient(String id, int value) {
        return clientStorage.addNewClient(new Client(id, value));
    }

    public Client printClientById(String id) {
        if(clientStorage.findById(id) == null) {
            System.out.println("Nie ma takiego Klienta o podanym ID");
            return null;
        }
        return clientStorage.findById(id);
    }

    public OrderTransaction addNewOrderTransactionToTheBank(String id, int value) {
        Client client = clientStorage.findById(id);

        if(client == null) {
            System.out.println("Nie ma takiego kielnta o podanym ID");
            return new OrderTransaction(Status.DECLINED, 0);
        }

        if(client.getSaldo() < value) {
            return new OrderTransaction(Status.DECLINED, client.getSaldo());
        }

        client.setSaldo(client.getSaldo() - value);
        return new OrderTransaction(Status.ACCEPTED, client.getSaldo());
    }

    public PaymentTransaction addNewPaymentTransactionToTheBank(String id, int value) {
        Client client = clientStorage.findById(id);

        if(client == null) {
            System.out.println("Nie ma takiego kielnta o podanym ID");
            return new PaymentTransaction(Status.DECLINED, 0);
        }

        client.setSaldo(client.getSaldo() + value);
        return new PaymentTransaction(Status.ACCEPTED, client.getSaldo());
    }


}
