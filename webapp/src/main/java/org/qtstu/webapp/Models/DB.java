package org.qtstu.webapp.Models;
import org.qtstu.webapp.Builder.UserBuilder;
import org.qtstu.webapp.Builder.VideoBuilder;
import org.qtstu.webapp.Packer.Packers;

import java.util.ArrayList;
import java.util.Objects;

import static org.qtstu.webapp.GlobalOptions.randomInteger;

public class DB {
    private final static ArrayList<User> usersDB = new ArrayList<User>();
    private final static ArrayList<Video> videosDB = new ArrayList<Video>();
    private DB() {
        usersDB.addAll(UserBuilder.create(randomInteger(2, 3)));
        for (User q : usersDB) {
            q.userVideos.addAll(VideoBuilder.create(randomInteger(1, 3), q));
            videosDB.addAll(q.userVideos);
        }
    }

    public static class DBG {
        public static User getUser(Long id) {
            for (User user : usersDB) {
                if (user.id.equals(id)) {
                    return user;
                }
            }
            return null;
        }
        public static Video getVideo(Long id) {
            for (Video video : videosDB) {
                if (video.id.equals(id)) {
                    return video;
                }
            }
            return null;
        }
        public static ArrayList<Video> getUserVideos(Long id) {
            ArrayList<Video> export = new ArrayList<Video>();
            for (Video video : videosDB) {
                if (video.userUploader.id.equals(id)) {
                    export.add(video);
                }
            }
            return export;
        }


        private static final DB db = new DB();
        public static ArrayList<UserRecord> getUserRecords() {
            ArrayList<UserRecord> export = new ArrayList<UserRecord>();
            for (User u:usersDB) {
                export.add(Packers.Packer.userToRecord(u));
            }
            return export;
        }
        public static ArrayList<UserRecord> getUserRecord(Long id) {
            ArrayList<UserRecord> u = new ArrayList<>();
            for (int i=0;i<=usersDB.size();i++){
                if(usersDB.get(i).id.equals(id)){
                    u.add(Packers.Packer.userToRecord(usersDB.get(i)));
                    return u;
                }
            }
            return u;
        }
        public static Boolean addUsers(ArrayList<UserRecord> users) {
            for (UserRecord rec:users) {
                for (User usr:usersDB) {
                    if(rec.id().equals(usr.id)){
                        return Boolean.FALSE;
                    }
                }
            }
            for (UserRecord rec:users) {
                usersDB.add(Packers.Packer.recordToUser(rec));
            }
            return Boolean.TRUE;
        }
        public static Boolean updateUsers(ArrayList<UserRecord> users) {
            boolean newEntity;
            for (UserRecord rec:users) {
                newEntity = Boolean.TRUE;
                for (User usr:usersDB) {
                    if(rec.id().equals(usr.id)){
                        newEntity = Boolean.FALSE;
                        break;
                    }
                }
                if(newEntity){
                    return Boolean.FALSE;
                }
            }
            for (UserRecord user : users) {
                for (int ii = 0; ii < usersDB.size(); ii++) {
                    if (user.id().equals(usersDB.get(ii).id)) {
                        usersDB.set(ii, Packers.Packer.recordToUser(user));
                    }
                }
            }
            return Boolean.TRUE;
        }

        public static Boolean deleteUser(Long id,Boolean Cascade) {
//            for (int i=0;i<=usersDB.size();i++){
//                User curUser = usersDB.get(i);
//                if(curUser.id.equals(id)){
//                    if(Cascade){
//                        for (Video vdv: curUser.userVideos) {
//                            videosDB.remove(vdv);
//                        }
//                    }
//                    usersDB.remove(i);
//                    return Boolean.TRUE;
//                }
//            }

            usersDB.removeIf(user -> user.id.equals(id));
            if(Cascade != null && Cascade) {
                videosDB.removeIf(video -> video.userUploader.id.equals(id));
            }
            return Boolean.FALSE;
        }

        public static ArrayList<VideoRecord> getVideoRecords(Long userId){
            ArrayList<VideoRecord> export = new ArrayList<>();
            for (User usr:usersDB) {
                if(Objects.equals(usr.id, userId)){
                    for (Video vdv:usr.userVideos) {
                        export.add(Packers.Packer.videoToRecord(vdv));
                    }
                }
            }
            return export;
        }
        public static ArrayList<VideoRecord> getVideoRecord(Long userId,Long videoId){
            ArrayList<VideoRecord> export = new ArrayList<>();
            for (User usr:usersDB) {
                if(Objects.equals(usr.id, userId)){
                    for (Video vdv:usr.userVideos) {
                        if(Objects.equals(vdv.id, videoId)){
                            export.add(Packers.Packer.videoToRecord(vdv));
                        }
                    }
                }
            }
            return export;
        }
        public static Boolean addVideos(ArrayList<VideoRecord> videos, Long userId){
            for(VideoRecord record:videos){
                if(!record.userUploader().equals(userId)){
                    return Boolean.FALSE;
                }
                for (Video vdv:videosDB) {
                    if(record.id().equals(vdv.id)){
                        return Boolean.FALSE;
                    }
                }
                Boolean exists = Boolean.FALSE;
                for (User usr:usersDB) {
                    if (usr.id.equals(record.userUploader())){
                        exists = Boolean.TRUE;
                        break;
                    }
                }
                if(!exists){
                    return Boolean.FALSE;
                }
            }
            for(VideoRecord record:videos){
                Video toInsert = Packers.Packer.recordToVideo(record);
                videosDB.add(toInsert);
                for (User usr:usersDB) {
                    if (usr.id.equals(record.userUploader())){
                        usr.userVideos.add(toInsert);
                    }
                }
            }
            return Boolean.TRUE;
        }
        public static Boolean updateVideos(ArrayList<VideoRecord> videos, Long userId){
            for(VideoRecord record:videos){
                if(!record.userUploader().equals(userId)){
                    return Boolean.FALSE;
                }
                Boolean exists = Boolean.FALSE;
                for (Video vdv:videosDB) {
                    if(record.id().equals(vdv.id)){
                        exists = Boolean.TRUE;
                        break;
                    }
                }
                if(!exists){
                    return Boolean.FALSE;
                }
                exists = Boolean.FALSE;
                for (User usr:usersDB) {
                    if (usr.id.equals(record.userUploader())){
                        exists = Boolean.TRUE;
                        break;
                    }
                }
                if(!exists){
                    return Boolean.FALSE;
                }
            }
            for (VideoRecord record : videos) {
                for (int ii = 0; ii < videosDB.size(); ii++) {
                    if (record.id().equals(videosDB.get(ii).id)) {
                        videosDB.set(ii, Packers.Packer.recordToVideo(record));
                    }
                }
            }
            return Boolean.TRUE;
        }
        public static Boolean deleteVideo(Long userId, Long videoId){
            if(usersDB.size() < videosDB.size()){
                for (int i = 0;i<usersDB.size();i++) {
                    User current = usersDB.get(i);
                    if(current.id.equals(userId)){
                        for (int ii = 0; ii < current.userVideos.size(); ii++) {
                            if(current.userVideos.get(ii).id.equals(videoId)){
                                videosDB.remove(current.userVideos.get(ii));
                                current.userVideos.remove(ii);
                                return Boolean.FALSE;
                            }
                        }
                    }
                }
            }else{
               for (int i = 0;i<videosDB.size();i++) {
                    if(videosDB.get(i).id.equals(videoId)){
                        videosDB.get(i).userUploader.userVideos.remove(videosDB.get(i));
                        videosDB.remove(i);
                        return Boolean.TRUE;
                    }
               }
            }

            return Boolean.FALSE;
        }
    }
}