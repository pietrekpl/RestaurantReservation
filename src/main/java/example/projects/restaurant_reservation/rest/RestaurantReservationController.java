package example.projects.restaurant_reservation.rest;

import example.projects.restaurant_reservation.model.RestaurantReservationDTO;
import example.projects.restaurant_reservation.service.RestaurantReservationService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/restaurantReservations", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantReservationController {

    private final RestaurantReservationService restaurantReservationService;

    public RestaurantReservationController(
            final RestaurantReservationService restaurantReservationService) {
        this.restaurantReservationService = restaurantReservationService;
    }

    @GetMapping
    public ResponseEntity<List<RestaurantReservationDTO>> getAllRestaurantReservations() {
        return ResponseEntity.ok(restaurantReservationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantReservationDTO> getRestaurantReservation(
            @PathVariable final Long id) {
        return ResponseEntity.ok(restaurantReservationService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createRestaurantReservation(
            @RequestBody @Valid final RestaurantReservationDTO restaurantReservationDTO) {
        return new ResponseEntity<>(restaurantReservationService.create(restaurantReservationDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRestaurantReservation(@PathVariable final Long id,
            @RequestBody @Valid final RestaurantReservationDTO restaurantReservationDTO) {
        restaurantReservationService.update(id, restaurantReservationDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurantReservation(@PathVariable final Long id) {
        restaurantReservationService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
