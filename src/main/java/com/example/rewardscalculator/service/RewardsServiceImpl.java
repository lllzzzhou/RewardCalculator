package com.example.rewardscalculator.service;

import com.example.rewardscalculator.entity.TransactionEntity;
import com.example.rewardscalculator.pojo.Rewards;
import com.example.rewardscalculator.repository.CustomerRepository;
import com.example.rewardscalculator.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.rewardscalculator.utils.Constants.*;

@Service
public class RewardsServiceImpl implements RewardsService {

    private TransactionRepository transactionRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public RewardsServiceImpl(TransactionRepository transactionRepository, CustomerRepository customerRepository) {
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Rewards getRewardsByCustomerId(Long customerId) {
        Timestamp lastFirstMonthTimestamp = getDateGivenOffsetDays(DAYS_IN_MONTH);
        Timestamp lastSecondMonthTimestamp = getDateGivenOffsetDays(2*DAYS_IN_MONTH);
        Timestamp lastThirdMonthTimestamp = getDateGivenOffsetDays(3*DAYS_IN_MONTH);

        customerRepository.findById(customerId).orElseThrow();

        List<TransactionEntity> lastFirstMonthTransactionEntities = transactionRepository.
                findAllByCustomerIdAndTransactionDateBetween(customerId, lastFirstMonthTimestamp, new Timestamp(System.currentTimeMillis()));
        List<TransactionEntity> lastSecondMonthTransactionEntities = transactionRepository.
                findAllByCustomerIdAndTransactionDateBetween(customerId, lastSecondMonthTimestamp, lastFirstMonthTimestamp);
        List<TransactionEntity> lastThirdMonthTransactionEntities = transactionRepository.
                findAllByCustomerIdAndTransactionDateBetween(customerId, lastThirdMonthTimestamp, lastSecondMonthTimestamp);

        Long lastFirstMonthRewardsPoints = getRewardsPerMonth(lastFirstMonthTransactionEntities);
        Long lastSecondMonthRewardsPoints = getRewardsPerMonth(lastSecondMonthTransactionEntities);
        Long lastThirdMonthRewardsPoints = getRewardsPerMonth(lastThirdMonthTransactionEntities);
        Long totalRewardsPoints = lastFirstMonthRewardsPoints + lastSecondMonthRewardsPoints + lastThirdMonthRewardsPoints;

        Rewards rewards = Rewards.builder().customerId(customerId).
                lastFirstMonthRewards(lastFirstMonthRewardsPoints).
                lastSecondMonthRewards(lastSecondMonthRewardsPoints).
                lastThirdMonthRewards(lastThirdMonthRewardsPoints).
                totalRewards(totalRewardsPoints).build();

        return rewards;
    }

    private Long getRewardsPerMonth(List<TransactionEntity> transactionEntities) {
        return transactionEntities.stream().map(txn -> computeRewardsOfTransaction(txn)).collect(Collectors.summingLong(r -> r.longValue()));
    }

    public Long computeRewardsOfTransaction(TransactionEntity transactionEntity) {
        double transactionAmount = transactionEntity.getTransactionAmount();
        if (transactionAmount > FIRST_REWARD_LEVEL && transactionAmount < SECOND_REWARD_LEVEL)
            return Math.round(transactionAmount - FIRST_REWARD_LEVEL) * FIRST_REWARD_LEVEL_PT;
        else if (transactionAmount > SECOND_REWARD_LEVEL)
            return Math.round(transactionAmount - SECOND_REWARD_LEVEL) * SECOND_REWARD_LEVEL_PT
                    + (SECOND_REWARD_LEVEL - FIRST_REWARD_LEVEL) * FIRST_REWARD_LEVEL_PT;
        else
            return 0l;
    }

    public Timestamp getDateGivenOffsetDays(int days) {
        return Timestamp.valueOf(LocalDateTime.now().minusDays(days));
    }

}
