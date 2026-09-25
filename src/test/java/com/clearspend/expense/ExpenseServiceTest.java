package com.clearspend.expense;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

class ExpenseServiceTest {

    @Test
    void creatingExpenseAppliesApprovalRules() {
        FakeRepository repository = new FakeRepository();
        ExpenseService service = new ExpenseService(repository, new ExpenseRules());

        Expense expense = new Expense(null, LocalDate.of(2026, 10, 15), ExpenseCategory.REPRESENTATION,
                new BigDecimal("1200.00"), "Cena cliente", ApprovalStatus.NOT_REQUIRED, ApprovalLevel.NONE);

        Expense saved = service.save(expense);

        assertThat(saved.getApprovalStatus()).isEqualTo(ApprovalStatus.PENDING);
        assertThat(saved.getApprovalLevel()).isEqualTo(ApprovalLevel.MANAGER);
    }

    private static class FakeRepository implements ExpenseRepository {
        private final List<Expense> expenses = new ArrayList<>();

        @Override
        public List<Expense> findAll() {
            return List.copyOf(expenses);
        }

        @Override
        public Optional<Expense> findById(Long id) {
            return expenses.stream().filter(expense -> id.equals(expense.getId())).findFirst();
        }

        @Override
        public Expense save(Expense expense) {
            if (expense.getId() == null) {
                expense.setId((long) expenses.size() + 1);
            }
            expenses.removeIf(existing -> existing.getId().equals(expense.getId()));
            expenses.add(expense);
            return expense;
        }
    }
}
