package com.sparta_spring.sparta_spring4.dto;

import com.sparta_spring.sparta_spring4.domain.Food;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class RequestFoodsDto {

    private Long id;

    private String name;

    private int price;

    private int quantity;

    private int total;

    public RequestFoodsDto(Food food) {
        this.id = food.getId();
        this.name = food.getName();
        this.price = food.getPrice();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RequestFoodsDto) {
            RequestFoodsDto requestFoodsDto = (RequestFoodsDto) obj;
            return requestFoodsDto.getName().equals(this.name);
        } else {
            return false;
        }
    } // equals 동등성비교

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
