package org.qtstu.webapp.Controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.qtstu.webapp.Models.DB;
import org.qtstu.webapp.Models.VideoRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/user/{userId}/video")
public class VideoController {
    private DB db;
    @Autowired
    VideoController(DB db){
        this.db = db;
    }

    @GetMapping("/{videoId}")
    public List<VideoRecord> get(@PathVariable Long userId, @PathVariable Long videoId) {return db.getVideoRecord(userId,videoId);}
    @GetMapping
    public List<VideoRecord> gets(@PathVariable Long userId) {
        return db.getVideoRecords(userId);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void post(@RequestBody List<VideoRecord> videos, @PathVariable Long userId) {db.addVideos(videos,userId);}
    @PutMapping
    public void put(@RequestBody List<VideoRecord> videos, @PathVariable Long userId) {db.updateVideos(videos,userId);}
    @DeleteMapping("/{videoId}")
    public void delete(@PathVariable Long userId,@PathVariable Long videoId) {db.deleteVideo(userId,videoId);}

    @PostMapping("/deleteTrash")
    public void deleteTrash(@PathVariable String userId, HttpServletResponse response) throws IOException {
        db.deleteVideoTrash();
        response.sendRedirect("http://localhost:8080/videos");
    }
}
