package org.qtstu.webapp.Builder;

import org.qtstu.webapp.Models.User;
import org.qtstu.webapp.Models.Video;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.TimeZone;

import static org.qtstu.webapp.GlobalOptions.*;

public class VideoBuilder {

    public static Video create(User user) {
        Video video = new Video(lastVideoId,user,randomName("Video_"),randomDuration(),ZonedDateTime.now());
        lastVideoId++;
        return video;
    }

    public static ArrayList<Video> create(int count,User user) {
        ArrayList<Video> array = new ArrayList<Video>();
        for (int i = 0; i < count; i++) {
            array.add(create(user));
        }
        return array;
    }
}
