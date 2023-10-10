package org.qtstu.webapp.Controllers;


import jdk.jshell.spi.ExecutionControl;
import org.qtstu.webapp.Models.User;
import org.qtstu.webapp.Models.Video;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {

    //TODO GET
    @GetMapping
    public ArrayList<User> get() {
        return null;
    }


    //TODO POST
    // @PostMapping
    public ArrayList<User> post() {
        return null;
    }

    //TODO PUT
    @PutMapping
    public ArrayList<User> put() {
        return null;
    }

    //TODO DELETE
    @DeleteMapping
    public ArrayList<User> delete() {
        return null;
    }

    //TODO PATCH
    // @PatchMapping
    public ArrayList<User> patch() {
        return null;
    }


}
