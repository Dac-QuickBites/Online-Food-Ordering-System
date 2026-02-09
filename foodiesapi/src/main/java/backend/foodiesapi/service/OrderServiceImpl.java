package backend.foodiesapi.service;

import com.razorpay.RazorpayException;
import backend.foodiesapi.entity.OrderEntity;
import backend.foodiesapi.entity.OrderItemEntity;
import backend.foodiesapi.io.DashboardStats;
import backend.foodiesapi.io.OrderRequest;
import backend.foodiesapi.io.OrderResponse;
import backend.foodiesapi.repository.OrderRepository;
import backend.foodiesapi.util.AuthorizationUtil;
import backend.foodiesapi.util.RazorpayUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import backend.foodiesapi.exception.BadRequestException;
import backend.foodiesapi.exception.ResourceNotFoundException;
import backend.foodiesapi.exception.UnauthorizedException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final RazorpayUtil razorpayUtil; // Utility class to handle Razorpay interactions
    private final AuthenticationFacade authenticationFacade; // To get current user
    private final UserService userService; // Injected UserService
    private final AuthorizationUtil authorizationUtil;

    @Override
    public OrderResponse createOrderWithPayment(OrderRequest request) throws RazorpayException {
        // 1. Create Order Entity
        OrderEntity order = new OrderEntity();
        order.setUserId(getCurrentUserId());
        order.setUserAddress(request.getUserAddress());
        order.setPhoneNumber(request.getPhoneNumber());
        order.setEmail(request.getEmail());
        order.setRestaurantId(request.getRestaurantId());
        order.setSpecialInstructions(request.getSpecialInstructions());

        // 2. Integrate with Razorpay to create a payment order
        // RazorpayService could be a utility to interact with the Razorpay API
        String razorpayOrderId = razorpayUtil.createRazorpayOrder(request.getAmount());

        // 3. Save the Order with Razorpay Order ID
        order.setRazorpayOrderId(razorpayOrderId);
        order.setAmount(request.getAmount());
        order.setPaymentStatus("Pending");
        order.setOrderStatus("Pending");

        // Map items from request if they exist
        if (request.getOrderedItems() != null && !request.getOrderedItems().isEmpty()) {
            List<OrderItemEntity> itemEntities = request.getOrderedItems().stream().map(itemRequest -> {
                OrderItemEntity item = new OrderItemEntity();
                item.setFoodId(itemRequest.getFoodId());
                item.setFoodName(itemRequest.getName());
                item.setPrice(itemRequest.getPrice());
                item.setQuantity(itemRequest.getQuantity());
                item.setSubTotal(itemRequest.getPrice() * itemRequest.getQuantity());
                return item;
            }).collect(Collectors.toList());
            order.setOrderedItems(itemEntities);
        }

        orderRepository.save(order);

        // 4. Return Order Response
        return convertToOrderResponse(order);
    }

    @Override
    public void verifyPayment(Map<String, String> paymentData, String status) {
        // Extract Razorpay details
        String razorpayOrderId = paymentData.get("razorpay_order_id");
        String razorpayPaymentId = paymentData.get("razorpay_payment_id");
        String razorpaySignature = paymentData.get("razorpay_signature");

        // Verify the payment signature with Razorpay
        if (!razorpayUtil.verifyRazorpaySignature(razorpayOrderId, razorpayPaymentId, razorpaySignature)) {
            throw new BadRequestException("Payment verification failed.");
        }

        // Update payment status in the Order
        OrderEntity order = orderRepository.findByRazorpayOrderId(razorpayOrderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for Razorpay Order ID"));

        order.setPaymentStatus(status);
        order.setRazorpayPaymentId(razorpayPaymentId);
        order.setRazorpaySignature(razorpaySignature);

        orderRepository.save(order);
    }

    @Override
    public List<OrderResponse> getUserOrders() {
        Long currentUserId = getCurrentUserId();
        List<OrderEntity> userOrders = orderRepository.findByUserId(currentUserId);

        return userOrders.stream().map(this::convertToOrderResponse).collect(Collectors.toList());
    }

    @Override
    public void removeOrder(String orderId) {
        OrderEntity order = orderRepository.findById(Long.parseLong(orderId))
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        orderRepository.delete(order);
    }

    @Override
    public List<OrderResponse> getOrdersOfAllUsers() {
        // Admin or authorized role should be able to access all orders
        List<OrderEntity> allOrders;
        
        if (authorizationUtil.isRestaurantOwner()) {
            Long restaurantId = authorizationUtil.getCurrentUserRestaurantId();
            allOrders = orderRepository.findByRestaurantId(restaurantId);
        } else {
            allOrders = orderRepository.findAll();
        }
        
        return allOrders.stream().map(this::convertToOrderResponse).collect(Collectors.toList());
    }

    @Override
    public void updateOrderStatus(String orderId, String status) {
        // Only admins can change the order status
        OrderEntity order = orderRepository.findById(Long.parseLong(orderId))
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        if (!authorizationUtil.ownsRestaurant(order.getRestaurantId())) {
            throw new UnauthorizedException("Unauthorized to update this order status");
        }

        // Validate that the status is acceptable (e.g., "Pending", "Shipped",
        // "Delivered")
        if (!isValidOrderStatus(status)) {
            throw new BadRequestException("Invalid order status");
        }

        order.setOrderStatus(status);
        orderRepository.save(order);
    }

    @Override
    public DashboardStats getDashboardStats() {
        List<OrderEntity> allOrders;
        
        if (authorizationUtil.isRestaurantOwner()) {
            Long restaurantId = authorizationUtil.getCurrentUserRestaurantId();
            allOrders = orderRepository.findByRestaurantId(restaurantId);
        } else {
            allOrders = orderRepository.findAll();
        }
        
        int totalOrders = allOrders.size();
        double totalRevenue = allOrders.stream()
                .mapToDouble(OrderEntity::getAmount)
                .sum();
        double averageOrderValue = totalOrders > 0 ? totalRevenue / totalOrders : 0.0;
        
        return DashboardStats.builder()
                .totalRevenue(totalRevenue)
                .totalOrders(totalOrders)
                .averageOrderValue(averageOrderValue)
                .build();
    }

    private boolean isValidOrderStatus(String status) {
        return List.of("Pending", "Food Preparing", "Out for delivery", "Delivered", "Cancelled").contains(status);
    }

    private Long getCurrentUserId() {
        return userService.findByUserId(); // Use UserService to get ID safely
    }

    private OrderResponse convertToOrderResponse(OrderEntity order) {
        return OrderResponse.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .userAddress(order.getUserAddress())
                .phoneNumber(order.getPhoneNumber())
                .email(order.getEmail())
                .orderedItems(order.getOrderedItems()) // Assuming orderedItems is a list in response
                .amount(order.getAmount())
                .paymentStatus(order.getPaymentStatus())
                .orderStatus(order.getOrderStatus())
                .razorpayOrderId(order.getRazorpayOrderId())
                .razorpayPaymentId(order.getRazorpayPaymentId())
                .razorpaySignature(order.getRazorpaySignature())
                .restaurantId(order.getRestaurantId())
                .specialInstructions(order.getSpecialInstructions())
                .build();
    }
}
