package org.qtstu.webapp.Controllers;

import org.qtstu.webapp.Models.User;
import org.qtstu.webapp.Models.Video;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user/{userId}/video")
public class VideoController {


    //TODO GET
    @GetMapping
    public ArrayList<Video> get() {
        return null;
    }


    //TODO POST
    // @PostMapping
    public ArrayList<Video> post() {
        return null;
    }

    //TODO PUT
    @PutMapping
    public ArrayList<Video> put() {
        return null;
    }

    //TODO DELETE
    @DeleteMapping
    public ArrayList<Video> delete() {
        return null;
    }

    //TODO PATCH
    // @PatchMapping
    public ArrayList<Video> patch() {
        return null;
    }
}
