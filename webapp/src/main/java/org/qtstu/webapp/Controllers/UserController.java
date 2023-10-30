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
//DELETE - CREATE
//PATCH - UPDATE/MODIFY

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
    @DeleteMapping("/{userId}")
    public Boolean delete(@PathVariable Long userId) {
        return DBG.deleteUser(userId);
    }
}
