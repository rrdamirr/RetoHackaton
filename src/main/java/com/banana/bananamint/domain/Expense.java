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
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "expense")
@Schema(name = "expense", description = "Model expense")
@XmlRootElement
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    @Schema(name = "Identificador expense", example = "1", required = false)
    private Integer Id;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @Schema(name = "customer", example = "1", required = false)
    private Customer user;

    @Min(1)
    @Schema(name = "amount", example = "1000.00", required = true)
    private double amount;

    @DateTimeFormat
    @NotNull
    @Schema(name = "due date expense", example = "2024-02-14", required = true)
    private LocalDate dueDate;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @Schema(name = "money from account", example = "1", required = true)
    private Account moneyFrom;


    @NotNull
    @NotBlank(message = "Debe tener valor")
    @Size(min = 2, max = 50)
    @Schema(name = "status", example = "pagado", required = true)
    private String status;

    private boolean validarFecha() {
        return this.dueDate != null && this.dueDate.compareTo(LocalDate.now()) <= 0;
    }
}
