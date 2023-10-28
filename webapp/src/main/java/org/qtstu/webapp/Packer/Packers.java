package org.qtstu.webapp.Packer;

import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import org.qtstu.webapp.Models.User;
import org.qtstu.webapp.Models.UserRecord;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.TimeZone;

public class Packers {
    public static class Packer {
        public static User recordToUser(UserRecord record){
            return new User(record.id(),LocalDateTime.ofInstant(Instant.ofEpochSecond(record.registrationDate()),
                    ZoneOffset.UTC),record.username());
        }
        public static UserRecord userToRecord(User user){
            return new UserRecord(user.id,user.registrationDate.getLong(ChronoField.INSTANT_SECONDS),user.username);
        }
    }
}
