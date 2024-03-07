package com.banana.bananamint.controller;

import com.banana.bananamint.domain.Account;
import com.banana.bananamint.domain.StatusMessage;
import com.banana.bananamint.services.AccountService;
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
@Tag(name = "customer API", description = "customer management APIs")
public class CustomerController {
     private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    AccountService service;


    @Operation(summary = "Add a new account of a customer", description = "Returns a account proyect")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "4XX", description = "Bad request")
    })
    @PostMapping(value = "/{cid}/account", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity abrirCuenta(
            @Parameter(name = "id", description = "customer id", example = "1", required = true)
            @PathVariable("cid") @Min(1) Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Account data")
            @RequestBody @Valid Account newAccount
    ) {
        newAccount.setId(null);
        Account nuevo = service.open(id, newAccount);
        if (nuevo != null && nuevo.getId() > 0) return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(new StatusMessage(HttpStatus.BAD_REQUEST.value(), "No encontrado"), HttpStatus.BAD_REQUEST);

    }
}
