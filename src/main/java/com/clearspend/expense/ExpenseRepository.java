package com.clearspend.expense;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository {

    List<Expense> findAll();

    Optional<Expense> findById(Long id);

    Expense save(Expense expense);
}
