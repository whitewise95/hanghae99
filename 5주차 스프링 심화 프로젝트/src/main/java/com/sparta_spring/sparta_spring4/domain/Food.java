package com.sparta_spring.sparta_spring4.domain;

import com.sparta_spring.sparta_spring4.domain.resultType.FoodInfo;
import com.sparta_spring.sparta_spring4.dto.RequestFoodsDto;
import lombok.*;

import javax.persistence.*;

import static com.sparta_spring.sparta_spring4.domain.resultType.Number.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Food extends FoodInfo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long restaurantId;

    public Food(RequestFoodsDto requestFoodsDto, Long restaurantId) {
        this.setName(requestFoodsDto.getName());
        this.setPrice(valid(requestFoodsDto.getPrice(), HUNDRED.getNum()));
        this.restaurantId = restaurantId;
    }

    private int valid(int price, int sharedNumber) {
        if (price % sharedNumber != ZERO.getNum()) {
            throw new IllegalArgumentException(sharedNumber + "원 단위로 입력해주세요.");
        }
        if (!(sharedNumber <= price && price <= MILLION.getNum())) {
            throw new IllegalArgumentException("허용 값은 100원 ~ 1,000,000원");
        }
        return price;
    }
}
