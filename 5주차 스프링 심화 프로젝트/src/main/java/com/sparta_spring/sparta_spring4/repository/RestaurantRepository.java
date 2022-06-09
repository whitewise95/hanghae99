package com.sparta_spring.sparta_spring4.repository;

import com.sparta_spring.sparta_spring4.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
