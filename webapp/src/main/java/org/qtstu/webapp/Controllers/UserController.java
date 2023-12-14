package org.qtstu.webapp.Controllers;

import org.qtstu.webapp.Models.DB;
import org.qtstu.webapp.Models.UserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//POST - CREATE
//GET - READ
//PUT - UPDATE/REPLACE
//DELETE - CREATE
//PATCH - UPDATE/MODIFY

@RestController
@RequestMapping("/api/user")
public class UserController {
    private DB db;
    @Autowired
    UserController(DB db){
        this.db = db;
    }

    @GetMapping("/{userId}")
    public List<UserRecord> get(@PathVariable Long userId) {
        return db.getUserRecord(userId);
    }
    @GetMapping
    public List<UserRecord> gets() {
        return db.getUserRecords();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void post(@RequestBody List<UserRecord> users) {
        db.addUsers(users);
    }
    @PutMapping
    public void put(@RequestBody List<UserRecord> users) {
        db.updateUsers(users);
    }
    @DeleteMapping("/{userId}")
    public void delete(@PathVariable Long userId) {db.deleteUser(userId);
    }
}
