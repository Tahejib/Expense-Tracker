package com.expensetracker.repository;

import com.expensetracker.model.Category;
import com.expensetracker.model.Expense;
import com.expensetracker.model.User;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUser(User user);


    List<Expense> findByUserAndDateBetween(User user, LocalDate start, LocalDate end);
    @Query("SELECT e FROM Expense e WHERE e.user.username = :username AND e.date BETWEEN :start AND :end")
    List<Expense> findByUserAndDateBetween(@Param("username") String username,
                                           @Param("start") LocalDate start,
                                           @Param("end") LocalDate end);

    List<Expense> findByCategory(Category category);
    List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);


	List<Expense> findByUserAndCategory(User user, Category category);


	List<Expense> findByUserUsernameAndTitleContainingIgnoreCase(String username, String keyword);	
    
}

