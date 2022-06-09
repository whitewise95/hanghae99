package com.sparta_spring.sparta_spring4.controller;

import com.sparta_spring.sparta_spring4.domain.Receipt;
import com.sparta_spring.sparta_spring4.domain.resultType.ValidationGroups;
import com.sparta_spring.sparta_spring4.dto.RequestReceiptDto;
import com.sparta_spring.sparta_spring4.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order/request")
    public Receipt orderSave(@RequestBody @Validated(ValidationGroups.Save1.class) RequestReceiptDto requestReceiptDto) {
        return orderService.orderSave(requestReceiptDto);
    }

    @GetMapping("/orders")
    public List<Receipt> findAllOrder() {
        return orderService.findAll();
    }
}
