package org.qtstu.webapp.Controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.qtstu.webapp.Models.DBHolder;
import org.qtstu.webapp.Models.VideoRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/user/{userId}/video")
public class VideoController {
    private DBHolder db;
    @Autowired
    VideoController(DBHolder db){
        this.db = db;
    }

    @GetMapping("/{videoId}")
    public List<VideoRecord> get(@PathVariable Long userId, @PathVariable Long videoId) {return db.getDb().getVideoRecord(userId,videoId);}
    @GetMapping
    public List<VideoRecord> gets(@PathVariable Long userId) {
        return db.getDb().getVideoRecords(userId);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void post(@RequestBody List<VideoRecord> videos, @PathVariable Long userId) {
        db.getDb().addVideos(videos,userId);}
    @PutMapping
    public void put(@RequestBody List<VideoRecord> videos, @PathVariable Long userId) {
        db.getDb().updateVideos(videos,userId);}
    @DeleteMapping("/{videoId}")
    public void delete(@PathVariable Long userId,@PathVariable Long videoId) {
        db.getDb().deleteVideo(userId,videoId);}

    @PostMapping("/deleteTrash")
    public void deleteTrash(@PathVariable String userId, HttpServletResponse response) throws IOException {
        db.getDb().deleteVideoTrash();
        response.sendRedirect("http://localhost:8080/videos");
    }
}
