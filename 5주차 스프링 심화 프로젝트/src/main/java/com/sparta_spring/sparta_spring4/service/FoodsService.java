package com.sparta_spring.sparta_spring4.service;

import com.sparta_spring.sparta_spring4.domain.Food;
import com.sparta_spring.sparta_spring4.dto.RequestFoodsDto;
import com.sparta_spring.sparta_spring4.repository.FoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FoodsService {

    private final FoodsRepository foodsRepository;

    @Transactional(readOnly = true)
    public List<Food> findAllFoodsByRestaurantId(Long restaurantId) {
        return foodsRepository.findAllByRestaurantId(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("레스토랑 아이디가 없습니다."));
    }

    @Transactional
    public void foodsSave(List<RequestFoodsDto> requestFoodsDtoList, Long restaurantId) {
        if (findByRestaurantIdAndNameIn(requestFoodsDtoList, restaurantId).size() != 0) {
            throw new IllegalArgumentException("음식명이 중복됩니다.");
        }

        if (overlapValid(requestFoodsDtoList)) {
            throw new IllegalArgumentException("음식명이 중복됩니다.");
        }

        List<Food> foodList = new ArrayList<>();

        for (RequestFoodsDto requestFoodsDto : requestFoodsDtoList) {
            foodList.add(new Food(requestFoodsDto, restaurantId));
        }

        foodsRepository.saveAll(foodList);
    }

    public List<Food> findByRestaurantIdAndNameIn(List<RequestFoodsDto> requestFoodsDtoList, Long restaurantId) {
        return foodsRepository.findByRestaurantIdAndNameIn(
                restaurantId,
                requestFoodsDtoList.stream()
                        .map(RequestFoodsDto::getName)
                        .collect(Collectors.toList())
        );
    }

    private boolean overlapValid(List<RequestFoodsDto> requestFoodsDtoList) {
        return requestFoodsDtoList.stream().distinct().count() != requestFoodsDtoList.size();
    }

    public List<Food> findFoodsByRestaurantIdAndIdIn(Long restaurantId, List<Long> foodIds) {
        return foodsRepository.findFoodsByRestaurantIdAndIdIn(restaurantId, foodIds);
    }
}
