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
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
@Schema(name = "account", description = "Model account")
@XmlRootElement
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    @Schema(name = "Identificador account", example = "1", required = false)
    private Long id;

    @NotBlank(message = "Debe tener valor")
    @NotNull
    @Size(min = 2, max = 100)
    @Schema(name = "type account", example = "cuenta ahorro", required = true)
    private String type;

    @DateTimeFormat
    @NotNull
    @Schema(name = "opening date account", example = "2024-02-14", required = true)
    LocalDate openingDate;

    @Schema(name = "balance", example = "100.00", required = true)
    private double balance;

    @Schema(name = "max over draft", example = "100.00", required = true)
    private double maxOverdraft;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @Schema(name = "customer", example = "1", required = false)
    private Customer owner;

    @NotNull
    @BooleanFlag
    @Schema(name = "active", example = "true", required = true)
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
