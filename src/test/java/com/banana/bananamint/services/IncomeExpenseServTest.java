package com.banana.bananamint.services;

import com.banana.bananamint.domain.Expense;
import com.banana.bananamint.persistence.ExpenseJPARepository;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@ComponentScan(basePackages = {"com.banana.bananamint.service"})
@EnableAutoConfiguration
@SpringBootTest
class IncomeExpenseServTest {

     @Autowired
     private IncomeExpenseService servicio;

     @Autowired
     private ExpenseJPARepository repo;

     @PersistenceContext
     EntityManager em;

    @Test
    void addExpense_ok() {
        Expense expense = new Expense(null, null, 1500.00,LocalDate.now(),null, "pendiente");
        System.out.println("expense +++: " + expense);
        Expense nExpense = servicio.addExpense(1L, expense);
        System.out.println("nExpense +++: " + nExpense);
        assertNotNull(nExpense);
        assertThat(nExpense.getId(), greaterThan(0));
        assertEquals(nExpense.getStatus(),"pendiente");
        assertEquals(nExpense.getAmount(),1500.00);
    }

    @Test
    void addExpense_nok(){
        Expense expense = new Expense(null, null, 1500.00,LocalDate.now(),null, "p");

        Assertions.assertThrows(RuntimeException.class, () -> {
            servicio.addExpense(1L, expense);

        });

    }
}