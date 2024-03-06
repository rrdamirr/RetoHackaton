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
import java.time.LocalDate;
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
        boolean validGoal = goal.validarGoal();

        if (validGoal) {
            List<Goal> listGoals = aCustomer.getGoals();
            goal.setUser(aCustomer);
            listGoals.add(goal);
            repoGoal.save(goal);
            return listGoals;
        } else throw new GoalException("Goal is not valid");


    }

    @Override
    public List<GoalApproximation> generateReport(Long idCustomer, LocalDate initDate, LocalDate finalDate) throws GoalException {
        return null;
    }

    @Override
    public List<Debt> accumulatedDebt(Long idCustomer, LocalDate initDate, LocalDate finalDate) throws GoalException {
        return null;
    }
}
