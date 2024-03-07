package com.banana.bananamint.services;

import com.banana.bananamint.domain.Account;
import com.banana.bananamint.domain.Customer;
import com.banana.bananamint.domain.Income;
import com.banana.bananamint.exception.CustomerException;
import com.banana.bananamint.exception.IncomeExpenseException;
import com.banana.bananamint.payload.IncomeExpenseComparison;
import com.banana.bananamint.persistence.AccountJPARepository;
import com.banana.bananamint.persistence.CustomerJPARepository;
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

    @Autowired
    private CustomerJPARepository customerRepo;

    @Autowired
    private AccountJPARepository accountRepo;


    @Override
    public List<Income> showAllIncomes(Long idCustomer) throws IncomeExpenseException {
        return null;
    }

    @Override
    @Transactional
    public Income addIncome(Long idCustomer, Income income) throws IncomeExpenseException {
        Customer aCustomer = customerRepo.findById(idCustomer).orElseThrow(() -> new CustomerException("Cliente no encontrado"));
        //Account aAccount = accountRepo.findById(aCustomer.getAccounts()).orElseThrow(() -> new CustomerException("Cuenta no encontrada"));
        //aAccount = aCustomer.getAccounts()
        //income.setMoneyTo(aAccount);
        income.setUser(aCustomer);
        System.out.println("Income add service: "+ income);
        incomeRepo.save(income);
        return income;
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
