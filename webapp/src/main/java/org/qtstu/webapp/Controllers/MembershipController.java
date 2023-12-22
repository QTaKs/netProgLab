package org.qtstu.webapp.Controllers;

import org.qtstu.webapp.Models.DB;
import org.qtstu.webapp.Models.Membership;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/membership")
public class MembershipController {
    private DB db;
    @Autowired
    MembershipController(DB db){
        this.db = db;
    }

    @GetMapping("/{trade}")
    public List<Membership> read(@PathVariable Long trade) {
        return db.read(trade);
    }
    @GetMapping
    public List<Membership> readAll() {
        return db.readAll();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void post(@RequestBody List<Membership> memberships) {
        db.create(memberships);
    }
    @PutMapping
    public void put(@RequestBody List<Membership> memberships) {
        db.update(memberships);
    }
    @DeleteMapping("/{trade}")
    public void delete(@PathVariable Long trade) {db.delete(trade);
    }
}
