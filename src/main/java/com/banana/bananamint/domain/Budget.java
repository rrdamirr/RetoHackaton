package com.banana.bananamint.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "budget")
@Schema(name = "budget", description = "Model budget")
@XmlRootElement
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    @Schema(name = "Identificador budget", example = "1", required = false)
    private Long id;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @Schema(name = "category", example = "reforma", required = true)
    private Category category;

    @Min(1)
    @Schema(name = "amount", example = "1000.00", required = true)
    private double amount;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @Schema(name = "user", example = "1", required = false)
    private Customer user;

    @Min(1)
    @Schema(name = "amount", example = "1000.00", required = true)
    private Long selected;

    @Min(1)
    @Schema(name = "balance", example = "1000.00", required = true)
    private Long balance;


}
