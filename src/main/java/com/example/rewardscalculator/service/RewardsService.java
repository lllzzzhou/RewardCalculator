package com.example.rewardscalculator.service;

import com.example.rewardscalculator.pojo.Rewards;
import org.springframework.stereotype.Service;

public interface RewardsService {
    Rewards getRewardsByCustomerId(Long customerId);
}
