package com.banana.bananamint.controller;

import com.banana.bananamint.domain.Goal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest()
@ComponentScan(basePackages = {"com.banana.bananamint.controller", "com.banana.bananamint.services"})
@AutoConfigureTestEntityManager
class RestControllerCustomerTest {

@Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RestControllerCustomer controllerCustomer;


@Test
    public void addGoalOK() {
        // given
        Goal aGoal = new Goal(null, "Ahorro", "Ahorro en mes de marzo", 300.00, "sataus", LocalDate.now(), null);

        // when
        ResponseEntity<List<Goal>> response = controllerCustomer.addGoal(1L, aGoal);

        System.out.println("aGoal ++++: " + aGoal);


        // then
        //assertThat(aProyecto.getId()).isGreaterThan(0);
        assertThat(response.getStatusCode().value())
                .isEqualTo(HttpStatus.CREATED.value());

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isGreaterThan(0);
    }

    @Test
    public void crearProyectoNOK() {
        // given
        Goal aGoal = new Goal(null, "A", "Ahorro en mes de marzo", 300.00, "sataus", LocalDate.now(), null);

        // when


        // then

        Assertions.assertThrows(RuntimeException.class, () -> {
            ResponseEntity<List<Goal>> response = controllerCustomer.addGoal(25L ,aGoal);

        });

    }

}