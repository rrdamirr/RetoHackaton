package com.banana.bananamint.services;

import com.banana.bananamint.domain.Customer;
import com.banana.bananamint.domain.Goal;
import com.banana.bananamint.payload.GoalApproximation;
import com.banana.bananamint.persistence.GoalJPARepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

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

    @Autowired
    private GoalJPARepository repoGoal;

    @Test
    void addOK() {
        // given

        Goal aGoal = new Goal(null, "Ahorro", "Ahorro en mes de marzo", 300.00, "sataus", LocalDate.now(), null);
        List<Goal> listGoalac = repoGoal.findAll();
        // when
        List<Goal> listGoalnew = serviceGoal.add(1L, aGoal);

        System.out.println("aGoal ++++: " + aGoal);

        // then
        assertThat(listGoalac.size() == listGoalnew.size() + 1);


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

    @Test
    void generateReportOK() throws SQLException {

        List<GoalApproximation> listGoalsApp = serviceGoal.generateReport(4L,LocalDate.of(2024,04, 01), LocalDate.of(2024, 06, 30));

        System.out.println("listGoals +++++ : " + listGoalsApp);

        assertThat(listGoalsApp.size() > 0);


    }
}