package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsProvider;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatisticsServiceImpl implements StatisticsProvider, StatisticsService {

    private final StatisticsRepository statisticsRepository;

    public StatisticsServiceImpl(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    @Override
    public Optional<Statistics> getStatistics(Long statisticsId) {
        return statisticsRepository.findById(statisticsId);
    }

    @Override
    public List<Statistics> findAllStatistics() {
        return statisticsRepository.findAll();
    }

    @Override
    public Statistics createStatistics(Statistics statistics) {
        return statisticsRepository.save(statistics);
    }
}