## 목차

> - [개요](#프로젝트-개요)
> - [개발진행상황](#개발진행상황)

## 프로젝트 개요

<img alt="cardme 로고" src="https://user-images.githubusercontent.com/33706043/140515262-14d29e79-c3f1-4660-875e-723285c9edcc.png" width="30%">

개발자들의 Github 및 블로그의 여러 프로필을 둘러보다 보면 Git Stat 을 보기 쉽게 디자인해놓은 Svg 들을 간혹 볼 수 있었습니다.  
보통 마크다운이나 블로그의 img 태그에 src 속성으로 API URI를 통하여 서비스를 이용하는데 저 또한 이와 같은 형식으로 자신을 간단하게 소개할 수 있는 카드를 제공하는 API를 만들어 보고자 하였습니다.  
위와 같은 서비스는 보통은 로그인 없이 API를 사용하는 형태이지만 저는 SpringBoot 공부 목적으로 회원 관리 기능을 포함시킨 후 추후 서비스 페이지를 SPA로 구성하고 사용자가 로그인 후 카드생성 기능을 통하여 자신만의 카드를 생성하고 Svg를 제공받도록 하는 것이 목표입니다.

코드는 [**GitHub 에서**](https://github.com/je0ngyun/cardmeBE) 보실 수 있습니다.

## 개발진행상황

Jenkins를 통하여 Docker Image 기반으로 CI/CD 파이프라인을 구축해놓았습니다.
테스트 서버로의 API요청 주소는 다음과 같습니다.  
[**https://www.je0ngyun.kro.kr/cardme/api/v1/card?userId=je0ngyun&cardName=test**](https://www.je0ngyun.kro.kr/cardme/api/v1/card?userId=je0ngyun&cardName=test)

```html
//e.g
<img
  src="https://www.je0ngyun.kro.kr/cardme/api/v1/card?userId=je0ngyun&cardName=test"
/>
```

### 제공된 Svg 예시

<img src="https://www.je0ngyun.kro.kr/cardme/api/v1/card?userId=je0ngyun&cardName=test"/>

## Ing.

1. Spring Security 기반 토큰기반인증방식 적용 완료.
2. 회원관련 API 일부 개발완료.
3. Let's Encrypt를 통한 ssl 적용완료.
4. Svg 카드의 Skill 정보 표현 완료.
