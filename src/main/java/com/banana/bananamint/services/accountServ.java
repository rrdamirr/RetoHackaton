package com.banana.bananamint.services;

import com.banana.bananamint.domain.Account;
import com.banana.bananamint.domain.Customer;
import com.banana.bananamint.exception.AccountException;
import com.banana.bananamint.exception.CustomerException;
import com.banana.bananamint.persistence.AccountJPARepository;
import com.banana.bananamint.persistence.CustomerJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class accountServ implements AccountService {

    @Autowired
    private AccountJPARepository repo;

    @Autowired
    private CustomerJPARepository repoCustomer;

    @Override
    public List<Account> showAll(Long idCustomer) throws AccountException {
        return null;
    }

    @Override
    @Transactional
    public Account open(Long idCustomer, Account account) throws AccountException {

        Customer customer = repoCustomer.findById(idCustomer).orElseThrow(() -> new CustomerException("Cliente no encontrado"));

        account.setOwner(customer);

        return repo.save(account);
    }
}
