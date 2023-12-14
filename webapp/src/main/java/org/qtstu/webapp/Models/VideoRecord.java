package org.qtstu.webapp.Models;
import java.sql.Date;

public record VideoRecord(Integer id, Integer uploader, String name, Integer duration, Date uploadDate) {


//    public static Video create(User user) {
//        Video video = new Video(lastVideoId,user,randomName("Video_"),randomDuration(), LocalDateTime.now());
//        lastVideoId++;
//        return video;
//    }
//
//    public static ArrayList<Video> create(int count, User user) {
//        ArrayList<Video> array = new ArrayList<Video>();
//        for (int i = 0; i < count; i++) {
//            array.add(create(user));
//        }
//        return array;
//    }

}
