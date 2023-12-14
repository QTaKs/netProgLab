package org.qtstu.webapp.Models;

import org.qtstu.webapp.GlobalOptions;
import org.qtstu.webapp.Hack.ApplicationContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

public abstract class DBI {
    public abstract List<UserRecord> getUserRecords();

    public abstract List<UserRecord> getUserRecord(Long id);

    public abstract void addUsers(List<UserRecord> users);

    public abstract void updateUsers(List<UserRecord> users);

    public abstract void deleteUser(Long id);

    public abstract List<VideoRecord> getVideoRecords(Long userId);

    public abstract List<VideoRecord> getVideoRecordsAll();

    public abstract List<VideoRecord> getVideoRecord(Long userId, Long videoId);

    public abstract void addVideos(List<VideoRecord> videos, Long userId);

    public abstract void updateVideos(List<VideoRecord> videos, Long userId);

    public abstract void deleteVideo(Long userId, Long videoId);

    public abstract void deleteVideoTrash();
}
