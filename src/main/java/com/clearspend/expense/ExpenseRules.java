package com.clearspend.expense;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class ExpenseRules {

    private static final BigDecimal REPRESENTATION_APPROVAL_THRESHOLD = new BigDecimal("1000.00");

    public ApprovalLevel approvalLevelFor(Expense expense) {
        if (expense.getCategory() == ExpenseCategory.REPRESENTATION
                && expense.getAmount().compareTo(REPRESENTATION_APPROVAL_THRESHOLD) > 0) {
            return ApprovalLevel.MANAGER;
        }
        return ApprovalLevel.NONE;
    }
}
