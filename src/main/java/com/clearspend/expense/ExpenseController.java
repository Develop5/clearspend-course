package com.clearspend.expense;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService service;

    public ExpenseController(ExpenseService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("expenses", service.findAll());
        return "expenses";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        Expense expense = new Expense();
        expense.setDate(LocalDate.of(2026, 10, 15));
        model.addAttribute("expense", expense);
        model.addAttribute("categories", ExpenseCategory.values());
        return "expense-form";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("expense", service.findById(id));
        model.addAttribute("categories", ExpenseCategory.values());
        return "expense-form";
    }

    @PostMapping
    public String save(Expense expense) {
        service.save(expense);
        return "redirect:/expenses";
    }
}
