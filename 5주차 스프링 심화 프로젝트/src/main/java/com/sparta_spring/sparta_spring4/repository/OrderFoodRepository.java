package com.sparta_spring.sparta_spring4.repository;

import com.sparta_spring.sparta_spring4.domain.OrderFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderFoodRepository extends JpaRepository<OrderFood, Long> {
}
