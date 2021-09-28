package example.projects.restaurant_reservation;

import example.projects.restaurant_reservation.model.RestaurantReservation;
import example.projects.restaurant_reservation.model.User;
import example.projects.restaurant_reservation.service.RestaurantReservationService;
import example.projects.restaurant_reservation.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


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
        public String reservations(Model model, HttpSession session){
        User user = userService.get(10000L);
        session.setAttribute("user",user);
        RestaurantReservation restaurantReservation = new RestaurantReservation();
        model.addAttribute("reservation", restaurantReservation);
        return "reservations";

    }

}
