package org.qtstu.webapp.Packer;

import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import org.qtstu.webapp.Models.User;
import org.qtstu.webapp.Models.UserRecord;
import org.qtstu.webapp.Models.Video;
import org.qtstu.webapp.Models.VideoRecord;
import java.time.*;
import java.util.ArrayList;

import org.qtstu.webapp.Models.DB.DBG;


public class Packers {
    public static class Packer {
        public static User recordToUser(UserRecord record){
            User usr = new User(record.id(),LocalDateTime.ofInstant(Instant.ofEpochSecond(record.registrationDate()),
                    ZoneOffset.UTC),record.username());
            usr.userVideos.addAll(DBG.getUserVideos(record.id()));
            return usr;
        }
        public static UserRecord userToRecord(User user){
            ArrayList<Long> vids = new ArrayList<Long>();
            for (Video vid:user.userVideos) {
                vids.add(vid.id);
            }
            return new UserRecord(user.id,user.registrationDate.toEpochSecond(ZoneOffset.UTC),user.username,vids);
        }
        public static Video recordToVideo(VideoRecord record){
            return new Video(record.id(), DBG.getUser(record.userUploader()), record.name(), Duration.ofSeconds(record.duration()), LocalDateTime.ofInstant(Instant.ofEpochSecond(record.userUploader()),ZoneOffset.UTC));
        }
        public static VideoRecord videoToRecord(Video video){
            return new VideoRecord(video.id,video.userUploader.id, video.name, video.duration.getSeconds(),video.uploadDate.toEpochSecond(ZoneOffset.UTC));
        }
    }
}
