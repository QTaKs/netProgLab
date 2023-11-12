package org.qtstu.webapp.Packers;

import org.qtstu.webapp.Models.UserRecord;
import org.qtstu.webapp.Models.VideoRecord;

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
    }
}
