package com.banana.bananamint.persistence;

import com.banana.bananamint.domain.Customer;
import com.banana.bananamint.domain.Goal;
import com.banana.bananamint.exception.CustomerException;
import com.banana.bananamint.exception.GoalException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
@ExtendWith(SpringExtension.class)
@DataJpaTest()
@ComponentScan(basePackages = {"com.banana.bananamint.persistence"})
@AutoConfigureTestEntityManager
class GoalJPARepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GoalJPARepository jpaRepo;

    @Test
    void saveOK() {
        // given
        Customer aCustomer = entityManager.find(Customer.class,1L);
        //Customer aCustomer = new Customer();
        //entityManager.persist(aCustomer);
        //aCustomer.setId(1L);

        Goal aGoal = new Goal(null, "Ahorro", "Ahorro en mes de marzo", 300.00, "sataus", LocalDate.now(), aCustomer);

        // when
        jpaRepo.save(aGoal);

        System.out.println(aGoal);

        // then
        assertThat(aGoal.getId()).isGreaterThan(0);
    }

    @Test
    void saveNOK() {
        // given
        Customer aCustomer = entityManager.find(Customer.class,1L);

        Goal aGoal = new Goal(null, "A", "Ahorro en mes de marzo", 300.00, "status", LocalDate.now(), aCustomer);

       Assertions.assertThrows(RuntimeException.class, () -> {
            jpaRepo.save(aGoal);

        });
    }




}