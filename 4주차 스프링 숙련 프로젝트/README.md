

# My Blog 
- 간단한 글과 이미지를 업로드할 수 있는 웹 
- 스프링 시큐리티와 JWT를 사용하여 회원관리 및 로그인 유지와 댓글CRUD도 가능하게 develop 하는 프로젝트 입니다.

![화면 캡처 2022-05-26 152101](https://user-images.githubusercontent.com/81284265/170429416-7c76d86e-a24b-4d21-bc06-0f5cf2d4cbc7.png)



# #이번 프로젝트에서 배운점 및 알게된 점
> 링크를 타고 들어가면 정리해둔 내용을 확인 할 수 있습니다.
## 개발 환경
> 언어
- [JAVA](https://github.com/whitewise95/TIL/tree/main/Java)
> 프레임워크
- [Spring](https://github.com/whitewise95/TIL/tree/main/Java/Spring)
- [JPA]()
> view 
- HTML5
> 라이브러리
- [SweetAlert2](https://github.com/whitewise95/TIL/blob/main/HTML/SweetAlert2.md)
- [twbspagination](https://github.com/whitewise95/TIL/blob/main/HTML/paging/twbspagination%EC%98%88%EC%A0%9C%20%EB%B0%8F%20%EC%98%B5%EC%85%98.md) 
> css  
- [부트스트랩 5.0 cdn사용](https://getbootstrap.kr/).
- [구글폰트](https://fonts.google.com/?subset=korean).  
> 서버환경
-  AWS EC2 (Ubuntu 22.04 LTS)
> DB
- AWS RDS(MySql)

> 그 외  
- jQuery
- Ajax  

## DB설계
| DB : blog | 타입            | 컬럼명          | 설명         |
|-----------|---------------|--------------|------------|
| NotNull   | Long (PK)     | id           | 글의 고유번호    |
| NotNull   | String        | content      | 글 내용       |
| NotNull   | String        | password     | 글 비밀번호     |
|           | String        | imgUrl       | 이미지path    |
| NotNull   | String        | writer       | 작성자        |
| NotNull   | LocalDateTime | createDate   | 글 작성 날짜    |
| NotNull   | LocalDateTime | modifiedDate | 최근 글 수정 날짜 |


## API정의서

| 기능                         | method | url                                                  | request | response |
|----------------------------|--------|------------------------------------------------------|---------|----------|
| 전체 게시글 목록 조회 API           | GET    | blog                                                 |         | 1타입      |
| 페이징 처리한 게시글 목록 조회 (Size 6) | GET    | blogByPage?size=6&page=${page}&sort=createDate,desc  |         | 1타입      |
| 게시글 작성 API                 | POST   | blog                                                 | 2타입     | id       |
| 게시글 수정 API                 | PUT    | blog/{id}                                            | 2타입     | id       |
| 게시글 삭제 API                 | DELETE | blog/{id}                                            | 3타입     | id       |


1타입
```
 {
    "createDate": "2022.05.26 04:21:49",
    "modifiedDate": "2022.05.26 04:21:49"
    "id": 96,
    "content": "먼 산 언저리 마다",
    "password": "1234",
    "imgUrl": "upload/9c08ec98d98e4281a5faea236e67a6a8.png",
    "writer": "백현명",
  },
```

2타입
```
 {
    "content": "먼 산 언저리 마다",
    "password": "1234",
    "imgUrl": "upload/9c08ec98d98e4281a5faea236e67a6a8.png", (필수 아님)
    "writer": "백현명"
  },
```
3타입
```
 {
    "password": "1234"
  },
```

## 프로젝트 구성
```
SPARTA_SPRING3
└─src
   └─main
      ├─java
      │  └─com
      │      └─sparta_spring
      │          └─sparta_spring3
      │              │  SpartaSpring3Application.java
      │              │
      │              ├─components
      │              │      Components.java
      │              │
      │              ├─controller
      │              │      BlogController.java
      │              │      CommonController.java
      │              │
      │              ├─domain
      │              │      Blog.java
      │              │      BlogRepository.java
      │              │      RequestBlogDto.java
      │              │      ResponseBlogDto.java
      │              │      Timestamped.java
      │              │
      │              ├─service
      │              │      BlogService.java
      │              │      CommonService.java
      │              │
      │              └─utiles
      │                      InitialComponents.java
      │                      WebConfig.java
      │
      └─resources
          │  application.properties(.gitIfnore)
          │  application.yml
          │
          ├─static
          │  │  index.html
          │  │
          │  ├─images
          │  │      no-image.png
          │  │
          │  └─js
          │          Gruntfile.js
          │          jquery.twbsPagination.js
          │          jquery.twbsPagination.min.js
          │
          └─templates
```


## HTML, Controller, Service, 
- 전형적인 CRUD 형태의 코드들로 이루어져있습니다.

## 라이브러리
⭐️  Paging 처리를 위한 twbspagination 를 사용
```
<div class="paging-div">
  <ul class="pagination" id="pagination">
    
  </ul>
</div>
```
```
  $('#pagination').twbsPagination({
      totalPages: totalPage, // 전체 페이지
      startPage: page, // 시작(현재) 페이지
      visiblePages: 10, // 최대로 보여줄 페이지
      prev: "‹", // Previous Button Label
      next: "›", // Next Button Label
      first: '«', // First Button Label
      last: '»', // Last Button Label
      onPageClick: function (event, page) { // Page Click event
		    	//클릭 이벤트
				console.log("클릭");
      }
  });
```

⭐️alert창을 이쁘게 하고싶어서 SweetAlert2 사용 
```
 Swal.fire("massage");
```

## Linux서버에 이미지 업로드를 위한 WebConfig 설정
⭐️ 전에 스프링부트가 아닌 아파치를 사용했기 때문에 이미지 업로드를 했을 경우 문제가 없었지만 스프링부트는 내장톰캣이기에 이 설정이 필요하다는 걸 삽질을 5시간정도 하고 알아냈다.
```
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:///home/ubuntu/upload/");
    }
}
```

## yml을 이용해 데이터 주입을  InitialConponents.class 에서 관리
⭐️InitialConponents
```
package com.sparta_spring.sparta_spring3.utiles;

import com.sparta_spring.sparta_spring3.components.Components;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;

@Configuration
public class InitialComponents {

    @Bean
    @ConfigurationProperties(prefix = "components")
    public Components components() {
        return new Components();
    }

}
``` 
⭐️ yml  - prod 환경과 local 환경을 나누어 파일업로드할 때 경로를 따로 설정해두었다.
```
components:
  slide: "\\"
  base-url: "\\src\\main\\resources\\static"
  src-url: "images"



---
spring:
  profiles: prod
components:
  slide: "/"
  base-url: "/home/ubuntu"
  src-url: "upload"
```
⭐️Components - 빈관리는  InitialConponents에서 하고있다.
```
package com.sparta_spring.sparta_spring3.components;

public class Components {
    private String baseUrl;
    private String slide;
    private String srcUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public Components setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public String getSlide() {
        return slide;
    }

    public Components setSlide(String slide) {
        this.slide = slide;
        return this;
    }

    public String getSrcUrl() {
        return srcUrl;
    }

    public Components setSrcUrl(String srcUrl) {
        this.srcUrl = srcUrl;
        return this;
    }
}
```

## CommonService 의 fileUpload() 
⭐️ 아마 내장톰켓 문제 때문에 제일 시간이 오래걸렸던 기능이다.
```
package com.sparta_spring.sparta_spring3.service;

import com.sparta_spring.sparta_spring3.components.Components;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CommonService {

    public final Components components;

    public String fileUpload(MultipartFile attcFile) {
        String rtnVal = "";
        try {
            String storedFileName = makeFileName(attcFile);
            String folder = makeDir();

            File f = new File(folder);

            if (!f.exists()) {
                f.mkdirs();
            } //폴더가 존재하지 않으면 경로 생성

            attcFile.transferTo(new File(folder + components.getSlide() + storedFileName));
            rtnVal += components.getSrcUrl() + components.getSlide() + storedFileName;
        } catch (Exception e) {
            rtnVal = "";
        }
        return rtnVal;
    }

    public String makeDir() {
        String folder = "";
        if (components.getSlide().equals("\\")) {
            folder += new File("").getAbsolutePath();
        }
        folder += components.getBaseUrl() + components.getSlide() + components.getSrcUrl();
        return folder;
    }

    public String makeFileName(MultipartFile attcFile) {
        String attcFileNm = UUID.randomUUID().toString().replaceAll("-", "");
        String attcFileOriNm = attcFile.getOriginalFilename();
        String attcFileOriExt = attcFileOriNm.substring(attcFileOriNm.lastIndexOf("."));
        return attcFileNm + attcFileOriExt;
    }
}

```




1. [스프링 시큐리티](https://github.com/whitewise95/TIL/blob/main/Java/Spring/SpringSecurity/SpringSecurity(%EC%8A%A4%ED%94%84%EB%A7%81%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0).md)와 [인증과 인가](https://github.com/whitewise95/TIL/blob/main/Security/%EC%9D%B8%EC%A6%9D%EA%B3%BC%20%EC%9D%B8%EA%B0%80.md)에  대해서도 알게되었다. 기존에 " [스프링 부트와 AWS로 구현하는 웹서비스 따라하기](https://github.com/whitewise95/JPA_JUnitTest_Gradle_Oauth2_Practice) " 라는 책 따라서 한번 구현 해본적이 있지만 이렇게 깊이 공부까지 못했고 나의 기존 서비스에 적용해보고 난 에러들을 눈으로 볼 수 있었다.

```
  첫번째 - 기존의 우분투환경에 이미지를 업로드하는 로직도 png 파일이 깨져서 표시되었다 이런 사소한 부분까지
          시큐리티에서 인가와 인증을 설정 해줘야한다.
          
  두번째 - 모든 API에 대한 인가와 인증관리를 해줘야한다.
```  

2. [스프링 시큐리티를 이용한 JWT](https://github.com/whitewise95/TIL/blob/main/Java/Spring/SpringSecurity/JWT%20%EC%99%80%20Security%20%EC%84%A4%EC%A0%95.md)를 알게 되었다. 지금까지 JWT는 단독으로 사용하는 줄 알았는데 Filter Chain에 포함시킬 Filter를 만들어 인가와 인증을 적용시킬 수 있다.  

3. Ajax에 헤더에 데이터를 태워 통신하는 법을 알게되었다. 토큰을 헤더 담아 서버에서 받는데 Ajax 기본적이 통신만 해보았지 헤더를 설정할 수 있다는걸 알게되었다.  

# #보안할 점 및 문제해결한 부분

### RestFul하게 API설계
RestFul하게 API설계하고 싶어서 느리더라도 천천히 프로그래밍을 했다. 하지만 다 끝나고 생각해보니 계층구도로 URI를 잘못 설계한 느낌이다. 보안을 하고싶다.  
<br>
현재 기술매니저님께 "데이터를 쓰지 않았도 아래 보안할 부분처럼 글 ID를 보내야하나요?" 라고 질문을 남겼는데 답변을 기다리고있다.
```
현재 uri 구성
/comment/{댓글ID}

보안하고싶은 부분
/comment/{글ID}/{댓글ID}
```

### dependensy 버전 문제?!
기존에 JWT 라이브러리를 'com.auth0:java-jwt:3.13.0' 를 써서 JWT를 구현했는데 우분투환경에 배포하면 아래와 같이 에러가 났다.  
<br>
나와 같은 문제를 겪는 사람은 아직 나의 서치능력으론 못찾았지만 group: 'io.jsonwebtoken', name: 'jjwt', version: '0.9.1' 라이브러리로  
변경하여 JWT부분을 다시 구현하였다.
```
Caused by: java.lang.ClassNotFoundException: com.auth0.jwt.interfaces.JWTVerifier
```





# # 추가된 API명세  

### 📍 회원관리 API  

| 기능   | method | url           | request | response |
|------|--------|---------------|---------|----------|
| 회원가입 | POST   | /user/signup  | 타입1     | 타입3      |
| 로그인  | POST   | /user         | 타입2     | 타입3      |  

```JSON
⭐️ 타입1

{
  "usernaem" : " ",
  "password" : " ",
  "passwordCheck" : " ",
  "email" : " "
}
```  

```JSON
⭐️ 타입2

{
  "usernaem" : " ",
  "password" : " "
}
```  

```JSON
⭐️ 타입3

{
  "id" : 
  "content" : ""
  "password" : ""
  "imgUrl" : ""
  "writer" : ""
  "createDate" : ""
  "ModifiedDate" : ""
}
```


### 📍 댓글CURD API  

| 기능    | method | url                   | request | response |
|-------|--------|-----------------------|---------|----------|
| 댓글 조회 | GET    | /comment/{blogId}     |         | 타입5      |
| 댓글 쓰기 | POST   | /comment/{blogId}     | 타입4     | 타입6      |
| 댓글 삭제 | DELETE | /comment/{commentId}  | 타입4     | 타입6      |
| 댓글 수정 | PUT    | /comment/{commentId}  | 타입4     | 타입6      |

```JSON
⭐️ 타입4

Header =  Authorization : "토큰"

{
  "comment" : "sadasd222"
}
```

```JSON
⭐️ 타입5
{
    "createDate": "2022.06.03 11:22:43",
    "modifiedDate": "2022.06.03 11:22:43",
    "id": 4,
    "comment": "214512412",
    "blogId": 1,
    "userId": "admin"
}
```  
```JSON
⭐️ 타입6
    
{
  "statusCode": 200 또는 500,
  "message": "메세지"
}
```

# #스프링 시큐리티를 사용해 회원관리하는 방법을 순서도로 

### 📍 회원가입 순서도  
![success Handler (2)](https://user-images.githubusercontent.com/81284265/171804120-62c32569-1d77-4f6e-8ba7-5249de8f0a14.png)  

### 📍 로그인 순서도
![success Handler (1)](https://user-images.githubusercontent.com/81284265/171804126-ad4d6207-18c5-49a8-b97f-cc33d9307741.png)

### 📍 토큰으로 로그인유지 순서도
![success Handler](https://user-images.githubusercontent.com/81284265/171804128-b8254796-4f4c-4fd6-a9e5-27e482a47771.png)





# #추가된 UI  

### 📍 로그인화면  
![로그인화면](https://user-images.githubusercontent.com/81284265/171799772-09e29d2b-0b20-4fff-acb8-5f645c7bfe99.png)  
### 📍 로그아웃 버튼  
![로그아웃](https://user-images.githubusercontent.com/81284265/171799777-852d9a82-9a36-4b64-8cdd-2094599360a8.png)  
### 📍 댓글 모달 열기 가능  
![화면 캡처 2022-06-03 145403](https://user-images.githubusercontent.com/81284265/171799776-4a082174-8440-4c40-863f-63cb8c4e8cec.png)  
### 📍 댓글CURD  
![댓글남기기](https://user-images.githubusercontent.com/81284265/171799800-21b372b8-db04-4f7a-aa01-6d24cbf4ab05.png)  

# #시큐리티 와 JWT 로직을 위한 생성된 구조
```
프로젝트\SRC\MAIN\JAVA\COM\SPARTA_SPRING\SPARTA_SPRING3\SECURITY
│  FilterSkipMatcher.java
│  FormLoginSuccessHandler.java
│  UserDetailsImpl.java
│  UserDetailsServiceImpl.java
│  WebSecurityConfig.java
│
├─filter
│      FormLoginFilter.java
│      JwtAuthFilter.java
│
├─jwt
│      HeaderTokenExtractor.java
│      JwtDecoder.java
│      JwtPreProcessingToken.java
│      JwtTokenUtils.java
│
└─provider
        FormLoginAuthProvider.java
        JWTAuthProvider.java

```



