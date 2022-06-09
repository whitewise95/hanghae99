package com.sparta_spring.sparta_spring4.controller;

import com.sparta_spring.sparta_spring4.domain.Restaurant;
import com.sparta_spring.sparta_spring4.domain.resultType.ValidationGroups;
import com.sparta_spring.sparta_spring4.dto.RequestRestaurantDto;
import com.sparta_spring.sparta_spring4.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> findAllRestaurant() {
        return restaurantService.findAllRestaurant();
    }

    @PostMapping("/restaurant/register")
    public Restaurant restaurantSave(@RequestBody @Validated(ValidationGroups.Save1.class) RequestRestaurantDto requestRestaurantDto) {
        return restaurantService.restaurantSave(requestRestaurantDto);
    }

}
