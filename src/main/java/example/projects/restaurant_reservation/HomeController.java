package example.projects.restaurant_reservation;

import example.projects.restaurant_reservation.model.User;
import example.projects.restaurant_reservation.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HomeController {

    private UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/reservations")
        public String reservations(Model model){
        User user = userService.get(10000L);
        model.addAttribute("user", user);
        return "reservations";

    }

}
