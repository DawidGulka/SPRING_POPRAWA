package com.capgemini.wsb.fitnesstracker.statistics.api;

import java.util.List;

/**
 * Service interface for managing statistics.
 */
public interface StatisticsService {

    /**
     * Retrieves all statistics.
     *
     * @return a list of all statistics
     */
    List<Statistics> findAllStatistics();

    /**
     * Creates new statistics.
     *
     * @param statistics the statistics to create
     * @return the created statistics
     */
    Statistics createStatistics(Statistics statistics);
}