package com.sparta_spring.sparta_spring4.dto;

import com.sparta_spring.sparta_spring4.domain.*;
import lombok.*;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class RequestReceiptDto {

    private Long restaurantId;

    private String restaurantName;

    private int deliveryFee;

    private int totalPrice;

    private List<RequestFoodsDto> foods = new ArrayList<>();

    public RequestReceiptDto(List<Food> foodList, RequestReceiptDto requestReceiptDto, Restaurant restaurant) {
        List<RequestFoodsDto> requestFoodsDtos = foodList.stream().map(RequestFoodsDto::new).collect(Collectors.toList());
        totalPriceCalculate(requestReceiptDto, requestFoodsDtos);
        minPriceCheck(restaurant);
        this.foods = requestFoodsDtos;
        this.restaurantName = restaurant.getName();
        this.deliveryFee = restaurant.getDeliveryFee();
        this.totalPrice += restaurant.getDeliveryFee();
    }

    private void totalPriceCalculate(RequestReceiptDto requestReceiptDto, List<RequestFoodsDto> foodList) {
        for (RequestFoodsDto dtoFood : requestReceiptDto.getFoods()) {
            for (RequestFoodsDto food : foodList) {
                if (food.getId() == dtoFood.getId()) {
                    this.totalPrice += food.getPrice() * dtoFood.getQuantity();
                    break;
                }
            }
        }
    }

    private void minPriceCheck(Restaurant restaurant){
        if (this.totalPrice < restaurant.getMinOrderPrice()) {
            throw new IllegalArgumentException("최소 금액에 맞춰 주문해주세요.");
        }
    }
}