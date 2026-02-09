package backend.foodiesapi.io;

import backend.foodiesapi.entity.OrderItemEntity;
import java.util.List;

public class OrderResponse {
    private Long id;
    private Long userId;
    private String userAddress;
    private String phoneNumber;
    private String email;
    private double amount;
    private String paymentStatus;
    private String razorpayOrderId;
    private String orderStatus;
    private List<OrderItemEntity> orderedItems;
    private String razorpayPaymentId;
    private String razorpaySignature;
    private Long restaurantId;
    private String specialInstructions;

    public OrderResponse() {
    }

    public OrderResponse(Long id, Long userId, String userAddress, String phoneNumber, String email, double amount,
            String paymentStatus, String razorpayOrderId, String orderStatus, List<OrderItemEntity> orderedItems,
            String razorpayPaymentId, String razorpaySignature, Long restaurantId) {
        this.id = id;
        this.userId = userId;
        this.userAddress = userAddress;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.razorpayOrderId = razorpayOrderId;
        this.orderStatus = orderStatus;
        this.orderedItems = orderedItems;
        this.razorpayPaymentId = razorpayPaymentId;
        this.razorpaySignature = razorpaySignature;
        this.restaurantId = restaurantId;
    }

    public static OrderResponseBuilder builder() {
        return new OrderResponseBuilder();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getRazorpayOrderId() {
        return razorpayOrderId;
    }

    public void setRazorpayOrderId(String razorpayOrderId) {
        this.razorpayOrderId = razorpayOrderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderItemEntity> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<OrderItemEntity> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public String getRazorpayPaymentId() {
        return razorpayPaymentId;
    }

    public void setRazorpayPaymentId(String razorpayPaymentId) {
        this.razorpayPaymentId = razorpayPaymentId;
    }

    public String getRazorpaySignature() {
        return razorpaySignature;
    }

    public void setRazorpaySignature(String razorpaySignature) {
        this.razorpaySignature = razorpaySignature;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public static class OrderResponseBuilder {
        private Long id;
        private Long userId;
        private String userAddress;
        private String phoneNumber;
        private String email;
        private double amount;
        private String paymentStatus;
        private String razorpayOrderId;
        private String orderStatus;
        private List<OrderItemEntity> orderedItems;
        private String razorpayPaymentId;
        private String razorpaySignature;
        private Long restaurantId;
        private String specialInstructions;

        public OrderResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public OrderResponseBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public OrderResponseBuilder userAddress(String userAddress) {
            this.userAddress = userAddress;
            return this;
        }

        public OrderResponseBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public OrderResponseBuilder email(String email) {
            this.email = email;
            return this;
        }

        public OrderResponseBuilder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public OrderResponseBuilder paymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
            return this;
        }

        public OrderResponseBuilder razorpayOrderId(String razorpayOrderId) {
            this.razorpayOrderId = razorpayOrderId;
            return this;
        }

        public OrderResponseBuilder orderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public OrderResponseBuilder orderedItems(List<OrderItemEntity> orderedItems) {
            this.orderedItems = orderedItems;
            return this;
        }

        public OrderResponseBuilder razorpayPaymentId(String razorpayPaymentId) {
            this.razorpayPaymentId = razorpayPaymentId;
            return this;
        }

        public OrderResponseBuilder razorpaySignature(String razorpaySignature) {
            this.razorpaySignature = razorpaySignature;
            return this;
        }

        public OrderResponseBuilder restaurantId(Long restaurantId) {
            this.restaurantId = restaurantId;
            return this;
        }

        public OrderResponseBuilder specialInstructions(String specialInstructions) {
            this.specialInstructions = specialInstructions;
            return this;
        }

        public OrderResponse build() {
            return new OrderResponse(id, userId, userAddress, phoneNumber, email, amount, paymentStatus,
                    razorpayOrderId, orderStatus, orderedItems, razorpayPaymentId, razorpaySignature, restaurantId);
        }
    }
}
