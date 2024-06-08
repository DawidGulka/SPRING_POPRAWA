package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsService;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the {@link StatisticsServiceImpl} class.
 */
@SpringBootTest
class StatisticsServiceImplTest {

    @Autowired
    private StatisticsService statisticsService;

    @MockBean
    private StatisticsRepository statisticsRepository;

    @Test
    void shouldFindAllStatistics() {
        // given
        User user1 = new User("John", "Doe", LocalDate.of(1990, 1, 1), "john.doe@example.com");
        user1.setId(1L);
        User user2 = new User("Jane", "Doe", LocalDate.of(1995, 1, 1), "jane.doe@example.com");
        user2.setId(2L);
        Statistics statistics1 = Statistics.create(user1);
        Statistics statistics2 = Statistics.create(user2);
        List<Statistics> expectedStatistics = List.of(statistics1, statistics2);

        when(statisticsRepository.findAll()).thenReturn(expectedStatistics);

        // when
        List<Statistics> actualStatistics = statisticsService.findAllStatistics();

        // then
        assertThat(actualStatistics).isEqualTo(expectedStatistics);
    }

    @Test
    void shouldCreateStatistics() {
        // given
        User user = new User("John", "Doe", LocalDate.of(1990, 1, 1), "john.doe@example.com");
        user.setId(1L);
        Statistics expectedStatistics = Statistics.create(user);

        when(statisticsRepository.save(any(Statistics.class))).thenReturn(expectedStatistics);

        // when
        Statistics actualStatistics = statisticsService.createStatistics(expectedStatistics);

        // then
        assertThat(actualStatistics).isEqualTo(expectedStatistics);
    }

}