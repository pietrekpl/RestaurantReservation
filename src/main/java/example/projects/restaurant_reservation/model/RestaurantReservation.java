package example.projects.restaurant_reservation.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import javax.persistence.*;

import lombok.*;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantReservation {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long id;

    @Column
    private String restaurantName;

    @Column
    private LocalDate dateOfReservation;

    @Column
    private LocalTime reservationTimeFrom;

    @Column
    private LocalTime reservationTimeTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_reservation_id", nullable = false)
    private User userReservation;

    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RestaurantType restaurantType;

    @PrePersist
    public void prePersist() {
        dateCreated = OffsetDateTime.now();
        lastUpdated = dateCreated;
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdated = OffsetDateTime.now();
    }

}
