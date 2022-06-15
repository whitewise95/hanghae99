package com.sparta_spring.sparta_spring4.domain;

import com.sparta_spring.sparta_spring4.dto.*;
import lombok.*;

import javax.persistence.*;

import java.util.*;
import java.util.stream.Collectors;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column
    private String restaurantName;

    @Column(nullable = false)
    private int deliveryFee;

    @Column(nullable = false)
    private int totalPrice;

    @OneToMany
    @JoinColumn(name = "OrderFood_id")
    private List<OrderFood> foods = new ArrayList<>();

    public Receipt(RequestReceiptDto requestReceiptDto, List<RequestOrderFoodDto> requestOrderFoodDtos) {
        this.restaurantName = requestReceiptDto.getRestaurantName();
        this.deliveryFee = requestReceiptDto.getDeliveryFee();
        this.foods = requestOrderFoodDtos.stream().map(OrderFood::new).collect(Collectors.toList());
        this.totalPrice = requestReceiptDto.getTotalPrice();
    }
}
