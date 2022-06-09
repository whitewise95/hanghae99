package com.sparta_spring.sparta_spring4.repository;

import com.sparta_spring.sparta_spring4.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface FoodsRepository extends JpaRepository<Food, Long> {
    Optional<List<Food>> findAllByRestaurantId(Long restaurantId);

    List<Food> findByRestaurantIdAndNameIn(Long restaurantId, List<String> name);

    List<Food> findFoodsByRestaurantIdAndIdIn(Long restaurantId, List<Long> foodIds);
}
