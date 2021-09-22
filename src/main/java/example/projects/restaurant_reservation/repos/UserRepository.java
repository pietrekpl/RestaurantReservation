package example.projects.restaurant_reservation.repos;

import example.projects.restaurant_reservation.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
