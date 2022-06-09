package com.sparta_spring.sparta_spring4.domain;

import com.sparta_spring.sparta_spring4.dto.RequestRestaurantDto;
import lombok.*;

import javax.persistence.*;

import static com.sparta_spring.sparta_spring4.domain.resultType.Number.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int minOrderPrice;

    @Column(nullable = false)
    private int deliveryFee;

    public Restaurant(RequestRestaurantDto requestRestaurantDto) {
        this.name = requestRestaurantDto.getName();
        this.minOrderPrice = valid(requestRestaurantDto.getMinOrderPrice(), HUNDRED.getNum());
        this.deliveryFee = valid(requestRestaurantDto.getDeliveryFee(), FIVE_HUNDRED.getNum());
    }

    private int valid(int price, int sharedNumber) {
        if (price % sharedNumber != ZERO.getNum()) {
            throw new IllegalArgumentException(sharedNumber + "단위로 입력해주세요.");
        }
        if (sharedNumber == HUNDRED.getNum()) {
            if (!(THOUSAND.getNum() <= price && price <= A_HUNDRED_THOUSAND.getNum())) {
                throw new IllegalArgumentException("허용값: 1,000원 ~ 100,000원");
            }
        } else {
            if (!(ZERO.getNum() <= price && price <= TEN_THOUSAND.getNum())) {
                throw new IllegalArgumentException("허용값: 0원 ~ 10,000원");
            }
        }
        return price;
    }
}
