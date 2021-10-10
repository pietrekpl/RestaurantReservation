package example.projects.restaurant_reservation;

import example.projects.restaurant_reservation.model.RestaurantReservation;
import example.projects.restaurant_reservation.model.User;
import example.projects.restaurant_reservation.service.RestaurantReservationService;
import example.projects.restaurant_reservation.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Set;


@Controller
public class HomeController {

    private final UserService userService;
    private final RestaurantReservationService reservationService;


    public HomeController(UserService userService, RestaurantReservationService reservationService) {
        this.userService = userService;
        this.reservationService = reservationService;
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
    @PostMapping("/reservations-submit")
    public String reservationsSubmit(@ModelAttribute RestaurantReservation restaurantReservation,
                                     @SessionAttribute("user")User user){
        assert user != null;
        restaurantReservation.setUserReservation(user);
        reservationService.create(restaurantReservation);
        Set<RestaurantReservation> userReservations = user.getReservations();
        userReservations.add(restaurantReservation);
        user.setReservations(userReservations);
        userService.update(user.getId(), user);
        return "redirect:/reservations";

    }

}
