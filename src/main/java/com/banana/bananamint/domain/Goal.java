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
    @Schema(name = "Identificator goal", example = "1", required = false)
    private Long id;

    @Size(min = 2, max = 60)
    @NotNull
    @NotBlank
    @Schema(name = "name type goal", example = "Home", required = true)
    private String name;

    @Size(min = 2, max = 100)
    @NotNull
    @NotBlank
    @Schema(name = "Description goal", example = "Gimnasio", required = true)
    private String description;

    @Min(1)
    @Schema(name = "Target amount", example = "400.00", required = true)
    private double targetAmount;

    @NotNull
    @NotBlank
    @Schema(name = "Status goal", example = "Finalizada", required = true)
    private String status;

    @DateTimeFormat
    @NotNull
    @Schema(name = "Target date", example = "2024-05-31", required = true)
    private LocalDate targetDate;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer user;

    public boolean validarGoal() throws GoalException {
        if (this.targetDate != null && this.targetDate.compareTo(LocalDate.now()) >= 0)
            return true;
        else throw new GoalException("Target Date not valid");
    }
}
