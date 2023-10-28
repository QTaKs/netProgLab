package org.qtstu.webapp.Models;

import java.time.*;

public class Video {
    public final Long id;
    public final User userUploader;
    public String name;
    public final Duration duration;
    public final LocalDateTime uploadDate;
    public Video(Long id, User userUploader,String name, Duration duration, LocalDateTime uploadDate) {
        this.userUploader = userUploader;
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.uploadDate = uploadDate;
    }
}
