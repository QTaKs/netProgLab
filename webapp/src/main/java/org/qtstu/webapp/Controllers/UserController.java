package org.qtstu.webapp.Controllers;


import org.qtstu.webapp.Models.User;
import org.qtstu.webapp.Models.Video;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {

    //TODO
    private final ArrayList<User> Users = new ArrayList<User>();


    //TODO GET
    @GetMapping
    public ArrayList<User> get() {
        return Users;
    }


    //TODO POST
    // @PostMapping
    public ArrayList<User> post() {
        return Users;
    }

    //TODO PUT
    @PutMapping
    public ArrayList<User> put() {
        return Users;
    }

    //TODO DELETE
    @DeleteMapping
    public ArrayList<User> delete() {
        return Users;
    }

    //TODO PATCH
    // @PatchMapping
    public ArrayList<User> patch() {
        return Users;
    }


}
