package org.qtstu.webapp.Controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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

}