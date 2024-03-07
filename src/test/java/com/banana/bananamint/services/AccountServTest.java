package com.banana.bananamint.services;

import com.banana.bananamint.domain.Account;
import com.banana.bananamint.exception.AccountException;
import com.banana.bananamint.persistence.AccountJPARepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ComponentScan(basePackages = {"com.banana.bananamint.service"})
@EnableAutoConfiguration
@SpringBootTest
class AccountServTest {

     @Autowired
     private AccountService servicio;

      @Autowired
     private AccountJPARepository repo;

     @PersistenceContext
     EntityManager em;

    @Test
    void open_ok(){
        Account account = new Account(null, "Ahorro",LocalDate.now(),1500.00,1000.00,null,true, null, null);
        System.out.println("account +++: " + account);
        Account nAccount = servicio.open(1L, account);
        System.out.println("nAccount +++: " + nAccount);
        assertNotNull(nAccount);
        assertThat(nAccount.getId(), greaterThan(0L));
        assertEquals(nAccount.getType(),"Ahorro");
        assertEquals(nAccount.getBalance(),1500.00);

    }

    @Test
    void open_nok(){
        Account account = new Account(null, "A",LocalDate.now(),1500.00,1000.00,null,true, null, null);

        Assertions.assertThrows(RuntimeException.class, () -> {
            servicio.open(1L, account);

        });

    }



}