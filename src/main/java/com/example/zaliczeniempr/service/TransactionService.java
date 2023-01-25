package com.example.zaliczeniempr.service;

import com.example.zaliczeniempr.model.Client;
import com.example.zaliczeniempr.model.OrderTransaction;
import com.example.zaliczeniempr.model.PaymentTransaction;
import com.example.zaliczeniempr.model.Status;
import com.example.zaliczeniempr.storage.BankStorage;
import com.example.zaliczeniempr.storage.ClientStorage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionService {

    private final ClientStorage clientStorage;
    private final BankStorage bankStorage;

    public TransactionService(ClientStorage clientStorage, BankStorage bankStorage) {
        this.clientStorage = clientStorage;
        this.bankStorage = bankStorage;
    }

    public Client addNewClient(String id, int value) {
        return clientStorage.addNewClient(new Client(id, value));
    }

    public List<Client> printClientById(String id) {
        if(clientStorage.findById(id).isEmpty()) {
            System.out.println("Nie ma takiego Klienta o podanym ID");
            return null;
        }
        return clientStorage.findById(id);
    }

    public OrderTransaction addNewOrderTransactionToTheBank(String id, int value) {
        List<Client> client = clientStorage.findById(id);

        if(client.get(0).getSaldo() >= value) {
            System.out.println("Wystarczająco środków na koncie");
            client.stream().map(x -> {
                x.setSaldo(x.getSaldo() - value);
                return x;
            }).collect(Collectors.toList());

            return bankStorage.addNewOrderTransaction(new OrderTransaction(Status.ACCEPTED, client.get(0).getSaldo()));
        }
        else {
            System.out.println("Nie masz wystarczająco kasy na koncie");
            return bankStorage.addNewOrderTransaction(new OrderTransaction(Status.DECLINED, client.get(0).getSaldo()));
        }
    }

    public PaymentTransaction addNewPaymentTransactionToTheBank(String id, int value) {
        List<Client> client = clientStorage.findById(id);

        if(client.isEmpty()) {
            System.out.println("Nie ma takiego kielnta o podanym ID");
//            return new PaymentTransaction(Status.DECLINED, 0);
            return bankStorage.addNewPaymentTransaction(new PaymentTransaction(Status.DECLINED, 0));
        } else {
            client.stream().map(x -> {
                x.setSaldo(x.getSaldo() + value);
                return x;
            }).collect(Collectors.toList());
            return bankStorage.addNewPaymentTransaction(new PaymentTransaction(Status.ACCEPTED, client.get(0).getSaldo()));
        }
    }


}
