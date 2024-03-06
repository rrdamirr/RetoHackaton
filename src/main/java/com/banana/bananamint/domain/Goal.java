package com.banana.bananamint.domain;


import com.banana.bananamint.exception.GoalException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "goal")
@Schema(name = "goal", description = "Model goal")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    @Schema(name = "Identificador goal", example = "1", required = false)
    private Long id;

    @Size(min = 2, max = 60)
    @NotNull
    @NotBlank
    private String name;

    @Size(min = 2, max = 100)
    @NotNull
    @NotBlank
    private String description;

    @Min(1)
    private double targetAmount;

    private String status;

    @DateTimeFormat
    @NotNull
    private LocalDate targetDate;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer user;

    private boolean validarFecha() throws GoalException {
        return this.targetDate != null && this.targetDate.compareTo(LocalDate.now()) <= 0;
    }
}
