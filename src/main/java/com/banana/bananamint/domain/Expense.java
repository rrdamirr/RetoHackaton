package com.banana.bananamint.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "expense")
@Schema(name = "expense", description = "Model expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    @Schema(name = "Identificador expense", example = "1", required = false)
    private Integer Id;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer user;

    @Min(1)
    private double amount;

    @DateTimeFormat
    @NotNull
    private LocalDate dueDate;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account moneyFrom;

    private String status;

    private boolean validarFecha() {
        return this.dueDate != null && this.dueDate.compareTo(LocalDate.now()) <= 0;
    }
}
