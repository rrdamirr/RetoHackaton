package com.banana.bananamint.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "income")
@Schema(name = "income", description = "Model income")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    @Schema(name = "Identificador income", example = "1", required = false)
    private Integer Id;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer user;

    @Min(1)
    private double amount;

    @DateTimeFormat
    @NotNull
    private LocalDate enterDate;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account moneyTo;

    private String status;

    private boolean validarFecha() {
        return this.enterDate != null && this.enterDate.compareTo(LocalDate.now()) <= 0;
    }
}
