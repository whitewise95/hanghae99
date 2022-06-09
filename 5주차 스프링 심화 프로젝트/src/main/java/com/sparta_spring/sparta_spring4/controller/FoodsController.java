package com.sparta_spring.sparta_spring4.controller;

import com.sparta_spring.sparta_spring4.domain.Food;
import com.sparta_spring.sparta_spring4.dto.RequestFoodsDto;
import com.sparta_spring.sparta_spring4.service.FoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/restaurant")
public class FoodsController {

    private final FoodsService foodsService;

    @GetMapping("/{restaurantId}/foods")
    public List<Food> findAllFoodsByRestaurantId(@PathVariable Long restaurantId) {
        return foodsService.findAllFoodsByRestaurantId(restaurantId);
    }

    @PostMapping("/{restaurantId}/food/register")
    public void foodsSave(@PathVariable Long restaurantId,
                          @RequestBody List<RequestFoodsDto> requestFoodsDto) {
        foodsService.foodsSave(requestFoodsDto, restaurantId);
    }
}
