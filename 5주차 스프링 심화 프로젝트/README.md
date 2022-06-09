# #목차
1) [버전 1.1](#버전-1.1))
    - [프로젝트 소개](#프로젝트-소개)
    - [API 기능 구성도](#API-기능-구성도)
    - [과제 목적](#과제-목적)
      - [API명세서](#명세서를-보고-기능을-구현할-수-있게-된다)
      - [EntityClass](#테이블의-관계도를-이해할-수-있다)
      - [for문을 사용할 상황과 Stream을 사용할 상황을 구별](#for문-vs-Stream)
    - [유지 보수](#유지보수)
    - [Comment](#comment)
2) [버전 1.2](#버전-1.2))
    - [변경된 사항](#변경된-사항)


<br>
<br>


# #버전 1.1
> 22.06.09  
## 프로젝트 소개
이번 과제는 항해99에서 설계된 API정의서에 맞춰 기능을 구현하는 프로젝트였습니다.  
따로 UI없이 API로만 구성되어 있습니다. 하지만 추 후 ui도 추가할 예정입니다.


<br>
<br>


## API 기능 구성도  
![음식점 등록](https://user-images.githubusercontent.com/81284265/172744123-a4f32b96-8088-42d5-8521-fbcb38475ad9.png)  


<br>
<br>


## 과제 목적
이번 과제에서는 내가 생각하기엔 이런것들을 이 목표를 가지고 공부할 수 있었다.

- 명세서를 보고 기능을 구현할 수 있게 된다.
- 테이블의 관계도를 이해할 수 있다.
- for문을 사용할 상황과 Stream을 사용할 상황을 구별할 수 있다.



<br>
<br>



### 명세서를 보고 기능을 구현할 수 있게 된다
> API 명세서  

![화면 캡처 2022-06-09 103659](https://user-images.githubusercontent.com/81284265/172745813-b2a2d37c-4ce3-47e1-844f-d758be4c4a9e.png)
```java
@GetMapping("/restaurants")
public List<Restaurant> findAllRestaurant() {
    return restaurantService.findAllRestaurant();
}

@PostMapping("/restaurant/register")
public Restaurant restaurantSave(@RequestBody @Validated(ValidationGroups.Save1.class) RequestRestaurantDto requestRestaurantDto) {
    return restaurantService.restaurantSave(requestRestaurantDto);
}
```
![화면 캡처 2022-06-09 103722](https://user-images.githubusercontent.com/81284265/172745816-539e1768-a75f-498a-838e-01a95abe92a0.png)
```java
@RequiredArgsConstructor
@RestController
@RequestMapping("/restaurant")
public class FoodsController {

    private final FoodsService foodsService;

    @GetMapping("/{restaurantId}/foods")
    public List<Food> findAllFoodsByRestaurantId(@PathVariable Long restaurantId) {
        return foodsService.findAllFoodsByRestaurantId(restaurantId);
    }

    @PostMapping("/{restaurantId}/food/register")
    public void foodsSave(@PathVariable Long restaurantId,
                          @RequestBody List<RequestFoodsDto> requestFoodsDto) {
        foodsService.foodsSave(requestFoodsDto, restaurantId);
    }
}
```
![화면 캡처 2022-06-09 103759](https://user-images.githubusercontent.com/81284265/172745818-5f83c51e-d1d5-462a-baae-050a30fbda6a.png)
```java
    @PostMapping("/order/request")
    public UserOrder orderSave(@RequestBody @Validated(ValidationGroups.Save1.class) RequestOrderDto requestReceiptDto) {
        return orderService.orderSave(requestReceiptDto);
    }

    @GetMapping("/orders")
    public List<UserOrder> findAllOrder() {
        return orderService.findAll();
    }
```



<br>
<br>



### 테이블의 관계도를 이해할 수 있다.
> Food(메뉴들) 클래스와 UserOrderFood(주문한 정보) 클래스에 같은 변수를 쓰는게 있어서 abstract클래스를 활용해보았다.  
```java
public abstract class FoodInfo {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

}
```  
> 유효성 검사를 할 때 공동으로 쓰는 숫자들이 많아 enum으로 모음을 만들어 이용하였다.   

```java
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
```  

>  유효성검사는 DTO클래스에서 Entity클래스로 데이터타입을 변경할 때 체크를 해주고 있습니다.
```java
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
```  
> 주문한 음식들만 DB에 저장할 Entity클래스 입니다.
```java
@Setter
@Getter
@Entity
@NoArgsConstructor
public class UserOrderFood extends FoodInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long foodsId;

    @Column(nullable = false)
    private Integer quantity;

    public UserOrderFood(RequestFoodsDto requestFoodsDto, List<RequestFoodsDto> foodList) {
        this.foodsId = requestFoodsDto.getId();
        this.quantity = requestFoodsDto.getQuantity();
        setFoodNameAndPrice(requestFoodsDto, foodList);
    }

    public UserOrderFood(RequestUserOrderFoodDto requestOrderFoodDto) {
        this.id = requestOrderFoodDto.getId();
        this.foodsId = requestOrderFoodDto.getFoodsId();
        this.quantity = requestOrderFoodDto.getQuantity();
        this.setPrice(requestOrderFoodDto.getPrice());
        this.setName(requestOrderFoodDto.getName());
    }

    private void setFoodNameAndPrice(RequestFoodsDto requestFoodsDto, List<RequestFoodsDto> foodList) {
        for (RequestFoodsDto foodsDto : foodList) {
            if (foodsDto.getId() == requestFoodsDto.getId()) {
                this.setName(foodsDto.getName());
                this.setPrice(foodsDto.getPrice() * orderQuantityCheck(requestFoodsDto.getQuantity()));
            }
        }
    }

    private int orderQuantityCheck(int quantity) {
        if (quantity > HUNDRED.getNum() || quantity < ONE.getNum()) {
            throw new IllegalArgumentException("음식당 주문 허용 수량은 1 ~ 100 이하입니다.");
        }
        return quantity;
    }

}

```  
> Food(음식들 테이블) 과 UserOrderFood(주문한 음식들 테이블) 을 비교해 음식의 가격을 합하고 최종 주문결과를 저장하는 클래스입니다.  
>  @OneToMany를 사용해 UserOrderFood클래스와 UserOrder 단반향관계를 이어주고 있습니다.
```java
@Setter
@Getter
@Entity
@NoArgsConstructor
public class UserOrder {

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
    @JoinColumn(name = "UserOrderFood_id")
    private List<UserOrderFood> foods = new ArrayList<>();

    public UserOrder(RequestOrderDto requestReceiptDto, List<RequestUserOrderFoodDto> requestOrderFoodDtos) {
        this.restaurantName = requestReceiptDto.getRestaurantName();
        this.deliveryFee = requestReceiptDto.getDeliveryFee();
        this.foods = requestOrderFoodDtos.stream().map(UserOrderFood::new).collect(Collectors.toList());
        this.totalPrice = requestReceiptDto.getTotalPrice();
    }
}

```   

<br>    
<br>    

   
### for문 vs Stream

📍 for문과 Stream 중 선택기준은 단순 반복문만 사용한다면 for문을 데이터 가공이 이루어진다면 Stream을 사용했습니다.  

⭐️for문(향상된 for문) ex)   
Stream.forEach로 돌리 수 있는 상황이지만 단순 반복문은 Stream의 취지에 맞지 않기에 사용하지 않았다.
```java
List<Food> foodList = new ArrayList<>();

for (RequestFoodsDto requestFoodsDto : requestFoodsDtoList) {
    foodList.add(new Food(requestFoodsDto, restaurantId));
}
```  


⭐️ Strema사용 ex)   

RequestFoodsDto의 id만 Integer로 이루어진 List로 가공해야하는 로직인데 for문 보다 더 간결하게 코드를 작성할 수 있으며 단순 반복이 아니기에 사용하였다.

```java
requestReceiptDto.getFoods().stream()
        .map(RequestFoodsDto::getId)
        .collect(Collectors.toList())
```  


<br>  
<br>   


## 유지보수
- 프론트 추가
- 쿠폰제도 추가
- 리뷰 시스템
- 업체 이미지 등록
- 메뉴 이미지 등록



<br>  
<br>   

 

## Comment

이번 프로젝트는 UI를 만들지 않아서 비교적 빨리 완성되었다.  
그래서 그런가 비지니스 로직에 이게 효율적이 코드가 맞는지 아닌지를 분석할 시간이 있었고  
for문과 Stream에 대해 깊이 공부할 수 있던 시간이였다.
UI가 없지만 UI가지 만들면 그럴싸한 서비스가 될 수 있을거같다.



# #버전-1.2

## 변경된 사항
domain클래스들의 네임이 의미와 맞지 않아서 수정을 하였다.
```
📍UserOrderFood -> OrderFood   
= 이 클래스는 고객이 주문한 음식을 저장하는 domain이다 근데 User가 들어감으로 
  의미가 변질되는 느낌이라 OrderFood라는 변경

📍UserOrder -> Receipt
= 고객이 최종 주문한 음식들과 식당명 그리고 가격들을 저장하는 domain이다 
  근데 UserOrder는 그냥 주문같은 느낌인데 영수증에 가깝기 때문에 Receipt로 변경하였다.

```
