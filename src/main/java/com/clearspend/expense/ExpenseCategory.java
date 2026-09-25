package com.clearspend.expense;

public enum ExpenseCategory {
    TRAVEL("Viajes"),
    REPRESENTATION("Representación"),
    TRAINING("Formación"),
    IT_EQUIPMENT("Material informático"),
    OTHER("Otros");

    private final String label;

    ExpenseCategory(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
