package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for accessing statistics data.
 */
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
}