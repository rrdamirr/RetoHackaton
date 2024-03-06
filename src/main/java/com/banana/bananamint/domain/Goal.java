package com.banana.bananamint.domain;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "goal")
@Schema(name = "goal", description = "Model goal")
@XmlRootElement
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    @Schema(name = "Identificador goal", example = "1", required = false)
    private Long id;

    @Size(min = 2, max = 60)
    @NotNull
    @NotBlank(message = "Debe tener valor")
    @Schema(name = "name goal", example = "ahorro", required = true)
    private String name;

    @Size(min = 2, max = 100)
    @NotNull
    @NotBlank(message = "Debe tener valor")
    @Schema(name = "description goal", example = "ahorro mes marzo", required = true)
    private String description;

    @Min(1)
    @Schema(name = "target amount goal", example = "100.00", required = true)
    private double targetAmount;

    @NotNull
    @NotBlank(message = "Debe tener valor")
    @Size(min = 2, max = 50)
    @Schema(name = "target amount goal", example = "100.00", required = true)
    private String status;

    @DateTimeFormat
    @NotNull
    @Schema(name = "target date goal", example = "2024-02-14", required = true)
    private LocalDate targetDate;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @Schema(name = "customer goal", example = "1", required = false)
    private Customer user;

    private boolean validarFecha() {
        return this.targetDate != null && this.targetDate.compareTo(LocalDate.now()) <= 0;
    }
}
