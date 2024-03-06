package com.banana.bananamint.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Entity
@Table(name = "category")
@Schema(name = "category", description = "Model category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    @Schema(name = "Identificador category", example = "1", required = false)
    private Integer id;

    @Size(min = 2, max = 60)
    @NotNull
    @NotBlank
    private String name;

    @Size(min = 2, max = 60)
    @NotNull
    @NotBlank
    private String type;

    @Size(min = 5, max = 100)
    @NotNull
    @NotBlank
    private String description;

    @DateTimeFormat
    @NotNull
    private LocalDate createdAt;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "category")
    @ToString.Exclude
    @JsonIgnore
    @Schema(name = "Category budget list", example = "model budget", required = false)
    private List<Budget> budgetCats;

}
