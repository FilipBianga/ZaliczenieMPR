package com.example.zaliczeniempr;

import com.example.zaliczeniempr.model.Client;
import com.example.zaliczeniempr.service.TransactionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZaliczenieMprApplication {

    public ZaliczenieMprApplication(TransactionService service) {
        Client newClient = service.addNewClient("6", 600);
        System.out.println(newClient);
        System.out.println();
        System.out.println(service.addNewOrderTransactionToTheBank("1", 10));
        System.out.println(service.printClientById("1"));
        System.out.println();
        System.out.println(service.addNewPaymentTransactionToTheBank("11", 500));
        System.out.println(service.printClientById("1"));
        System.out.println();
        System.out.println(service.addNewPaymentTransactionToTheBank("1", 500));
        System.out.println(service.printClientById("1"));
        System.out.println();
    }

    public static void main(String[] args) {
        SpringApplication.run(ZaliczenieMprApplication.class, args);
    }

}
