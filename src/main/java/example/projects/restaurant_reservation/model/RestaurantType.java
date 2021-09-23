package example.projects.restaurant_reservation.model;

public enum RestaurantType {
    FAST_FOOD("FAST_FOOD"),
    CASUAL("CASUAL"),
    PREMIUM("PREMIUM")
    ;
    private final String type;

    RestaurantType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "RestaurantType{" +
                "type='" + type + '\'' +
                '}';
    }
}
