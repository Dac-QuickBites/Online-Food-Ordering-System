package backend.foodiesapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "foods")
public class FoodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Auto-increment ID

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private double price;

    private String category;

    private String imageUrl;

    @Column(name = "restaurant_id")
    private Long restaurantId;

    public FoodEntity() {
    }

    public FoodEntity(Long id, String name, String description, double price, String category, String imageUrl, Long restaurantId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.imageUrl = imageUrl;
        this.restaurantId = restaurantId;
    }

    public static FoodEntityBuilder builder() {
        return new FoodEntityBuilder();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public static class FoodEntityBuilder {
        private Long id;
        private String name;
        private String description;
        private double price;
        private String category;
        private String imageUrl;
        private Long restaurantId;

        public FoodEntityBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public FoodEntityBuilder name(String name) {
            this.name = name;
            return this;
        }

        public FoodEntityBuilder description(String description) {
            this.description = description;
            return this;
        }

        public FoodEntityBuilder price(double price) {
            this.price = price;
            return this;
        }

        public FoodEntityBuilder category(String category) {
            this.category = category;
            return this;
        }

        public FoodEntityBuilder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public FoodEntityBuilder restaurantId(Long restaurantId) {
            this.restaurantId = restaurantId;
            return this;
        }

        public FoodEntity build() {
            return new FoodEntity(id, name, description, price, category, imageUrl, restaurantId);
        }
    }
}
