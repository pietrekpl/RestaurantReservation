package example.projects.restaurant_reservation.service;

import example.projects.restaurant_reservation.model.RestaurantReservation;
import example.projects.restaurant_reservation.repos.RestaurantReservationRepository;
import example.projects.restaurant_reservation.repos.UserRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class RestaurantReservationService {

    private final RestaurantReservationRepository restaurantReservationRepository;
    private final UserRepository userRepository;

    public RestaurantReservationService(
            final RestaurantReservationRepository restaurantReservationRepository,
            final UserRepository userRepository) {
        this.restaurantReservationRepository = restaurantReservationRepository;
        this.userRepository = userRepository;
    }

    public List<RestaurantReservation> findAll() {
        return restaurantReservationRepository.findAll();
    }

    public RestaurantReservation get(final Long id) {
        return restaurantReservationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final RestaurantReservation restaurantReservation) {
        return restaurantReservationRepository.save(restaurantReservation).getId();
    }

    public void update(final Long id, final RestaurantReservation restaurantReservation) {
        final RestaurantReservation existRestaurantReservation = restaurantReservationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        restaurantReservationRepository.save(restaurantReservation);
    }

    public void delete(final Long id) {
        restaurantReservationRepository.deleteById(id);
    }
}
