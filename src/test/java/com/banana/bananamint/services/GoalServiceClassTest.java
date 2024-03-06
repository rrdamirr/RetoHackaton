package com.banana.bananamint.services;

import com.banana.bananamint.domain.Customer;
import com.banana.bananamint.domain.Goal;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest()
@ComponentScan(basePackages = {"com.banana.bananamint.services"})
@AutoConfigureTestEntityManager
class GoalServiceClassTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GoalServiceClass serviceGoal;

    @Test
    void addOK() {
        // given
        //Customer aCustomer = entityManager.find(Customer.class,1L);


        Goal aGoal = new Goal(null, "Ahorro", "Ahorro en mes de marzo", 300.00, "sataus", LocalDate.now(), null);

        // when
        serviceGoal.add(1L, aGoal);

        System.out.println(aGoal);

        // then
        assertThat(aGoal.getId()).isGreaterThan(0);


    }

    @Test
    void addNOK() {
        // given


        Goal aGoal = new Goal(null, "A", "Ahorro en mes de marzo", 300.00, "sataus", LocalDate.now(), null);

        // when


        // then

        Assertions.assertThrows(RuntimeException.class, () -> {
            serviceGoal.add(1L, aGoal);

        });


    }
}