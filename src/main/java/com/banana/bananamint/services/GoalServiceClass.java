package com.banana.bananamint.services;

import com.banana.bananamint.domain.Customer;
import com.banana.bananamint.domain.Goal;
import com.banana.bananamint.payload.GoalApproximation;
import com.banana.bananamint.exception.CustomerException;
import com.banana.bananamint.exception.GoalException;
import com.banana.bananamint.payload.Debt;
import com.banana.bananamint.persistence.CustomerJPARepository;
import com.banana.bananamint.persistence.GoalJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoalServiceClass implements GoalService {

    @Autowired
    private GoalJPARepository repoGoal;

    @Autowired
    private CustomerJPARepository repoCustomer;

    @Override
    public List<Goal> showAll(Long idCustomer) throws GoalException {
        return null;
    }

    @Override
    public List<Goal> add(Long idCustomer, Goal goal) throws GoalException {
        Customer aCustomer = repoCustomer.findById(idCustomer).orElseThrow(() -> new CustomerException("Cliente no encontrado"));

        goal.validarGoal();

        List<Goal> listGoals = aCustomer.getGoals();
        goal.setUser(aCustomer);
        listGoals.add(goal);
        repoGoal.save(goal);

        return listGoals;

    }

    @Override
    public List<GoalApproximation> generateReport(Long idCustomer, LocalDate initDate, LocalDate finalDate) throws GoalException, SQLException {
        List<Goal> listGoals = repoGoal.findByUser_IdAndTargetDateBetween(idCustomer, initDate, finalDate);
        GoalApproximation gApp = new GoalApproximation();
        List<GoalApproximation> listRet = new ArrayList<>();

        for (Goal nGoals : listGoals) {

            gApp.setGoal(nGoals);
            gApp.setTargetAmountDifference(1000.00 - nGoals.getTargetAmount());
            if (gApp.getTargetAmountDifference() > 0) {
                gApp.setTendency(1);
            } else if (gApp.getTargetAmountDifference() == 0) {
                gApp.setTendency(1);
            } else gApp.setTendency(-1);

            gApp.setEstimatedReachingTargetDate(nGoals.getTargetDate());
            listRet.add(gApp);
        }

        return listRet;
    }

    @Override
    public List<Debt> accumulatedDebt(Long idCustomer, LocalDate initDate, LocalDate finalDate) throws GoalException {
        return null;
    }
}
