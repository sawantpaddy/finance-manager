package com.finance.service;

import com.finance.dto.ExpenseDTO;
import com.finance.entity.Expense;
import com.finance.entity.User;
import com.finance.entity.Category;
import com.finance.repository.ExpenseRepository;
import com.finance.repository.UserRepository;
import com.finance.repository.CategoryRepository;
import com.finance.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    public Page<ExpenseDTO> getAllExpenses(Long userId, Pageable pageable) {
        Page<Expense> expenses = expenseRepository.findByUserId(userId, pageable);
        return expenses.map(this::convertToDTO);
    }
    
    public Page<ExpenseDTO> getExpensesByCategory(Long userId, Long categoryId, Pageable pageable) {
        Page<Expense> expenses = expenseRepository.findByUserIdAndCategoryId(userId, categoryId, pageable);
        return expenses.map(this::convertToDTO);
    }
    
    public Page<ExpenseDTO> getExpensesByDateRange(Long userId, LocalDate startDate, LocalDate endDate, Pageable pageable) {
        Page<Expense> expenses = expenseRepository.findByUserIdAndDateRange(userId, startDate, endDate, pageable);
        return expenses.map(this::convertToDTO);
    }
    
    public ExpenseDTO createExpense(Long userId, ExpenseDTO expenseDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        Category category = categoryRepository.findById(expenseDTO.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        
        Expense expense = new Expense();
        expense.setAmount(expenseDTO.getAmount());
        expense.setDescription(expenseDTO.getDescription());
        expense.setExpenseDate(expenseDTO.getExpenseDate());
        expense.setUser(user);
        expense.setCategory(category);
        
        Expense savedExpense = expenseRepository.save(expense);
        return convertToDTO(savedExpense);
    }
    
    public ExpenseDTO updateExpense(Long userId, Long expenseId, ExpenseDTO expenseDTO) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found"));
        
        if (!expense.getUser().getId().equals(userId)) {
            throw new ResourceNotFoundException("Expense not found for this user");
        }
        
        expense.setAmount(expenseDTO.getAmount());
        expense.setDescription(expenseDTO.getDescription());
        expense.setExpenseDate(expenseDTO.getExpenseDate());
        
        if (expenseDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(expenseDTO.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
            expense.setCategory(category);
        }
        
        Expense updatedExpense = expenseRepository.save(expense);
        return convertToDTO(updatedExpense);
    }
    
    public void deleteExpense(Long userId, Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found"));
        
        if (!expense.getUser().getId().equals(userId)) {
            throw new ResourceNotFoundException("Expense not found for this user");
        }
        
        expenseRepository.deleteById(expenseId);
    }
    
    private ExpenseDTO convertToDTO(Expense expense) {
        ExpenseDTO dto = new ExpenseDTO();
        dto.setId(expense.getId());
        dto.setAmount(expense.getAmount());
        dto.setDescription(expense.getDescription());
        dto.setExpenseDate(expense.getExpenseDate());
        dto.setCategoryId(expense.getCategory().getId());
        dto.setCategoryName(expense.getCategory().getName());
        dto.setCreatedAt(expense.getCreatedAt());
        return dto;
    }
}