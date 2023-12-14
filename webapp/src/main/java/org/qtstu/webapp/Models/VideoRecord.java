package org.qtstu.webapp.Models;
import jakarta.persistence.*;

import java.sql.Date;
@Entity
@Table(name="video", schema="main")
public class VideoRecord {
    @Id
    @GeneratedValue
    @Column(name="id", unique=true,nullable=false)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "uploader", referencedColumnName = "id")
    private UserRecord uploader;
    @Column(name="name",nullable=false)
    private String name;
    @Column(name="duration",nullable=false)
    private Integer duration;
    @Column(name="uploadDate",nullable=false)
    private Date uploadDate;
    public VideoRecord(Integer id, UserRecord uploader, String name, Integer duration, Date uploadDate) {
        this.setId(id);
        this.setUploader(uploader);
        this.setName(name);
        this.setDuration(duration);
        this.setUploadDate(uploadDate);
    }

    public VideoRecord() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserRecord getUploader() {
        return uploader;
    }

    public void setUploader(UserRecord uploader) {
        this.uploader = uploader;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
}