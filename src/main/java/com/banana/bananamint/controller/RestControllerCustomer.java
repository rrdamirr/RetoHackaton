package com.banana.bananamint.controller;

import com.banana.bananamint.domain.Goal;
import com.banana.bananamint.services.GoalServiceClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/customers")
@Validated
public class RestControllerCustomer {

@Autowired
    private GoalServiceClass service;

    @PostMapping("/{cid}/goals")
    public ResponseEntity<List<Goal>> addGoal(
            @PathVariable("cid") @Min(1) Long id, @RequestBody @Valid Goal goal
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.add(id, goal));
    }

}
