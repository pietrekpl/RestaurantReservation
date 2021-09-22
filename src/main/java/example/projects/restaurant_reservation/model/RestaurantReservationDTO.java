package example.projects.restaurant_reservation.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RestaurantReservationDTO {

    private Long id;

    @Size(max = 255)
    private String restaurantName;

    private LocalDate dateOfReservation;

    @Schema(type = "string", example = "14:30")
    private LocalTime reservationTimeFrom;

    @Schema(type = "string", example = "14:30")
    private LocalTime reservationTimeTo;

    @NotNull
    private Long userReservation;

}
