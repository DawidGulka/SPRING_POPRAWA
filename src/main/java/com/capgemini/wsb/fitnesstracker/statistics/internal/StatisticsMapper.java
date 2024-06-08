package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsDto;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsSummaryDto;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between statistics entities and DTOs.
 */
@Component
public class StatisticsMapper {

    private final UserProvider userProvider;

    /**
     * Constructs a new {@code StatisticsMapper} with the specified user provider.
     *
     * @param userProvider the provider for accessing user data
     */
    public StatisticsMapper(UserProvider userProvider) {
        this.userProvider = userProvider;
    }

    /**
     * Converts a {@link Statistics} entity to a {@link StatisticsSummaryDto}.
     *
     * @param statistics the statistics entity to convert
     * @return the converted statistics summary DTO
     */
    public StatisticsSummaryDto toSummaryDto(Statistics statistics) {
        return new StatisticsSummaryDto(
                statistics.getId(),
                statistics.getUser().getId(),
                statistics.getTotalTrainings(),
                statistics.getTotalDistance(),
                statistics.getTotalCaloriesBurned()
        );
    }

    /**
     * Converts a {@link StatisticsDto} to a {@link Statistics} entity.
     *
     * @param statisticsDto the statistics DTO to convert
     * @return the converted statistics entity
     */
    public Statistics toEntity(StatisticsDto statisticsDto) {
        User user = userProvider.getUser(statisticsDto.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + statisticsDto.userId()));

        Statistics statistics = Statistics.create(user);
        statistics.setTotalTrainings(statisticsDto.totalTrainings());
        statistics.setTotalDistance(statisticsDto.totalDistance());
        statistics.setTotalCaloriesBurned(statisticsDto.totalCaloriesBurned());
        return statistics;
    }
}