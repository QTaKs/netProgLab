package org.qtstu.webapp.Controllers;

import org.qtstu.webapp.Models.*;
import org.springframework.web.bind.annotation.*;
import jdk.jshell.spi.ExecutionControl;
import org.qtstu.webapp.Models.DB.DBG;
import org.qtstu.webapp.Models.User;
import org.qtstu.webapp.Models.Video;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import java.util.ArrayList;

@RestController
@RequestMapping("/user/{userId}/video")
public class VideoController {
    @GetMapping("/{videoId}")
    public ArrayList<VideoRecord> get(@PathVariable Long userId, @PathVariable Long videoId) {
        return DBG.getVideoRecord(userId,videoId);
    }
    @GetMapping
    public ArrayList<VideoRecord> gets(@PathVariable Long userId) {
        return DBG.getVideoRecords(userId);
    }
     @PostMapping
    public Boolean post(@RequestBody ArrayList<VideoRecord> videos, @PathVariable Long userId) {
        return DBG.addVideos(videos,userId);
    }
    @PutMapping
    public Boolean put(@RequestBody ArrayList<VideoRecord> videos, @PathVariable Long userId) {
        return DBG.updateVideos(videos,userId);
    }
    @DeleteMapping("/{videoId}")
    public Boolean delete(@PathVariable Long userId,@PathVariable Long videoId) {
        return DBG.deleteVideo(userId,videoId);
    }
}
