# #목차
1) 1.0 버전
   - [프로젝트 소개](#1-프로젝트-소개)
   - [프로젝트 목적](#2-프로젝트-목적)
   - [프로젝트 개요](#3-프로젝트-개요)
   - [개발 환경](#4-개발-환경be)
   - [와이어프레임](#5-와이어프레임)
   - [API 설계](#6-api-설계)
   - [ERD](#7-erd)
   - [기여도](#8-)

2) COMMENT
   - [1.0버전 COMMENT](#10버전-comment)

<br>
<br>

# 1. 프로젝트 소개

본인의 개성을 더해줄 버킷리스트 작성!!

<br>
<br>

# 2. 프로젝트 목적

프론트개발자와 백개발자가 5주차까지 배운 내용을 기반으로 협력을 통해 개발하는 프로젝트

<br>
<br>

# 3. 프로젝트 개요

다들 목표를 말만하고 못지키느라 힘드시죠?? 작심삼일은 여기서 해결하세요!

<br>
<br>

# 4. 개발 환경(BE)  
> 언어
- [java](https://github.com/whitewise95/TIL/tree/main/Java)
  
> 프레임워크
- [스프링프레임워크](https://github.com/whitewise95/TIL/tree/main/Java/Spring)
  - [스프링 시큐리티](https://github.com/whitewise95/TIL/tree/main/Java/Spring/SpringSecurity)
  
> 서버환경
-  [AWS EC2 (Ubuntu 22.04)](https://github.com/whitewise95/TIL/tree/main/AWS/EC2)
  
> 형상관리 및 툴 그리고 DB
- [소스트리](https://www.sourcetreeapp.com/).
- [github](https://github.com/whitewise95).
- [RDS(MySQL)](https://github.com/whitewise95/TIL/tree/main/AWS/RDS)

> 라이브러리(gradle)
- lombok
```
    implementation 'org.projectlombok:lombok:1.18.22'
    compileOnly 'org.projectlombok:lombok'
    annotationProcessor 'org.projectlombok:lombok'
    testCompileOnly 'org.projectlombok:lombok:1.18.22'
    testAnnotationProcessor 'org.projectlombok:lombok:1.18.22'
```
- mysql
```
    runtimeOnly 'mysql:mysql-connector-java'
```
- H2
```
    runtimeOnly 'com.h2database:h2'
```
- security
```
implementation 'org.springframework.boot:spring-boot-starter-security'
```
- jwt
```
 implementation group: 'io.jsonwebtoken', name: 'jjwt', version: '0.9.1'
```
  
<br>
<br>


# 5. 와이어프레임

![화면 캡처 2022-06-10 164721](https://user-images.githubusercontent.com/81284265/173017207-b5c4bb29-da04-4b69-b7bf-ffcf3ef53c45.png)

<br>
<br>

# 6. API 설계
### 로그인 API  

![화면 캡처 2022-06-10 165922](https://user-images.githubusercontent.com/81284265/173019366-60bfcff1-3ff1-4eca-bce1-733fd1dc3a72.png)  

### 버킷리스트 CURD
![화면 캡처 2022-06-10 170256](https://user-images.githubusercontent.com/81284265/173020012-c9b1acba-ab3d-49bf-b387-3e368f01798a.png)

<br>
<br>

# 7. ERD
![화면 캡처 2022-06-10 164751](https://user-images.githubusercontent.com/81284265/173017171-f9becb62-d4ba-48a1-bbda-5d4f3349e404.png)

<br>
<br>

# 8. BE 기여도(객관적) 총 3 명
- 회원가입 - 100%
- 아이디중복검사 - 100%
- 닉네임 중복검사 - 0%
- 로그인(시큐리티) - 50%
- 게시글 작성 - 0%
- 게시글 수정 - 0%
- 게시글 삭제 - 0%
- 게시글 조회 - 0%
- 프로젝트 초기 세팅 - 100%
- 서버 배포 - 100%
- 형상관리 - 50%
- 프로젝트 전체 중 50% 기여


<br>
<br>


# #COMMENT
## 1.0버전 COMMENT

### 1) 힘들었던점 
>  BE서버와 FE서버가 따로 있어 CORS문제를 많이 겪었다. 
- 1-1) 시큐리티를 사용했을 땐 SecurityConfig에 CORS를 설정해줘야한다.
  - [시큐리티 CORS설정](https://github.com/whitewise95/TIL/blob/main/Java/Spring/SpringSecurity/%EC%8A%A4%ED%94%84%EB%A7%81%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0%20CORS%EC%84%A4%EC%A0%95.md)
- 1-2) 시큐리티를 사용하지 않을 땐 WebConfig를 만들어 CORS를 설정해줘야한다.
  - [CORS설정](https://github.com/whitewise95/TIL/blob/main/Java/Spring/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8%20CORS%20%EC%84%A4%EC%A0%95.md) 
- 1-3) 프론트와 서버가 함께 있을 땐 시큐리티를 사용해도 세션쪽 문제를 겪지 않았는데 CORS 때문에 세션을 받으려면 설정해줘야하는 것들이 많다.
  - [스프링시큐리티에서 세션 사용하기](https://github.com/whitewise95/TIL/blob/main/Java/Spring/SpringSecurity/%EC%8A%A4%ED%94%84%EB%A7%81%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0%20%EC%84%B8%EC%85%98%20%EC%9D%B4%EC%9A%A9%ED%95%98%EA%B8%B0.md) 
  - [CORS문제 세션차단 해결하기](https://github.com/whitewise95/TIL/blob/main/Java/Spring/SpringSecurity/%EC%8A%A4%ED%94%84%EB%A7%81%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0%EC%97%90%EC%84%9C%20%EC%84%B8%EC%85%98%EC%B0%A8%EB%8B%A8%20%20CORS%20%EB%AC%B8%EC%A0%9C%20%ED%95%B4%EA%B2%B0%ED%95%98%EA%B8%B0.md)

- 처음엔 세션문제 때문에 해결하지 못해서 jwt로 넘어가 jwt를 사용하기로 하였다.

### 2) 성장한 점 
- 2-1) 협력을 한다는게 정말 어렵다는 것을 알게 되었다. 
  - 개발자로 일했을 땐 모르게 많아 그냥 말을 듣고 지시에만 개발을 했지만 같은 위치에서 협력하여 개발하는건 의견 차이도 많고 합의점을 찾는게 힘들었 던 것 같다.
- 2-2) 상대방을 존중하는 방법을 배웠다.
  - 처음에는 내가 맞다는 씩으로 얘기를 했다면 내가 틀린 점 또는 정확하지 않을 수도 있으니 나는 이렇게 생각하고 있는데 이 부분은 어떻게 생각하는지 여쭤보는 방식으로 말하게 된 거같다.

- 2-3) 프론트엔트 개발자와 협력하는 방법을 알게되었다.
  - 물론 프론트엔드 개발자도 잘하지만 내가 이런 API를 만드려고하는데 요청값은 이렇게 줘야하고 응답값은 프론트단에서 이렇게 사용하도록 이렇게 내려 줄 예정인데 괜찮은지 협의하는 과정을 알게되었다.

- 2-4) 프로젝트 초기 설계 및 형상관리 브랜치 설계등에 자심감을 얻게 되었다.
  - 프로젝트 초기에 gradle 및 공동으로 사용하는 부분을 먼저 설계하는 방법에 익숙해졌고 기존에 협업툴 소스트리 기본 사용법은 익숙했지만 병합은 안해보고 브랜치 나누는 설계도 해보지않아 이번 기회에 익숙해졌다. 

### 3) Develop 계획
1. view단을 좀 더 나의 스타일로 변경하고 싶어서 변경해 볼 예정이다.
2. 세션방식으로 변경해서 시큐리티를 이용해 볼 예정이다.
 
