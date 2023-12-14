package org.qtstu.webapp.Controllers;
import org.qtstu.webapp.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class ViewController {
    private DBHolder db;
    @Autowired
    ViewController(DBHolder db){
        this.db = db;
    }

    @GetMapping("/users")
    public String viewUsers(Model model) {
        model.addAttribute("users", db.getDb().getUserRecords());
        return "UsersView";
    }
    @GetMapping("/videos")
    public String viewVideos(Model model) {
        model.addAttribute("videos", db.getDb().getVideoRecordsAll());
        return "VideosView";
    }
}
