package com.clearspend.expense;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleData implements CommandLineRunner {

    private final InMemoryExpenseRepository repository;
    private final ExpenseRules rules;

    public SampleData(InMemoryExpenseRepository repository, ExpenseRules rules) {
        this.repository = repository;
        this.rules = rules;
    }

    @Override
    public void run(String... args) {
        List<Expense> expenses = List.of(
                expense(1L, "2026-10-14", ExpenseCategory.OTHER, "45.00", "Taxi cliente"),
                expense(2L, "2026-10-13", ExpenseCategory.REPRESENTATION, "500.00", "Comida cliente A"),
                expense(3L, "2026-10-13", ExpenseCategory.REPRESENTATION, "500.01", "Comida cliente B"),
                expense(4L, "2026-10-10", ExpenseCategory.TRAINING, "1200.00", "Curso negociación"),
                expense(5L, "2026-10-09", ExpenseCategory.OTHER, "1000.00", "Material evento"),
                expense(6L, "2026-10-09", ExpenseCategory.OTHER, "1000.01", "Material evento grande"),
                expense(7L, "2026-10-14", ExpenseCategory.IT_EQUIPMENT, "750.00", "Monitor"),
                expense(8L, "2026-10-14", ExpenseCategory.IT_EQUIPMENT, "750.01", "Portátil accesorio"),
                expense(9L, "2026-10-01", ExpenseCategory.OTHER, "120.00", "Mensajería"),
                expense(10L, "2026-10-12", ExpenseCategory.OTHER, "2500.00", "Evento corporativo"),
                expense(11L, "2026-10-02", ExpenseCategory.REPRESENTATION, "2600.00", "Cena feria"),
                expense(12L, "2026-10-11", ExpenseCategory.OTHER, "300.00", "Material oficina")
        );

        expenses.forEach(this::applyApproval);
        repository.replaceAll(expenses);
    }

    private Expense expense(Long id, String date, ExpenseCategory category, String amount, String description) {
        return new Expense(id, LocalDate.parse(date), category, new BigDecimal(amount), description,
                ApprovalStatus.NOT_REQUIRED, ApprovalLevel.NONE);
    }

    private void applyApproval(Expense expense) {
        ApprovalLevel level = rules.approvalLevelFor(expense);
        expense.setApprovalLevel(level);
        expense.setApprovalStatus(level == ApprovalLevel.NONE
                ? ApprovalStatus.NOT_REQUIRED
                : ApprovalStatus.PENDING);
    }
}
