package org.qtstu.webapp.Builder;
import org.qtstu.webapp.GlobalOptions;
import org.qtstu.webapp.Models.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TimeZone;

import static org.qtstu.webapp.GlobalOptions.*;

public class UserBuilder{
    //Random generation
    public static User create(){
        User user = new User(lastUserId, LocalDateTime.now(),randomName("User_"));
        lastUserId++;
        return user;
    };
    public static ArrayList<User> create(int count){
        ArrayList<User> array = new ArrayList<User>();
        for (int i = 0; i < count; i++) {
            array.add(create());
        }
        return array;
    };
}

