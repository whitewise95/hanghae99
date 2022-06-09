package com.sparta_spring.sparta_spring4.dto;

import com.sparta_spring.sparta_spring4.domain.OrderFood;
import com.sparta_spring.sparta_spring4.domain.resultType.FoodInfo;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class RequestOrderFoodDto extends FoodInfo {

    private Long id;

    private Long foodsId;

    private Integer quantity;

    public RequestOrderFoodDto(OrderFood orderFood) {
        this.id = orderFood.getId();
        this.foodsId = orderFood.getFoodsId();
        this.quantity = orderFood.getQuantity();
        this.setName(orderFood.getName());
        this.setPrice(orderFood.getPrice());
    }
}
