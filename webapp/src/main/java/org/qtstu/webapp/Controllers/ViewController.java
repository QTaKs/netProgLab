package org.qtstu.webapp.Controllers;
import org.qtstu.webapp.Models.DB;
import org.qtstu.webapp.Models.UserRecord;
import org.qtstu.webapp.Models.VideoRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
@RequestMapping("/")
public class ViewController {
    private DB db;
    @Autowired
    ViewController(DB db){
        this.db = db;
    }

    @GetMapping("/users")
    public String viewUsers(Model model) {
        model.addAttribute("users", db.getUserRecords());
        return "UsersView";
    }
    @GetMapping("/videos")
    public String viewVideos(Model model) {
        model.addAttribute("videos", db.getVideoRecordsAll());
        return "VideosView";
    }
}
