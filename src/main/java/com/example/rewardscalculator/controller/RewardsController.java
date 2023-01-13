package com.example.rewardscalculator.controller;

import com.example.rewardscalculator.pojo.ErrorMessage;
import com.example.rewardscalculator.pojo.Rewards;
import com.example.rewardscalculator.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/customers")
public class RewardsController {

    private RewardsService rewardsService;

    @Autowired
    public RewardsController(RewardsService rewardsService) {
        this.rewardsService = rewardsService;
    }

    @GetMapping(("/{customerId}"))
    public ResponseEntity<Rewards> getRewardsByCustomerId(@PathVariable Long customerId) {
        Rewards rewards = rewardsService.getRewardsByCustomerId(customerId);
        return new ResponseEntity<>(rewards, HttpStatus.OK);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ErrorMessage handleNoSuchElt(NoSuchElementException e) {
        return new ErrorMessage("Customer not found");
    }
}
