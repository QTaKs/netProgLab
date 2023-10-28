package org.qtstu.webapp.Controllers;


import jdk.jshell.spi.ExecutionControl;
import org.qtstu.webapp.Models.DB.DBG;
import org.qtstu.webapp.Models.User;
import org.qtstu.webapp.Models.UserRecord;
import org.qtstu.webapp.Models.Video;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;


import java.util.ArrayList;

//POST - CREATE
//GET - READ
//PUT - UPDATE/REPLACE
//PATCH - UPDATE/MODIFY
//DELETE - CREATE

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/")
    public ArrayList<User> get() {
        return DBG.getUsers();
    }
    @GetMapping("/{userId}")
    public ArrayList<User> gets(@PathVariable Long userId) {
        return DBG.getUser(userId);
    }
    @PostMapping
    public Boolean post(@RequestBody ArrayList<UserRecord> users) {
        return DBG.addUsers(users);
    }
    @PutMapping
    public Boolean put(@RequestBody ArrayList<UserRecord> users) {
        return DBG.updateUsers(users);
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
