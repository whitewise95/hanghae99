package com.sparta_spring.sparta_spring4.service;

import com.sparta_spring.sparta_spring4.domain.*;
import com.sparta_spring.sparta_spring4.dto.*;
import com.sparta_spring.sparta_spring4.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final ReceiptRepository receiptRepository;
    private final FoodsService foodsService;
    private final RestaurantService restaurantService;
    private final OrderFoodRepository orderFoodRepository;

    @Transactional
    public Receipt orderSave(RequestReceiptDto requestReceiptDto) {
        RequestReceiptDto OrderDto = new RequestReceiptDto(
                foodsService.findFoodsByRestaurantIdAndIdIn(
                        requestReceiptDto.getRestaurantId(),
                        requestReceiptDto.getFoods().stream().map(RequestFoodsDto::getId).collect(Collectors.toList())
                ),
                requestReceiptDto,
                restaurantService.findById(requestReceiptDto.getRestaurantId())

        );
        return receiptRepository.save(
                new Receipt(
                        OrderDto,
                        orderFoodRepository.saveAll(
                                        requestReceiptDto.getFoods().stream()
                                                .map(requestFoodsDto -> new OrderFood(requestFoodsDto, OrderDto.getFoods()))
                                                .collect(Collectors.toList())
                                ).stream()
                                .map(RequestOrderFoodDto::new)
                                .collect(Collectors.toList())
                )
        );
    }

    @Transactional(readOnly = true)
    public List<Receipt> findAll() {
        return receiptRepository.findAll();
    }
}
