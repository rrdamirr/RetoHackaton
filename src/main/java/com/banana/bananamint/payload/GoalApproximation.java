package com.banana.bananamint.payload;


import com.banana.bananamint.domain.Goal;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "goal approximation", description = "goal approximation class")
public class GoalApproximation {

    @Schema(name = "Object goal", example = "{1, Ahorro, Ahorro mes marzo, 100.00, Finalizada, 2024-03-31, 2 }", required = true)
    private Goal goal;

    @Schema(name = "Target amount difference", example = "100.00", required = true)
    private double targetAmountDifference;

    @Schema(name = "Tendency into dates", example = "-1", required = true)
    private int tendency; // 1, 0, -1, depending on targetAmountDifference (positive, near zero, negative)

    @Schema(name = "Estimated Reaching target date", example = "2024-04-31", required = true)
    private LocalDate estimatedReachingTargetDate;

}
