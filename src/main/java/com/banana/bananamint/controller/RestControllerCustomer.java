package com.banana.bananamint.controller;

import com.banana.bananamint.domain.Goal;
import com.banana.bananamint.payload.GoalApproximation;
import com.banana.bananamint.services.GoalServiceClass;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/customers")
@Validated
public class RestControllerCustomer {

    @Autowired
    private GoalServiceClass service;

    @Operation(summary = "Add goal for customer", description = "Returns a list from goals for customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "400", description = "Bad Request - The goals not valid"),
            @ApiResponse(responseCode = "404", description = "Not found - The customer was not found")
    })

    @PostMapping("/{cid}/goals")
    public ResponseEntity<List<Goal>> addGoal(
            @PathVariable("cid") @Min(1) Long id, @RequestBody @Valid Goal goal
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.add(id, goal));
    }

    @Operation(summary = "achievement of objectives", description = "Returns a list from achievement of objectives")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request - The goals not valid"),
            @ApiResponse(responseCode = "404", description = "Not found - The customer was not found")

    })

    @GetMapping("/{cid}/goals/{dateini}/{datefin}")
    public ResponseEntity<List<GoalApproximation>> generateReport(
            @PathVariable("cid") @Min(1) Long id,
            @PathVariable("dateini") @DateTimeFormat LocalDate dateini,
            @PathVariable("datefin") @DateTimeFormat LocalDate datefin

    ) throws SQLException {
        return ResponseEntity.status(HttpStatus.OK).body(service.generateReport(id, dateini, datefin));
    }

}
