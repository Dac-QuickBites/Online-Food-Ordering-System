package backend.foodiesapi.io;

public class FoodResponse {
    private Long id;
    private String name;
    private String description;
    private String category;
    private double price;
    private String imageUrl;
    private Long restaurantId;
    private String restaurantName;

    public FoodResponse() {
    }

    public FoodResponse(Long id, String name, String description, String category, double price, String imageUrl, Long restaurantId, String restaurantName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.imageUrl = imageUrl;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
    }

    public static FoodResponseBuilder builder() {
        return new FoodResponseBuilder();
    }

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

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public static class FoodResponseBuilder {
        private Long id;
        private String name;
        private String description;
        private String category;
        private double price;
        private String imageUrl;
        private Long restaurantId;
        private String restaurantName;

        public FoodResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public FoodResponseBuilder name(String name) {
            this.name = name;
            return this;
        }

        public FoodResponseBuilder description(String description) {
            this.description = description;
            return this;
        }

        public FoodResponseBuilder category(String category) {
            this.category = category;
            return this;
        }

        public FoodResponseBuilder price(double price) {
            this.price = price;
            return this;
        }

        public FoodResponseBuilder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public FoodResponseBuilder restaurantId(Long restaurantId) {
            this.restaurantId = restaurantId;
            return this;
        }

        public FoodResponseBuilder restaurantName(String restaurantName) {
            this.restaurantName = restaurantName;
            return this;
        }

        public FoodResponse build() {
            return new FoodResponse(id, name, description, category, price, imageUrl, restaurantId, restaurantName);
        }
    }
}
