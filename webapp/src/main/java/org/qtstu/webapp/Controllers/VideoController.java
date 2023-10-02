package org.qtstu.webapp.Controllers;

import org.qtstu.webapp.Models.User;
import org.qtstu.webapp.Models.Video;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user/{userId}/video")
public class VideoController {
    //TODO
    private final ArrayList<Video> Videos = new ArrayList<Video>();

    //TODO GET
    @GetMapping
    public ArrayList<Video> get() {
        return Videos;
    }


    //TODO POST
    // @PostMapping
    public ArrayList<Video> post() {
        return Videos;
    }

    //TODO PUT
    @PutMapping
    public ArrayList<Video> put() {
        return Videos;
    }

    //TODO DELETE
    @DeleteMapping
    public ArrayList<Video> delete() {
        return Videos;
    }

    //TODO PATCH
    // @PatchMapping
    public ArrayList<Video> patch() {
        return Videos;
    }
}
