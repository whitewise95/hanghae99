package com.sparta_spring.sparta_spring4.domain;

import com.sparta_spring.sparta_spring4.domain.resultType.FoodInfo;
import com.sparta_spring.sparta_spring4.dto.*;
import lombok.*;

import javax.persistence.*;

import java.util.List;

import static com.sparta_spring.sparta_spring4.domain.resultType.Number.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class OrderFood extends FoodInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long foodsId;

    @Column(nullable = false)
    private Integer quantity;

    public OrderFood(RequestFoodsDto requestFoodsDto, List<RequestFoodsDto> foodList) {
        this.foodsId = requestFoodsDto.getId();
        this.quantity = requestFoodsDto.getQuantity();
        setFoodNameAndPrice(requestFoodsDto, foodList);
    }

    public OrderFood(RequestOrderFoodDto requestOrderFoodDto) {
        this.id = requestOrderFoodDto.getId();
        this.foodsId = requestOrderFoodDto.getFoodsId();
        this.quantity = requestOrderFoodDto.getQuantity();
        this.setPrice(requestOrderFoodDto.getPrice());
        this.setName(requestOrderFoodDto.getName());
    }

    private void setFoodNameAndPrice(RequestFoodsDto requestFoodsDto, List<RequestFoodsDto> foodList) {
        for (RequestFoodsDto foodsDto : foodList) {
            if (foodsDto.getId() == requestFoodsDto.getId()) {
                this.setName(foodsDto.getName());
                this.setPrice(foodsDto.getPrice() * orderQuantityCheck(requestFoodsDto.getQuantity()));
            }
        }
    }

    private int orderQuantityCheck(int quantity) {
        if (quantity > HUNDRED.getNum() || quantity < ONE.getNum()) {
            throw new IllegalArgumentException("음식당 주문 허용 수량은 1 ~ 100 이하입니다.");
        }
        return quantity;
    }

}
