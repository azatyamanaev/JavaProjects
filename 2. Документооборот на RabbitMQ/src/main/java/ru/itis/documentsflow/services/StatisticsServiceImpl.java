package ru.itis.documentsflow.services;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.itis.documentsflow.models.Statistics;
import ru.itis.documentsflow.repositories.StatisticsRepository;
import ru.itis.documentsflow.repositories.StatisticsRepositoryJdbcImpl;

public class StatisticsServiceImpl implements StatisticsService {

    private StatisticsRepository statisticsRepository;

    public StatisticsServiceImpl(JdbcTemplate jdbcTemplate) {
        this.statisticsRepository = new StatisticsRepositoryJdbcImpl(jdbcTemplate);
    }

    @Override
    public void saveStat(Statistics statistics) {
        statisticsRepository.save(statistics);
    }
}
