package example.projects.restaurant_reservation;

import example.projects.restaurant_reservation.model.RestaurantReservation;
import example.projects.restaurant_reservation.model.RestaurantType;
import example.projects.restaurant_reservation.model.User;
import example.projects.restaurant_reservation.repos.RestaurantReservationRepository;
import example.projects.restaurant_reservation.repos.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;


@SpringBootApplication
public class RestaurantReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantReservationApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository, RestaurantReservationRepository restaurantReservationRepository) {

        return (args) -> {
            User user = new User();
            user.setFullName("Piotr");
            user.setUsername("PL");
            user.setPasswordHash(bCryptPasswordEncoder().encode("12345"));
            userRepository.save(user);
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = new Date();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            RestaurantReservation restaurantReservation = RestaurantReservation.builder()
                    .dateOfReservation(localDate)
                    .reservationTimeFrom(LocalTime.of(14, 00))
                    .reservationTimeTo(LocalTime.of(14, 30))
                    .userReservation(user)
                    .restaurantName("KFC")
                    .restaurantType(RestaurantType.FAST_FOOD)
                    .build();

            restaurantReservationRepository.save(restaurantReservation);

        };

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

