package com.clearspend.expense;

public enum ApprovalLevel {
    NONE("Ninguno"),
    MANAGER("Responsable directo");

    private final String label;

    ApprovalLevel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
