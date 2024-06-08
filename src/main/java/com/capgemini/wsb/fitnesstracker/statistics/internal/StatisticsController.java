package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsDto;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsService;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsSummaryDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing statistics.
 */
@RestController
@RequestMapping("/v1/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;
    private final StatisticsMapper statisticsMapper;

    /**
     * Constructs a new {@code StatisticsController} with the specified service and mapper.
     *
     * @param statisticsService the service for managing statistics
     * @param statisticsMapper  the mapper for converting statistics entities to DTOs
     */
    public StatisticsController(StatisticsService statisticsService, StatisticsMapper statisticsMapper) {
        this.statisticsService = statisticsService;
        this.statisticsMapper = statisticsMapper;
    }

    /**
     * Retrieves all statistics summaries.
     *
     * @return a list of all statistics summaries
     */
    @GetMapping
    public List<StatisticsSummaryDto> getAllStatisticsSummary() {
        return statisticsService.findAllStatistics()
                .stream()
                .map(statisticsMapper::toSummaryDto)
                .toList();
    }

    /**
     * Creates new statistics.
     *
     * @param statisticsDto the DTO containing the data for the new statistics
     * @return the created statistics summary
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StatisticsSummaryDto createStatistics(@RequestBody StatisticsDto statisticsDto) {
        Statistics statistics = statisticsMapper.toEntity(statisticsDto);
        Statistics createdStatistics = statisticsService.createStatistics(statistics);
        return statisticsMapper.toSummaryDto(createdStatistics);
    }
}