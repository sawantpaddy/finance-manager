package com.finance.controller;

import com.finance.dto.ExpenseDTO;
import com.finance.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/expenses")
@Tag(name = "Expenses", description = "Expense Management APIs")
@SecurityRequirement(name = "Bearer Authentication")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;
    
    @GetMapping
    @Operation(summary = "Get all expenses", description = "Get all expenses with pagination and sorting")
    public ResponseEntity<Page<ExpenseDTO>> getAllExpenses(
            Authentication authentication,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "expenseDate") String sortBy,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction) {
        
        Long userId = getUserIdFromToken(authentication);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<ExpenseDTO> expenses = expenseService.getAllExpenses(userId, pageable);
        return ResponseEntity.ok(expenses);
    }
    
    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Get expenses by category", description = "Get all expenses filtered by category")
    public ResponseEntity<Page<ExpenseDTO>> getExpensesByCategory(
            Authentication authentication,
            @PathVariable Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Long userId = getUserIdFromToken(authentication);
        Pageable pageable = PageRequest.of(page, size);
        Page<ExpenseDTO> expenses = expenseService.getExpensesByCategory(userId, categoryId, pageable);
        return ResponseEntity.ok(expenses);
    }
    
    @GetMapping("/date-range")
    @Operation(summary = "Get expenses by date range", description = "Get expenses within a specific date range")
    public ResponseEntity<Page<ExpenseDTO>> getExpensesByDateRange(
            Authentication authentication,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Long userId = getUserIdFromToken(authentication);
        Pageable pageable = PageRequest.of(page, size);
        Page<ExpenseDTO> expenses = expenseService.getExpensesByDateRange(userId, startDate, endDate, pageable);
        return ResponseEntity.ok(expenses);
    }
    
    @PostMapping
    @Operation(summary = "Create new expense", description = "Create a new expense record")
    public ResponseEntity<ExpenseDTO> createExpense(
            Authentication authentication,
            @Valid @RequestBody ExpenseDTO expenseDTO) {
        
        Long userId = getUserIdFromToken(authentication);
        ExpenseDTO createdExpense = expenseService.createExpense(userId, expenseDTO);
        return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update expense", description = "Update an existing expense record")
    public ResponseEntity<ExpenseDTO> updateExpense(
            Authentication authentication,
            @PathVariable Long id,
            @Valid @RequestBody ExpenseDTO expenseDTO) {
        
        Long userId = getUserIdFromToken(authentication);
        ExpenseDTO updatedExpense = expenseService.updateExpense(userId, id, expenseDTO);
        return ResponseEntity.ok(updatedExpense);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete expense", description = "Delete an expense record")
    public ResponseEntity<String> deleteExpense(
            Authentication authentication,
            @PathVariable Long id) {
        
        Long userId = getUserIdFromToken(authentication);
        expenseService.deleteExpense(userId, id);
        return ResponseEntity.ok("Expense deleted successfully");
    }
    
    private Long getUserIdFromToken(Authentication authentication) {
        return 1L; // In production, extract from JWT token or authentication principal
    }
}