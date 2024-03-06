package com.banana.bananamint.persistence;

import com.banana.bananamint.domain.Account;
import com.banana.bananamint.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.SQLException;
import java.util.List;

public interface CategoryJPARepository extends JpaRepository<Category,Long> {
    //public List<Category> findAll() throws SQLException;

    //public Category save(Category category) throws SQLException;
}
