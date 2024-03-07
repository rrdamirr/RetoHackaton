package com.banana.bananamint.services;

import com.banana.bananamint.domain.Customer;
import com.banana.bananamint.domain.Expense;
import com.banana.bananamint.domain.Income;
import com.banana.bananamint.exception.CustomerException;
import com.banana.bananamint.exception.IncomeExpenseException;
import com.banana.bananamint.payload.IncomeExpenseComparison;
import com.banana.bananamint.persistence.AccountJPARepository;
import com.banana.bananamint.persistence.CustomerJPARepository;
import com.banana.bananamint.persistence.ExpenseJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class IncomeExpenseServ implements IncomeExpenseService{

    @Autowired
    private ExpenseJPARepository repo;

    @Autowired
    private CustomerJPARepository repoCustomer;

    @Override
    public List<Income> showAllIncomes(Long idCustomer) throws IncomeExpenseException {
        return null;
    }

    @Override
    public Income addIncome(Long idCustomer, Income income) throws IncomeExpenseException {
        return null;
    }

    @Override
    public List<Income> showAllExpenses(Long idCustomer) throws IncomeExpenseException {
        return null;
    }

    @Override
    @Transactional
    public Expense addExpense(Long idCustomer, Expense expense) throws IncomeExpenseException {
        Customer customer = repoCustomer.findById(idCustomer).orElseThrow(() -> new CustomerException("Cliente no encontrado"));


        expense.setUser(customer);

        return repo.save(expense);
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
