package com.sparta_spring.sparta_spring4.domain.resultType;

public enum Number {
    ZERO(0),
    ONE(1),
    HUNDRED(100),
    FIVE_HUNDRED(500),
    THOUSAND(1000),
    TEN_THOUSAND(10000),
    A_HUNDRED_THOUSAND(100000),
    MILLION(1000000);

    private int num;

    Number(int i) {
        this.num = i;
    }

    public int getNum() {
        return num;
    }

    public Number setNum(int num) {
        this.num = num;
        return this;
    }
}
