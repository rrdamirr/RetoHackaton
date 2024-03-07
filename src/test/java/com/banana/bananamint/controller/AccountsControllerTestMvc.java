package com.banana.bananamint.controller;

import com.banana.bananamint.domain.Account;
import com.banana.bananamint.persistence.AccountJPARepository;
import com.banana.bananamint.services.AccountService;
import com.banana.bananamint.util.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountsController.class)
public class AccountsControllerTestMvc {

    @BeforeEach
    public void setUp() {
        Account account = new Account(1L, "Ahorro", LocalDate.now(),1500.00,1000.00,null,true, null, null);
        Mockito.when(service.open(Mockito.any(), Mockito.any()))
                .thenReturn(account);


    }

    @Autowired
    private AccountsController accountsController;

    @MockBean
    private AccountService service;

     @Autowired
    private MockMvc mvc;


    @Test
    void addAccount_OK() {

//        given
        Long id= 1L;
        Account nAccount = new Account(null, "Ahorro", LocalDate.now(),1500.00,1000.00,null,true, null, null);

//        when + then
        ResponseEntity<Account> responseEntity = accountsController.abrirCuenta(id, nAccount);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void addAccount_OK2() throws Exception {

//        given
        Long id = 1L;
        Account nAccount = new Account(null, "Ahorro", LocalDate.now(),1500.00,1000.00,null,true, null, null);


//        when + then
        mvc.perform(post("/account/customer/" + id)
                        .content(JsonUtil.asJsonString(nAccount))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
        ;
    }
}
