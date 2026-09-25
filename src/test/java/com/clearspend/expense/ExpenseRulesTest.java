package com.clearspend.expense;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class ExpenseRulesTest {

    private final ExpenseRules rules = new ExpenseRules();

    @Test
    void ordinaryExpenseDoesNotRequireApproval() {
        assertThat(rules.approvalLevelFor(expense(ExpenseCategory.OTHER, "2500.00")))
                .isEqualTo(ApprovalLevel.NONE);
    }

    @Test
    void representationExpenseBelowThresholdDoesNotRequireApproval() {
        assertThat(rules.approvalLevelFor(expense(ExpenseCategory.REPRESENTATION, "999.99")))
                .isEqualTo(ApprovalLevel.NONE);
    }

    @Test
    void representationExpenseAtThresholdDoesNotRequireApproval() {
        assertThat(rules.approvalLevelFor(expense(ExpenseCategory.REPRESENTATION, "1000.00")))
                .isEqualTo(ApprovalLevel.NONE);
    }

    @Test
    void representationExpenseAboveThresholdRequiresManagerApproval() {
        assertThat(rules.approvalLevelFor(expense(ExpenseCategory.REPRESENTATION, "1000.01")))
                .isEqualTo(ApprovalLevel.MANAGER);
    }

    private Expense expense(ExpenseCategory category, String amount) {
        return new Expense(null, LocalDate.of(2026, 10, 15), category, new BigDecimal(amount), "Test",
                ApprovalStatus.NOT_REQUIRED, ApprovalLevel.NONE);
    }
}
