package example.projects.restaurant_reservation.repos;

import example.projects.restaurant_reservation.model.RestaurantReservation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RestaurantReservationRepository extends JpaRepository<RestaurantReservation, Long> {
}
