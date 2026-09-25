package com.clearspend.expense;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryExpenseRepository implements ExpenseRepository {

    private final Map<Long, Expense> expenses = new LinkedHashMap<>();
    private final AtomicLong sequence = new AtomicLong();

    @Override
    public List<Expense> findAll() {
        return expenses.values().stream()
                .sorted(Comparator.comparing(Expense::getId))
                .toList();
    }

    @Override
    public Optional<Expense> findById(Long id) {
        return Optional.ofNullable(expenses.get(id));
    }

    @Override
    public Expense save(Expense expense) {
        if (expense.getId() == null) {
            expense.setId(sequence.incrementAndGet());
        } else {
            sequence.updateAndGet(current -> Math.max(current, expense.getId()));
        }
        expenses.put(expense.getId(), expense);
        return expense;
    }

    public void replaceAll(List<Expense> seedData) {
        expenses.clear();
        sequence.set(0);
        new ArrayList<>(seedData).forEach(this::save);
    }
}
