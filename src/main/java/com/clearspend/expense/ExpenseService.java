package com.clearspend.expense;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

    private final ExpenseRepository repository;
    private final ExpenseRules rules;

    public ExpenseService(ExpenseRepository repository, ExpenseRules rules) {
        this.repository = repository;
        this.rules = rules;
    }

    public List<Expense> findAll() {
        return repository.findAll();
    }

    public Expense findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Gasto no encontrado: " + id));
    }

    public Expense save(Expense expense) {
        applyApproval(expense);
        return repository.save(expense);
    }

    private void applyApproval(Expense expense) {
        ApprovalLevel level = rules.approvalLevelFor(expense);
        expense.setApprovalLevel(level);
        expense.setApprovalStatus(level == ApprovalLevel.NONE
                ? ApprovalStatus.NOT_REQUIRED
                : ApprovalStatus.PENDING);
    }
}
