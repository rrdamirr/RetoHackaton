package com.banana.bananamint.controller;

import com.banana.bananamint.domain.Account;
import com.banana.bananamint.domain.Income;
import com.banana.bananamint.domain.StatusMessage;
import com.banana.bananamint.services.AccountService;
import com.banana.bananamint.services.IncomeExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;


@RestController
@RequestMapping(value = "/customer")
@Validated
@Tag(name = "Customer API", description = "Customer management APIs")
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    IncomeExpenseService service;

    @Operation(summary = "Add a new income of a customer", description = "Returns a income of a customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "4XX", description = "Bad request")
    })
    @PostMapping(value = "/{cid}/account/{aid}/income", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addIncome(
            @Parameter(name = "cid", description = "customer id", example = "1", required = true)
            @PathVariable("cid") @Min(1) Long id,
            @Parameter(name = "aid", description = "account id", example = "1", required = true)
            @PathVariable("aid") @Min(1) Long aid,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Income data")
            @RequestBody @Valid Income newIncome
    ) {
        newIncome.setId(null);
        newIncome.setMoneyTo(new Account(aid));
        Income aIncome = service.addIncome(id, newIncome);
        if (aIncome != null && aIncome.getId() > 0) return new ResponseEntity<>(newIncome, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(new StatusMessage(HttpStatus.BAD_REQUEST.value(), "No encontrado"), HttpStatus.BAD_REQUEST);
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}