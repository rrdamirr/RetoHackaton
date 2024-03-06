package com.banana.bananamint.services;

import com.banana.bananamint.domain.Income;
import com.banana.bananamint.exception.IncomeExpenseException;
import com.banana.bananamint.payload.IncomeExpenseComparison;
import com.banana.bananamint.persistence.ExpenseJPARepository;
import com.banana.bananamint.persistence.IncomeJPARepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
@Service
public class IncomeExpenseServiceClass implements IncomeExpenseService {


    private Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private IncomeJPARepository incomeRepo;

    @Autowired
    private ExpenseJPARepository expenseRepo;

    @Override
    public List<Income> showAllIncomes(Long idCustomer) throws IncomeExpenseException {
        return null;
    }

    @Override
    @Transactional
    public Income addIncome(Long idCustomer, Income income) throws IncomeExpenseException {

        Income aIncome = new Income();
        incomeRepo.save(aIncome);
        return aIncome;
    }

    @Override
    public List<Income> showAllExpenses(Long idCustomer) throws IncomeExpenseException {
        return null;
    }

    @Override
    public Income addExpense(Long idCustomer, Income income) throws IncomeExpenseException {
        return null;
    }

    @Override
    public List<Income> showAllExpensesByDateRange(Long idCustomer, LocalDate initDate, LocalDate finalDate) throws IncomeExpenseException {
        return null;
    }

    @Override
    public List<IncomeExpenseComparison> getFinancialPerspective(Long idCustomer, LocalDate initDate, LocalDate finalDate) throws IncomeExpenseException {
        return null;
    }
}
