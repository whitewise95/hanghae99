package com.sparta_spring.sparta_spring4.dto;

import com.sparta_spring.sparta_spring4.domain.resultType.ValidationGroups;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class RequestRestaurantDto {

    @NotNull(groups = { ValidationGroups.Save1.class }, message = "Save 는 반드시 존재해야 합니다.")
    private String name;

    @NotNull(groups = { ValidationGroups.Save1.class }, message = "minOrderPrice 는 반드시 존재해야 합니다.")
    private int minOrderPrice;

    @NotNull(groups = { ValidationGroups.Save1.class }, message = "deliveryFee 는 반드시 존재해야 합니다.")
    private int deliveryFee;

}
