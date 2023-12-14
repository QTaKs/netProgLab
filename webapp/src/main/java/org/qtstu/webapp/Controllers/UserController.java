package org.qtstu.webapp.Controllers;

import org.qtstu.webapp.Models.DBHolder;
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
    private DBHolder db;
    @Autowired
    UserController(DBHolder db){
        this.db = db;
    }

    @GetMapping("/{userId}")
    public List<UserRecord> get(@PathVariable Long userId) {
        return db.getDb().getUserRecord(userId);
    }
    @GetMapping
    public List<UserRecord> gets() {
        return db.getDb().getUserRecords();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void post(@RequestBody List<UserRecord> users) {
        db.getDb().addUsers(users);
    }
    @PutMapping
    public void put(@RequestBody List<UserRecord> users) {
        db.getDb().updateUsers(users);
    }
    @DeleteMapping("/{userId}")
    public void delete(@PathVariable Long userId) {
        db.getDb().deleteUser(userId);
    }
}
