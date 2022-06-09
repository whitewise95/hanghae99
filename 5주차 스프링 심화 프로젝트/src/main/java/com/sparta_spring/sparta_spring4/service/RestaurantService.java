package com.sparta_spring.sparta_spring4.service;

import com.sparta_spring.sparta_spring4.domain.Restaurant;
import com.sparta_spring.sparta_spring4.dto.RequestRestaurantDto;
import com.sparta_spring.sparta_spring4.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Transactional(readOnly = true)
    public List<Restaurant> findAllRestaurant() {
        return restaurantRepository.findAll();
    }

    @Transactional
    public Restaurant restaurantSave(RequestRestaurantDto requestRestaurantDto) {
        return restaurantRepository.save(new Restaurant(requestRestaurantDto));
    }

    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 레스토랑 아이디입니다."));
    }
}
