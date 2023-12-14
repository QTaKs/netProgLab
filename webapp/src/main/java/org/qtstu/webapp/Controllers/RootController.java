package org.qtstu.webapp.Controllers;
import org.qtstu.webapp.Hack.ApplicationContextHolder;
import org.qtstu.webapp.Models.DBHiber;
import org.qtstu.webapp.Models.DBHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RootController {

    @GetMapping("/")
    public String index() {
        return  """
                <p>Where is nothing to see. See:</p>
                <p>user/  -   list of all users</p>
                <p>user/{id}/videos    -   list videos of user</p>
                <p>user/{id}/videos/{id}    -   print info about of video</p>
                """;
    }

    @GetMapping("/api/switchDB")
    public void switchDB() {
        ApplicationContextHolder.getContext().getBean(DBHolder.class).switchDB();
    }

}