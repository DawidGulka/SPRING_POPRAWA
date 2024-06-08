package com.capgemini.wsb.fitnesstracker.statistics.api;

/**
 * Data Transfer Object (DTO) representing summary information about statistics.
 *
 * @param id                  the ID of the statistics
 * @param userId              the ID of the user associated with the statistics
 * @param totalTrainings      the total number of trainings
 * @param totalDistance       the total distance covered
 * @param totalCaloriesBurned the total number of calories burned
 */
public record StatisticsSummaryDto(Long id, Long userId, int totalTrainings, double totalDistance, int totalCaloriesBurned) {
}