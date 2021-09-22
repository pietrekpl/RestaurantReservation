package example.projects.restaurant_reservation.service;

import example.projects.restaurant_reservation.domain.RestaurantReservation;
import example.projects.restaurant_reservation.domain.User;
import example.projects.restaurant_reservation.model.RestaurantReservationDTO;
import example.projects.restaurant_reservation.repos.RestaurantReservationRepository;
import example.projects.restaurant_reservation.repos.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
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

    public List<RestaurantReservationDTO> findAll() {
        return restaurantReservationRepository.findAll()
                .stream()
                .map(restaurantReservation -> mapToDTO(restaurantReservation, new RestaurantReservationDTO()))
                .collect(Collectors.toList());
    }

    public RestaurantReservationDTO get(final Long id) {
        return restaurantReservationRepository.findById(id)
                .map(restaurantReservation -> mapToDTO(restaurantReservation, new RestaurantReservationDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final RestaurantReservationDTO restaurantReservationDTO) {
        final RestaurantReservation restaurantReservation = new RestaurantReservation();
        mapToEntity(restaurantReservationDTO, restaurantReservation);
        return restaurantReservationRepository.save(restaurantReservation).getId();
    }

    public void update(final Long id, final RestaurantReservationDTO restaurantReservationDTO) {
        final RestaurantReservation restaurantReservation = restaurantReservationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(restaurantReservationDTO, restaurantReservation);
        restaurantReservationRepository.save(restaurantReservation);
    }

    public void delete(final Long id) {
        restaurantReservationRepository.deleteById(id);
    }

    private RestaurantReservationDTO mapToDTO(final RestaurantReservation restaurantReservation,
            final RestaurantReservationDTO restaurantReservationDTO) {
        restaurantReservationDTO.setId(restaurantReservation.getId());
        restaurantReservationDTO.setRestaurantName(restaurantReservation.getRestaurantName());
        restaurantReservationDTO.setDateOfReservation(restaurantReservation.getDateOfReservation());
        restaurantReservationDTO.setReservationTimeFrom(restaurantReservation.getReservationTimeFrom());
        restaurantReservationDTO.setReservationTimeTo(restaurantReservation.getReservationTimeTo());
        restaurantReservationDTO.setUserReservation(restaurantReservation.getUserReservation() == null ? null : restaurantReservation.getUserReservation().getId());
        return restaurantReservationDTO;
    }

    private RestaurantReservation mapToEntity(
            final RestaurantReservationDTO restaurantReservationDTO,
            final RestaurantReservation restaurantReservation) {
        restaurantReservation.setRestaurantName(restaurantReservationDTO.getRestaurantName());
        restaurantReservation.setDateOfReservation(restaurantReservationDTO.getDateOfReservation());
        restaurantReservation.setReservationTimeFrom(restaurantReservationDTO.getReservationTimeFrom());
        restaurantReservation.setReservationTimeTo(restaurantReservationDTO.getReservationTimeTo());
        if (restaurantReservationDTO.getUserReservation() != null && (restaurantReservation.getUserReservation() == null || !restaurantReservation.getUserReservation().getId().equals(restaurantReservationDTO.getUserReservation()))) {
            final User userReservation = userRepository.findById(restaurantReservationDTO.getUserReservation())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "userReservation not found"));
            restaurantReservation.setUserReservation(userReservation);
        }
        return restaurantReservation;
    }

}
