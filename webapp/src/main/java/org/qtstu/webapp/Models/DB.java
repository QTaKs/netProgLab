package org.qtstu.webapp.Models;
import org.qtstu.webapp.Exceptions.NotFoundException;
import org.qtstu.webapp.Hack.ApplicationContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.DataClassRowMapper;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DB {
    private static final String READ = """
                        SELECT * FROM MEMBERSHIP WHERE trade = ?
            """;
    private static final String READALL = """
                        SELECT * FROM MEMBERSHIP
            """;
    private static final String CREATE = """
                        INSERT INTO MEMBERSHIP (car, winter, hall)
                        VALUES (:car,:winter,:hall)
            """;
    private static final String UPDATE = """
                        UPDATE MEMBERSHIP
                           SET car = :car,
                               winter = :winter,
                               hall = :hall
                         WHERE trade = :trade;
            """;
    private static final String DELETE = """
                        DELETE FROM MEMBERSHIP
                         WHERE trade = :trade;
            """;

    private RowMapper<Membership> rowMapperUser = new DataClassRowMapper<Membership>(Membership.class);
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private ApplicationContextHolder holder;
    @Autowired
    public DB(JdbcTemplate template,NamedParameterJdbcTemplate ndParamJdbcTemplate) {
        jdbcTemplate = template;
        namedParameterJdbcTemplate = ndParamJdbcTemplate;
    }
    public DB(){}

    private void checkBools(){
        if(jdbcTemplate == null){
            jdbcTemplate = ApplicationContextHolder.getContext().getBean(JdbcTemplate.class);
        }
        if(namedParameterJdbcTemplate == null){
            namedParameterJdbcTemplate = ApplicationContextHolder.getContext().getBean(NamedParameterJdbcTemplate.class);
        }
        if(rowMapperUser == null){
            rowMapperUser = new DataClassRowMapper<Membership>(Membership.class);
        }
    }

    public List<Membership> readAll() {
        checkBools();
        try {
            return jdbcTemplate.query(READALL,rowMapperUser);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("No users have found", e);
        }
    }
    public List<Membership> read(Long trade) {
        checkBools();
        List<Membership> export = new ArrayList<>();
        try {
            export.add(jdbcTemplate.queryForObject(READ, rowMapperUser, trade));
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("User with id = [" + trade + "] not found", e);
        }
        return export;
    }
    public void create(List<Membership> memberships) {
        checkBools();
        memberships.forEach((membership -> {
            BeanPropertySqlParameterSource paramsSource = new BeanPropertySqlParameterSource(membership);
            namedParameterJdbcTemplate.update(CREATE, paramsSource);
        }));
    }
    public void update(List<Membership> memberships) {
        checkBools();
        memberships.forEach((membership -> {
            BeanPropertySqlParameterSource paramsSource = new BeanPropertySqlParameterSource(membership);
            namedParameterJdbcTemplate.update(UPDATE, paramsSource);
        }));
    }

    public void delete(Long trade) {
        checkBools();
        try {
            jdbcTemplate.update(DELETE,trade);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("User with id = [" + trade + "] not found", e);
        }
    }
}
