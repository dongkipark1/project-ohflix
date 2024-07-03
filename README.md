# 🚀 "OHFLIX" - 영상 스트리밍 플랫폼


<p align="center">
  <img src="https://github.com/chugue/project-ohflix/assets/30003848/3ba28c1a-6ab4-444a-8832-e07f103bfe85)"  style="width: 50%; height: auto;"/>
</p>



<br>

# 👉 영상 스트리밍 플랫폼
> ### 개발기간: 2024.06.12 ~ 2024.07.03

<br>

# 👉 깃허브 주소
  
> #### 애플리케이션 서버          : [https://github.com/chugue/ropa-admin](https://github.com/chugue/ropa-admin)<br>
> #### 인코딩 / 라이센스 서버     : [https://github.com/chugue/ropa-flutter-project](https://github.com/chugue/ropa-flutter-project)<br>
<br>

# 👉 개발팀 소개


<table style="width:100%; text-align:center;">
  <tr>
    <th style="text-align:center;">김성훈(팀장)</th>
    <th style="text-align:center;">박동기</th>
    <th style="text-align:center;">양승호</th>
    <th style="text-align:center;">공지영</th>
    <th style="text-align:center;">최윤정</th>
  </tr>
  <tr>
    <td style="text-align:center; vertical-align:middle;"><img width="160px" src="https://avatars.githubusercontent.com/u/30003848?v=4" /></td>
    <td style="text-align:center; vertical-align:middle;"><img width="160px" src="https://avatars.githubusercontent.com/u/153582240?v=4" /></td>
    <td style="text-align:center; vertical-align:middle;"><img width="160px" src="https://avatars.githubusercontent.com/u/97007464?v=4"/></td>
    <td style="text-align:center; vertical-align:middle;"><img width="160px" src="https://avatars.githubusercontent.com/u/52162820?v=4" /></td>
    <td style="text-align:center; vertical-align:middle;"><img width="160px" src="https://avatars.githubusercontent.com/u/129649095?v=4" /></td>
  </tr>
  <tr>
    <td style="text-align:center; vertical-align:middle;"><a href="https://github.com/chugue">@chugue</a></td>
    <td style="text-align:center; vertical-align:middle;"><a href="https://github.com/dongkipark1">@dongkipark1</a></td>
    <td style="text-align:center; vertical-align:middle;"><a href="https://github.com/LifeIsOne">@LifeIsOne</a></td>
    <td style="text-align:center; vertical-align:middle;"><a href="https://github.com/Kongjiyoung">@Kongjiyoung</a></td>
    <td style="text-align:center; vertical-align:middle;"><a href="https://github.com/yunchocopie">@yunchocopie</a></td>
  </tr>
</table>


<br>

# 👉프로젝트 소개 (핵심로직 설명)
> #### 오플릭스는 영상 스트리밍 서비스를 제공하는 플랫폼입니다.
> #### 사용자는 서비스를 구독하고, 오플릭스는 사용자에게 암호화된
> #### 영상을 DASH 프로토콜로 네트워크 상황에 따라 품질을 다르게 전송하여
> #### 끊김이 없는 영상 서비스를 제공합니다.

<br>


| 시연영상 링크 ( 사진 클릭 👇👇) |
|:---------------------------:|
| [![시연02](https://img.youtube.com/vi/3YMpCAriG0Y/0.jpg)](https://www.youtube.com/watch?v=3YMpCAriG0Y) |


<br>

# 👉 오플릭스 PPT 발표자료
[오플릭스 PPT - pdf](https://github.com/user-attachments/files/16082996/OHFLIX-PPT.pdf)

<br>
<br>

# 👉 Stacks

![기술스택](https://github.com/chugue/project-ohflix/assets/30003848/b169db7a-e2a7-46d2-9d6c-7eda6fe892d7)



### Communication

![Slack](https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=Slack&logoColor=white)
![Notion](https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=Notion&logoColor=white)

<br>

# 👉 Dependencies
```java
dependencies {
    implementation 'com.github.iamport:iamport-rest-client-java:0.1.6' // iamport
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-mustache'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    compileOnly 'org.projectlombok:lombok'
    developmentOnly 'org.springframework.boot:spring-boot-devtools'
    runtimeOnly 'com.h2database:h2'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testImplementation 'org.springframework.restdocs:spring-restdocs-mockmvc'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
    /////////// 추가 의존성 //////////
    implementation 'org.springframework.boot:spring-boot-starter-validation'
    implementation 'org.springframework.boot:spring-boot-starter-aop'
    implementation group: 'org.apache.commons', name: 'commons-lang3', version: '3.0'
    implementation group: 'com.auth0', name: 'java-jwt', version: '4.3.0'
    implementation group: 'org.mindrot', name: 'jbcrypt', version: '0.4'
    implementation 'com.google.code.gson:gson:2.10.1'
    implementation group: 'org.qlrm', name: 'qlrm', version: '4.0.1'
    runtimeOnly 'com.mysql:mysql-connector-j'
    implementation 'commons-codec:commons-codec:1.15'
    implementation 'org.springframework.boot:spring-boot-starter-data-redis'
    implementation 'org.springframework.session:spring-session-data-redis'
    implementation 'io.github.cdimascio:java-dotenv:5.2.2'
}
```

<br>

# 👉 테이블 설계
![ERD구조](https://github.com/chugue/project-ohflix/assets/30003848/2aebd0c4-c789-4a7a-8572-8e7b7adeb784)


<br>


# 👉 팀 블로깅  
[팀 블로그 링크](https://www.notion.so/stephenkiim/23dafc6dac1649318bc1bc1ece61c2fd)

<br>
<br>

# 👉 프로젝트 주요 기능 정리
* #### Redis 세션 인증
* #### 카카오 OAuth 인증
* #### 로그인 인터셉터 - 세션 기반
* #### 영상 CRUD 구현
* #### DASH 프로토콜 영상 재생
* #### 영상 암호화 패키징 - Shaka Packager
* #### 인코딩 / 라이센스 서버 구축 (DRM)
* #### 카카오 페이 결제
* #### 아임포트 카드 결제
* #### 비밀번호 암호화 - Bcrypt
* #### AI 추천 컨텐츠 - OpenAI
* #### 영상 시청 중단점 프로그레스 바
* #### 영상 찜하기
* #### 영상 좋아요
* #### 매출 페이지 chart.js 표현
* #### 키보드 입력 동시 검색기능
* #### AWS S3 스토리지 서버 구축
<br>
<br>


# 👉 핵심 시나리오 시연
### 🔹브랜드 아이템 등록

![브랜드 아이템 등록](https://github.com/chugue/ropa-admin/assets/30003848/acd9e3df-5eee-44b7-b831-b508baeef10d)

<br>

### 🔹 목록 보기
![홈 목록보기](https://github.com/chugue/ropa-admin/assets/30003848/dd09bfc5-c139-400c-a23e-13713717d937)

<br>

### 🔹 아이템 상세보기
![녹화_2024_05_24_10_57_37_548](https://github.com/chugue/ropa-admin/assets/30003848/6ce1593a-a75f-40c7-87de-4216d558b71a)

<br>

### 🔹 장바구니 추가
![녹화_2024_05_24_10_59_38_776](https://github.com/chugue/ropa-admin/assets/30003848/dcb75176-c179-43e4-9536-f1fdb0408040)

<br>

### 🔹 배송지 입력 / 결제
![녹화_2024_05_24_11_01_15_270](https://github.com/chugue/ropa-admin/assets/30003848/6eacc1fe-a391-450d-8efd-3f30a64faa83)

<br>

### 🔹 크리에이터 지원하기 
![녹화_2024_05_24_11_05_38_97](https://github.com/chugue/ropa-admin/assets/30003848/78555567-da40-4b26-a99a-66291fcc11d3)

<br>

### 🔹 크리에이터 승인하기
![녹화_2024_05_24_11_09_41_220](https://github.com/chugue/ropa-admin/assets/30003848/f487a0fe-7ba2-4444-b6f4-915612222410)

<br>

### 🔹 크리에이터 코디 메인사진 등록하기
![녹화_2024_05_24_11_11_54_847](https://github.com/chugue/ropa-admin/assets/30003848/a5a2596a-29bf-4d38-a36e-99c4a6a123cb)

<br>

### 🔹 크리에이터 코디에 아이템 연결하기
![녹화_2024_05_24_11_12_24_872](https://github.com/chugue/ropa-admin/assets/30003848/71935699-78e8-4b78-a86e-4fe234dd5f20)

