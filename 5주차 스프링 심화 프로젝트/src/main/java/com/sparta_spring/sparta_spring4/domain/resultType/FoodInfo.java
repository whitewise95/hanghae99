package com.sparta_spring.sparta_spring4.domain.resultType;

import lombok.*;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
public abstract class FoodInfo {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

}
