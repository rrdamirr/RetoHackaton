package com.banana.bananamint.controller;

import com.banana.bananamint.domain.Account;
import com.banana.bananamint.persistence.AccountJPARepository;
import com.banana.bananamint.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountsController.class)
public class AccountsControllerTestMvc {

    @BeforeEach
    public void setUp() {
        Account account = new Account(null, "Ahorro", LocalDate.now(),1500.00,1000.00,null,true, null, null);
        Mockito.when(service.open(Mockito.any(), Mockito.any()))
                .thenReturn(account);


    }

    @Autowired
    private AccountsController accountsController;

    @MockBean
    private AccountService service;


    @Test
    void addAccount_OK() {

//        given
        Long id= 1L;
        Account nAccount = new Account(null, "Ahorro", LocalDate.now(),1500.00,1000.00,null,true, null, null);

//        when + then
        ResponseEntity<Account> responseEntity = accountsController.abrirCuenta(id, nAccount);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
    }
}
