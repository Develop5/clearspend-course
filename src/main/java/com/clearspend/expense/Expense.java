package com.clearspend.expense;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Expense {

    private Long id;
    private LocalDate date;
    private ExpenseCategory category;
    private BigDecimal amount;
    private String description;
    private ApprovalStatus approvalStatus;
    private ApprovalLevel approvalLevel;

    public Expense() {
    }

    public Expense(Long id, LocalDate date, ExpenseCategory category, BigDecimal amount, String description,
                   ApprovalStatus approvalStatus, ApprovalLevel approvalLevel) {
        this.id = id;
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.description = description;
        this.approvalStatus = approvalStatus;
        this.approvalLevel = approvalLevel;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public ExpenseCategory getCategory() { return category; }
    public void setCategory(ExpenseCategory category) { this.category = category; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public ApprovalStatus getApprovalStatus() { return approvalStatus; }
    public void setApprovalStatus(ApprovalStatus approvalStatus) { this.approvalStatus = approvalStatus; }
    public ApprovalLevel getApprovalLevel() { return approvalLevel; }
    public void setApprovalLevel(ApprovalLevel approvalLevel) { this.approvalLevel = approvalLevel; }
}
