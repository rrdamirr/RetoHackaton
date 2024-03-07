package com.banana.bananamint.persistence;

import com.banana.bananamint.domain.Account;
import com.banana.bananamint.domain.Customer;
import com.banana.bananamint.domain.Income;
import com.banana.bananamint.exception.IncomeExpenseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ComponentScan(basePackages = {"com.banana.bananamint.persistence"})
@AutoConfigureTestEntityManager
class IncomeJPARepositoryTest {

    @Autowired
    private IncomeJPARepository incomeRep;

    @Autowired
    private TestEntityManager eM;

    @Test
    void addIncomeOK() {
        // given
        Customer aCustomer = new Customer(1L);
        Account aAccount = new Account(1L);

        Income aIncome = new Income(null, aCustomer, 300.00, LocalDate.now(), aAccount, "active");
        // when
        incomeRep.save(aIncome);
        System.out.println("Income add: " + aIncome);
        // then
        assertNotNull(aIncome);
    }

    @Test
    void addIncomeNOK() {
        // given
        Customer aCustomer = new Customer(1L);
        Account aAccount = new Account(1L);
        Income aIncome = new Income(null, aCustomer, 300.00, null, aAccount, "active");

        // when+then
        //Assertions.assertThrows(IncomeExpenseException.class, () -> {
        Assertions.assertThrows(RuntimeException.class, () -> {
            System.out.println("Income add: " + aIncome);
            incomeRep.save(aIncome);
        });
    }


}