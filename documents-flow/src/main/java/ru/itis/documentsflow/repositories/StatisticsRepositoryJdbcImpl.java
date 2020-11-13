package ru.itis.documentsflow.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.documentsflow.models.Statistics;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class StatisticsRepositoryJdbcImpl implements StatisticsRepository {

    //language=SQL
    private final String SQL_INSERT_STATS = "insert into statistics" +
            "(username, date) values (?, ?)";
    //language=SQL
    private final String SQL_UPDATE_STATS = "update statistics \n" +
            "set username = ?, date = ? \n" +
            "where id = ?";
    //language=SQL
    private final String SQL_SELECT_ALL = "select * from statistics";
    //language=SQL
    private final String SQL_SELECT_STATS_BY_ID = "select * from statistics where id = ?";
    //language=SQL
    private final String SQL_DELETE_BY_ID = "delete from statistics where id = ?";

    public StatisticsRepositoryJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private JdbcTemplate jdbcTemplate;

    private RowMapper<Statistics> statisticsRowMapper = (row, rowNumber) ->
            Statistics.builder()
                    .id(row.getLong("id"))
                    .username(row.getString("username"))
                    .date(row.getString("date"))
                    .build();

    @Override
    public void save(Statistics model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(dataSource -> {
            PreparedStatement statement = dataSource.prepareStatement(SQL_INSERT_STATS);
            statement.setString(1, model.getUsername());
            statement.setString(2, model.getDate());
            return statement;
        }, keyHolder);
        model.setId((Long) keyHolder.getKey());
    }

    @Override
    public void update(Statistics model) {
        jdbcTemplate.update(dataSource -> {
            PreparedStatement statement = dataSource.prepareStatement(SQL_UPDATE_STATS);
            statement.setString(1, model.getUsername());
            statement.setString(2, model.getDate());
            statement.setLong(3, model.getId());
            return statement;
        });
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(dataSource -> {
            PreparedStatement statement = dataSource.prepareStatement(SQL_DELETE_BY_ID);
            statement.setLong(1, id);
            return statement;
        });
    }

    @Override
    public Optional<Statistics> find(Long id) {
        try {
            Statistics statistics = jdbcTemplate.queryForObject(SQL_SELECT_STATS_BY_ID, new Object[]{id}, statisticsRowMapper);
            return Optional.ofNullable(statistics);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Statistics> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, statisticsRowMapper);
    }
}
