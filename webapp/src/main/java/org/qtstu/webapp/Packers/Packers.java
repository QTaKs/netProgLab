package org.qtstu.webapp.Packers;

import org.qtstu.webapp.Models.*;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Packers {
    static public class Packer{
        public static <Type> Type toObj(HashMap<String,String> record, Class<Type> type){
            if(type.equals(UserRecord.class)) {
                return (Type)new UserRecord(
                        Long.parseLong(record.get("id")),
                        Long.parseLong(record.get("registrationDate")),
                        record.get("username"),
                        (ArrayList<Long>) Arrays.stream(record.get("userVideos")
                                        .split(","))
                                .map(s -> Long.parseLong(s.trim()))
                                .collect(Collectors.toList()));
            }else if(type.equals(VideoRecord.class)) {
                return (Type)new VideoRecord(
                        Long.parseLong(record.get("id")),
                        Long.parseLong(record.get("userUploader")),
                        record.get("name"),
                        Long.parseLong(record.get("duration")),
                        Long.parseLong(record.get("uploadDate"))
                );
            }
            return null;
        }
//        public static <Type> HashMap<String,String> toHashMap(Type obj, Class<Type> type){
//            HashMap<String,String> hashMap = new HashMap<String, String>();
//            if(type.equals(UserRecord.class)) {
//                UserRecord user = (UserRecord) obj;
//                hashMap.put("id",user.id().toString());
//                hashMap.put("username",user.username());
//                hashMap.put("username",user.);
//            }else if(type.equals(VideoRecord.class)) {
//
//            }
//            return null;
//        }

//            public static User recordToUser(UserRecord record){
//                User usr = new User(record.id(), LocalDateTime.ofInstant(Instant.ofEpochSecond(record.registrationDate()),
//                        ZoneOffset.UTC),record.username());
//                usr.userVideos.addAll(DB.DBG.getUserVideos(record.id()));
//                return usr;
//            }
//            public static UserRecord userToRecord(User user){
//                ArrayList<Long> vids = new ArrayList<Long>();
//                for (Video vid:user.userVideos) {
//                    vids.add(vid.id);
//                }
//                return new UserRecord(user.id,user.registrationDate.toEpochSecond(ZoneOffset.UTC),user.username,vids);
//            }
//            public static Video recordToVideo(VideoRecord record){
//                return new Video(record.id(), DB.DBG.getUser(record.userUploader()), record.name(), Duration.ofSeconds(record.duration()), LocalDateTime.ofInstant(Instant.ofEpochSecond(record.userUploader()),ZoneOffset.UTC));
//            }
//            public static VideoRecord videoToRecord(Video video){
//                return new VideoRecord(video.id,video.userUploader.id, video.name, video.duration.getSeconds(),video.uploadDate.toEpochSecond(ZoneOffset.UTC));
//            }
    }
}
