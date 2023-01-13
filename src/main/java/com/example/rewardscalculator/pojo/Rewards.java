package com.example.rewardscalculator.pojo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rewards {
    private long customerId;
    private long lastFirstMonthRewards;
    private long lastSecondMonthRewards;
    private long lastThirdMonthRewards;
    private long totalRewards;
}
