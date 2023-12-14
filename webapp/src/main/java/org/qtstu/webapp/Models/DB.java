package org.qtstu.webapp.Models;
import org.qtstu.webapp.Exceptions.NotFoundException;
import org.qtstu.webapp.GlobalOptions;
import org.qtstu.webapp.Hack.ApplicationContextHolder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.DataClassRowMapper;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DB {
    private static final String GET_USER = """
                        SELECT * FROM USER WHERE id = ?
            """;
    private static final String GET_USERS = """
                        SELECT * FROM USER
            """;
    private static final String CREATE_USER = """
                        INSERT INTO USER (username, registrationDate)
                        VALUES (:username,:registrationDate)
            """;
    private static final String UPDATE_USER = """
                        UPDATE USER
                           SET username = :username,
                               registrationDate = :registrationDate
                         WHERE id = :id;
            """;
    private static final String DELETE_USER = """
                        DELETE FROM USER
                        WHERE id = ?
            """;
    private static final String GET_VIDEO = """
                        SELECT * FROM VIDEO WHERE id = ? and uploader = ?
            """;
    private static final String GET_VIDEOS = """
                        SELECT * FROM VIDEO WHERE uploader = ?
            """;
    private static final String GET_VIDEOSALL = """
                        SELECT * FROM VIDEO
            """;
    private static final String CREATE_VIDEO = """
                        INSERT INTO VIDEO (uploader,name,duration,uploadDate)
                        VALUES (:uploader,:name,:duration,:uploadDate)
            """;
    private static final String UPDATE_VIDEO = """
                        UPDATE VIDEO
                           SET name = :name,
                               duration = :duration,
                               uploadDate = :uploadDate
                         WHERE id = :id and uploader = :uploader;
            """;
    private static final String DELETE_VIDEO = """
                        DELETE FROM VIDEO
                        WHERE uploader = ? and id = ?
            """;
    private static final String DELETE_VIDEOTRASH = """
                        DELETE FROM VIDEO
                        WHERE duration < 30*60
            """;

    private RowMapper<UserRecord> rowMapperUser = new DataClassRowMapper<UserRecord>(UserRecord.class);
    private RowMapper<VideoRecord> rowMapperVideo = new DataClassRowMapper<VideoRecord>(VideoRecord.class);
    private GlobalOptions options;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private ApplicationContextHolder holder;
    @Autowired
    public DB(GlobalOptions opts, JdbcTemplate template,NamedParameterJdbcTemplate ndParamJdbcTemplate) {
        options=opts;
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
            rowMapperUser = new DataClassRowMapper<UserRecord>(UserRecord.class);
        }
        if(rowMapperVideo == null){
            rowMapperVideo = new DataClassRowMapper<VideoRecord>(VideoRecord.class);
        }
    }

    public List<UserRecord> getUserRecords() {
        checkBools();
        try {
            return jdbcTemplate.query(GET_USERS,rowMapperUser);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("No users have found", e);
        }
    }
    public List<UserRecord> getUserRecord(Long id) {
        checkBools();
        List<UserRecord> export = new ArrayList<>();
        try {
            export.add(jdbcTemplate.queryForObject(GET_USER, rowMapperUser, id));
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("User with id = [" + id + "] not found", e);
        }
        return export;
    }
    public void addUsers(List<UserRecord> users) {
        checkBools();
        users.forEach((userRecord -> {
            BeanPropertySqlParameterSource paramsSource = new BeanPropertySqlParameterSource(userRecord);
            namedParameterJdbcTemplate.update(CREATE_USER, paramsSource);
        }));
    }
    public void updateUsers(List<UserRecord> users) {
        checkBools();
        users.forEach((userRecord -> {
            BeanPropertySqlParameterSource paramsSource = new BeanPropertySqlParameterSource(userRecord);
            namedParameterJdbcTemplate.update(UPDATE_USER, paramsSource);
        }));
    }

    public void deleteUser(Long id) {
        checkBools();
        try {
            jdbcTemplate.update(DELETE_USER,id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("User with id = [" + id + "] not found", e);
        }
    }

    public List<VideoRecord> getVideoRecords(Long userId){
        checkBools();
        try {
            return jdbcTemplate.query(GET_VIDEOS,rowMapperVideo,userId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("User with id = [" + userId + "] not found", e);
        }
    }
    public List<VideoRecord> getVideoRecordsAll(){
        checkBools();
        return jdbcTemplate.query(GET_VIDEOSALL,rowMapperVideo);
    }
    public List<VideoRecord> getVideoRecord(Long userId,Long videoId){
        checkBools();
        try {
            return jdbcTemplate.query(GET_VIDEO,rowMapperVideo,userId,videoId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Video with id = [" + videoId + "] for user with id = [" + userId + "] not found", e);
        }
    }
    public void addVideos(List<VideoRecord> videos, Long userId){
        checkBools();
        videos.forEach((videoRecord -> {
            BeanPropertySqlParameterSource paramsSource = new BeanPropertySqlParameterSource(videoRecord);
            namedParameterJdbcTemplate.query(CREATE_VIDEO,paramsSource,rowMapperVideo);
        }));
    }
    public void updateVideos(List<VideoRecord> videos, Long userId){
        checkBools();
        videos.forEach((videoRecord -> {
            BeanPropertySqlParameterSource paramsSource = new BeanPropertySqlParameterSource(videoRecord);
            namedParameterJdbcTemplate.update(UPDATE_VIDEO, paramsSource);
        }));
    }
    public void deleteVideo(Long userId, Long videoId) {
        checkBools();
        try {
            jdbcTemplate.update(DELETE_VIDEO,userId,videoId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Video with id = [" + videoId + "] for user with id = [" + userId + "]  not found", e);
        }
    }
    public void deleteVideoTrash() {
        checkBools();
        jdbcTemplate.update(DELETE_VIDEOTRASH);
    }
}