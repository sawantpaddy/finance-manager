package com.finance.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDTO {
    private Long id;
    
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.1", message = "Amount should be greater than 0")
    private BigDecimal amount;
    
    @NotBlank(message = "Description is required")
    private String description;
    
    @NotNull(message = "Expense date is required")
    private LocalDate expenseDate;
    
    @NotNull(message = "Category ID is required")
    private Long categoryId;
    
    private String categoryName;
    private LocalDate createdAt;
}