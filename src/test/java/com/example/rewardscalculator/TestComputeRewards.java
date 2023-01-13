package com.example.rewardscalculator;

import com.example.rewardscalculator.entity.TransactionEntity;
import com.example.rewardscalculator.service.RewardsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TestComputeRewards {
    @Mock
    private TransactionEntity transactionEntity;

    @InjectMocks
    private RewardsServiceImpl rewardsService;

    @BeforeEach
    public void setupMockito() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testComputeRewardsOfTransaction() {
        when(transactionEntity.getTransactionAmount()).thenReturn(128.0);
        double res = rewardsService.computeRewardsOfTransaction(transactionEntity);
        assertEquals(106, res);
    }

}
