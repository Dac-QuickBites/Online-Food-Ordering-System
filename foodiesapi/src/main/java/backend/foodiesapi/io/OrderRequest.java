package backend.foodiesapi.io;

import java.util.List;

public class OrderRequest {
    private Long userId;
    private String userAddress;
    private String phoneNumber;
    private String email;
    private double amount;
    private Long restaurantId;
    private String specialInstructions;
    private List<OrderItemRequest> orderedItems;

    public OrderRequest() {
    }

    public List<OrderItemRequest> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<OrderItemRequest> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public OrderRequest(Long userId, String userAddress, String phoneNumber, String email, double amount, Long restaurantId) {
        this.userId = userId;
        this.userAddress = userAddress;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.amount = amount;
        this.restaurantId = restaurantId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
