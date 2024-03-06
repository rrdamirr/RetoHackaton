package com.banana.bananamint.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jdk.jfr.BooleanFlag;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "account")
@ToString
@Schema(name = "account", description = "Model account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    @Schema(name = "Identificador account", example = "1", required = false)
    private Long id;

    @NotBlank
    @NotNull
    @Size(min = 2, max = 100)
    private String type;

    @DateTimeFormat
    @NotNull
    LocalDate openingDate;

    private double balance;

    private double maxOverdraft;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer owner;

    @NotNull
    @BooleanFlag
    private boolean active;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "moneyFrom")
    @ToString.Exclude
    @JsonIgnore
    @Schema(name = "Account expense list", example = "model expense", required = false)
    private List<Expense> expenses;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "moneyTo")
    @ToString.Exclude
    @JsonIgnore
    @Schema(name = "Account income list", example = "model expense", required = false)
    private List<Income> incomes;

    private boolean validarFecha() {
        return this.openingDate != null && this.openingDate.compareTo(LocalDate.now()) <= 0;
    }

}
