package org.qtstu.webapp.Models;
import java.sql.Date;

public record UserRecord(Integer id, Date registrationDate, String username) {

//    public static UserRecord create(){
//        UserRecord user = new UserRecord(0,LocalDateTime.now(ZoneOffset.UTC),options.randomName("User_"));
//        return user;
//    };
//    public static ArrayList<UserRecord> create(int count){
//        ArrayList<UserRecord> array = new ArrayList<User>();
//        for (int i = 0; i < count; i++) {
//            array.add(create());
//        }
//        return array;
//    };
}
