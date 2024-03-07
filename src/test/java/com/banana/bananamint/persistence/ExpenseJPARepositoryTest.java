package com.banana.bananamint.persistence;

import com.banana.bananamint.domain.Account;
import com.banana.bananamint.domain.Expense;
import com.banana.bananamint.domain.Income;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest()
@ComponentScan(basePackages = {"com.banana.bananamint.persistence"})
@AutoConfigureTestEntityManager
class ExpenseJPARepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(ExpenseJPARepositoryTest.class);

    @Autowired
    private ExpenseJPARepository repo;

   @Test
   void save_ok() {

        Expense expense = new Expense(null, null, 1500.00,LocalDate.now(),null, "pendiente");

        repo.save(expense);
        System.out.println("expense: "+ expense);

        assertNotNull(expense);

    }

    @Test
   void save_nok() {

        Expense expense = new Expense(null, null, 1500.00,LocalDate.now(),null, "p");

        assertThrows(Exception.class, ()->{
            repo.save(expense);
        });
    }
}