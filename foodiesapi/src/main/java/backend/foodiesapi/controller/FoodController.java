package backend.foodiesapi.controller;

import backend.foodiesapi.io.FoodRequest;
import backend.foodiesapi.io.FoodResponse;
import backend.foodiesapi.service.FoodService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class FoodController {

    private final FoodService foodService;

    @PostMapping
    public FoodResponse addFood(@Valid @RequestPart("food") FoodRequest foodRequest,
            @RequestPart("file") MultipartFile file) {
        return foodService.addFood(foodRequest, file);
    }

    @PutMapping("/{id}")
    public FoodResponse updateFood(@PathVariable String id,
            @Valid @RequestPart("food") FoodRequest foodRequest,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        return foodService.updateFood(id, foodRequest, file);
    }

    @GetMapping
    public List<FoodResponse> readFoods() {
        return foodService.readFoods();
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<FoodResponse> getFoodsByRestaurant(@PathVariable Long restaurantId) {
        return foodService.readFoodsByRestaurant(restaurantId);
    }

    @GetMapping("/{id}")
    public FoodResponse readFood(@PathVariable String id) {
        return foodService.readFood(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFood(@PathVariable String id) {
        foodService.deleteFood(id);
    }
}
