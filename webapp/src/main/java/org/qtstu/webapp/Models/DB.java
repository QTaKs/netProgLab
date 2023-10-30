package org.qtstu.webapp.Models;
import org.qtstu.webapp.Builder.UserBuilder;
import org.qtstu.webapp.Builder.VideoBuilder;
import org.qtstu.webapp.Packer.Packers;

import java.util.ArrayList;
import static org.qtstu.webapp.GlobalOptions.randomInteger;

public class DB {
    private final static ArrayList<User> usersDB = new ArrayList<User>();
    private final static ArrayList<Video> videosDB = new ArrayList<Video>();
    private DB() {
        usersDB.addAll(UserBuilder.create(randomInteger(5, 10)));
        for (User q : usersDB) {
            q.userVideos.addAll(VideoBuilder.create(randomInteger(0, 2), q));
            videosDB.addAll(q.userVideos);
        }
    }

    public static class DBG {
        public static final DB db = new DB();
        public static ArrayList<User> getUsers() {
            return usersDB;
        }
        public static ArrayList<User> getUser(Long id) {
            ArrayList<User> u = new ArrayList<>();
            for (int i=0;i<=usersDB.size();i++){
                if(usersDB.get(i).id == id){
                    u.add(usersDB.get(i));
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
            boolean notNew;
            for (UserRecord rec:users) {
                notNew = Boolean.FALSE;
                for (User usr:usersDB) {
                    if(rec.id().equals(usr.id)){
                        notNew = Boolean.TRUE;
                    }
                }
                if(notNew){
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
        public static Boolean deleteUser(Long id) {
            for (int i=0;i<=usersDB.size();i++){
                if(usersDB.get(i).id.equals(id)){
                    usersDB.remove(i);
                    return Boolean.TRUE;
                }
            }
            return Boolean.FALSE;
        }
    }
}