package com.banana.bananamint.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "budget")
@Schema(name = "budget", description = "Model budget")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    @Schema(name = "Identificador budget", example = "1", required = false)
    private Long id;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Min(1)
    private double amount;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer user;

    @Min(1)
    private Long selected;

    @Min(1)
    private Long balance;


}
