package org.qtstu.webapp.Models;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.TimeZone;

public class User {
    public final Long id;
    public String username;
    public final LocalDateTime registrationDate;
    public final ArrayList<Video> userVideos;
    public User(Long id, LocalDateTime registrationDate, String username) {
        this.id = id;
        this.registrationDate = registrationDate;
        this.username = username;
        userVideos = new ArrayList<Video>();
    }
}
