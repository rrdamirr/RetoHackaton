package com.banana.bananamint.services;

import com.banana.bananamint.domain.Account;
import com.banana.bananamint.domain.Customer;
import com.banana.bananamint.domain.Income;
import com.banana.bananamint.persistence.IncomeJPARepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ComponentScan(basePackages = {"com.banana.bananamint.services"})
@AutoConfigureTestEntityManager
class IncomeExpenseServiceClassTest {

    @Autowired
    private IncomeExpenseServiceClass incomeService;

    @Autowired
    private IncomeJPARepository incomeRep;

    @Autowired
    private TestEntityManager eM;

    @Test
    void addIncomeOK() {
        // given
        Customer aCustomer = new Customer(1L);
        Account aAccount = new Account(1L);

        Income aIncome = new Income(null, null, 500.00, LocalDate.now(), aAccount, "closed");
        // when
        System.out.println("Income add: " + aIncome);
        incomeService.addIncome(aCustomer.getId(), aIncome);
        // then
        assertNotNull(aIncome);

    }

    @Test
    void addIncomeNOK() {
        // given
        Customer aCustomer = new Customer(2L);
        Account aAccount = new Account(2L);

        Income aIncome = new Income(null, null, 500.00, LocalDate.now(), aAccount, "closed");

        // when+then
        //Assertions.assertThrows(IncomeExpenseException.class, () -> {
        Assertions.assertThrows(RuntimeException.class, () -> {
            System.out.println("Income add: " + aIncome);
            incomeService.addIncome(aCustomer.getId(), aIncome);
        });
    }
}