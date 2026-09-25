package com.clearspend.expense;

public enum ApprovalStatus {
    NOT_REQUIRED("No requerida"),
    PENDING("Pendiente");

    private final String label;

    ApprovalStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
