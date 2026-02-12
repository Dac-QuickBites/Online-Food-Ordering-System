package backend.foodiesapi.controller;

import backend.foodiesapi.entity.RestaurantEntity;
import backend.foodiesapi.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping(consumes = { "multipart/form-data" })
    public RestaurantEntity createRestaurant(
            @jakarta.validation.Valid @RequestPart("restaurant") backend.foodiesapi.io.RestaurantRequest restaurantRequest,
            @RequestPart(value = "image", required = false) MultipartFile file) {
        RestaurantEntity restaurant = new RestaurantEntity();
        restaurant.setName(restaurantRequest.getName());
        restaurant.setDescription(restaurantRequest.getDescription());
        restaurant.setAddress(restaurantRequest.getAddress());
        restaurant.setPhoneNumber(restaurantRequest.getPhoneNumber());
        return restaurantService.createRestaurant(restaurant, file);
    }

    @GetMapping
    public List<RestaurantEntity> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{id}")
    public RestaurantEntity getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id);
    }

    @PutMapping(value = "/{id}", consumes = { "multipart/form-data" })
    public RestaurantEntity updateRestaurant(
            @PathVariable Long id,
            @jakarta.validation.Valid @RequestPart("restaurant") backend.foodiesapi.io.RestaurantRequest restaurantRequest,
            @RequestPart(value = "image", required = false) MultipartFile file) {
        RestaurantEntity restaurant = new RestaurantEntity();
        restaurant.setName(restaurantRequest.getName());
        restaurant.setDescription(restaurantRequest.getDescription());
        restaurant.setAddress(restaurantRequest.getAddress());
        restaurant.setPhoneNumber(restaurantRequest.getPhoneNumber());
        return restaurantService.updateRestaurant(id, restaurant, file);
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
    }

    @GetMapping("/my-restaurant")
    public RestaurantEntity getMyRestaurant() {
        return restaurantService.getMyRestaurant();
    }
}
