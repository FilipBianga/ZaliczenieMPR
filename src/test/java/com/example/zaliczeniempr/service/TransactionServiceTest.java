package com.example.zaliczeniempr.service;


import com.example.zaliczeniempr.model.Client;
import com.example.zaliczeniempr.model.OrderTransaction;
import com.example.zaliczeniempr.model.PaymentTransaction;
import com.example.zaliczeniempr.model.Status;
import com.example.zaliczeniempr.storage.ClientStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {
    @Mock
    private ClientStorage clientStorage;

    @InjectMocks
    private TransactionService service;

    private Client client;

    @BeforeEach
    void initClient() {
        client = new Client("1", 1000);
    }

    @DisplayName("Obnizenie salda klienta")
    @Test
    void transactionShouldBeDecreaseSaldoByProperAmount(){
        when(clientStorage.findById(any())).thenReturn(client);

        OrderTransaction orderTransaction = service.addNewOrderTransactionToTheBank("1", 500);

        assertThat(orderTransaction.getNewSaldo()).isEqualTo(500);
    }

    @DisplayName("Tranzakcja uniewazniona jesli klienta nie ma")
    @Test
    void transactionShouldBeDeclinedWhenClientNotFound() {
        //given
        when(clientStorage.findById(any())).thenReturn(null);

        //when
        PaymentTransaction paymentTransaction = service.addNewPaymentTransactionToTheBank("11", 1000);

        //then
        assertThat(paymentTransaction.getStatus()).isEqualTo(Status.DECLINED);
    }

    @DisplayName("Status Accepted jesli klient istnieje")
    @Test
    void statusShouldBeAcceptedWhenClientExist() {
        //given
        when(clientStorage.findById(any())).thenReturn(client);

        //when
        OrderTransaction orderTransaction = service.addNewOrderTransactionToTheBank("1", 100);

        //then
        assertThat(orderTransaction.getStatus()).isEqualTo(Status.ACCEPTED);
    }

    @DisplayName("Wzrost salda klienta")
    @Test
    void saldoShouldBeIncreaseWhenClientExist() {
        //given
        when(clientStorage.findById(any())).thenReturn(client);

        //when
        PaymentTransaction paymentTransaction = service.addNewPaymentTransactionToTheBank("1", 1000);

        //then
        assertThat(paymentTransaction.getNewSaldo()).isEqualTo(2000);

    }
}