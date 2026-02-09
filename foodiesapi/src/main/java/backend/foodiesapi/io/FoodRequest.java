package backend.foodiesapi.io;

public class FoodRequest {
    @jakarta.validation.constraints.NotBlank(message = "Name is required")
    private String name;

    @jakarta.validation.constraints.NotBlank(message = "Description is required")
    private String description;

    @jakarta.validation.constraints.NotBlank(message = "Category is required")
    private String category;

    @jakarta.validation.constraints.Positive(message = "Price must be positive")
    private double price;

    @jakarta.validation.constraints.NotNull(message = "Restaurant ID is required")
    private Long restaurantId;

    public FoodRequest() {
    }

    public FoodRequest(String name, String description, String category, double price, Long restaurantId) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
