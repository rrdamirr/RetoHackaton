package com.banana.bananamint.persistence;

import com.banana.bananamint.domain.Account;
import com.banana.bananamint.domain.Customer;
import com.banana.bananamint.domain.Goal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest()
@ComponentScan(basePackages = {"com.banana.bananamint.persistence"})
@AutoConfigureTestEntityManager
class AccountJPARepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(AccountJPARepositoryTest.class);

    @Autowired
    private AccountJPARepository repo;

    @Test
   void save_ok() {

        Account account = new Account(null, "Ahorro",LocalDate.now(),1500.00,1000.00,null,true, null, null);

        repo.save(account);

        System.out.println(account);

        Optional<Account> op = repo.findById(account.getId());
        Account sAccount = op.get();
        assertEquals(sAccount.getId(), account.getId());
        assertThat(sAccount.getId()).isGreaterThan(0);
    }

   @Test
   void save_nok() {

        Account account = new Account(null, "A",LocalDate.now(),1500.00,1000.00,null,true, null, null);

        assertThrows(Exception.class, ()->{
            repo.save(account);
        });
    }
}