package org.qtstu.webapp.Models;

public class Video {

    public final int id;
    public final int userUploader;

    public Video(int id, int userUploader) {
        this.userUploader = userUploader;
        this.id =id;
    }
}
