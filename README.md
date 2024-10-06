# 프로젝트 개요  ***AI View***

### AI 기반 OCR 기술을 이용한 “B2B 영수증 리뷰 API” 제공 서비스 “AI View”

<aside>
💡

리뷰의 신뢰도 확보를 위한 영수증 인증 기반 리뷰 시스템 구축

</aside>

## 🎯 Target

영수증 리뷰 시스템을 도입하고자 하는 **스타트업 개발자**를 핵심 타겟으로 하고 나아가 인터페이스를 고도화, 컴포넌트 개발을 통해 노코드 플랫폼인 **아임웹, 카페24** 등을 통해 자사몰을 운영중인 분들을 대상으로 서비스를 제공할 예정입니다. 

## 구조

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/60af02e3-3960-4ce1-bd3b-784de6473de2/ff1e42fc-6dd1-4652-80b3-429709c7388f/image.png)

# 설치 방법

## 1. Git Clone + yml 파일

```java
git clone https://github.com/hi-story4/LLM_INNOVATION.git
```

```java
yml 파일을 resource 디렉토리에 넣어줍니다. 
```

**

따로 첨부된 yml 파일을 넣어주세요.

## 2. Gradle 설치 및 Build (환경설정)

반드시 서버를 켜기전에 Gradle을 refresh 해줘야합니다.  (clean build)

다음 명령어를 프로젝트 위치에서 콘솔창에 입력해도 되고 IDE에 따라 직접 refresh 시켜줘도 됩니다.

gradle binary 설치

```java
./gradlew
```

**IDE 별로 명령어 다름.

인텔리제이

```java
 ./gradlew clean build
```

VSC

gradle build

```java
./gradlew jar
```

## 3. Redis 설치 및 실행

운영 체제에 따라 Redis 서버를 설치!!!

터미널을 키고 다음과 같이 입력하여 레디스 서버를 켜줍니다. 

```java
redis-server
```

# 사용방법

postman을 설치

https://www.postman.com/downloads/

아래 API 명세서를 참고하면 어떻게 쿼리를 보내야할지 + Error Code에 대해 자세히 나와있습니다.  

https://documenter.getpostman.com/view/27198897/2sAXxMesk6

바로 연동 가능하도록 아래 Publid workspace 접속하면 바로 postman을 이용해 Test 해볼 수 있습니다.

https://web.postman.co/workspace/LLM_Innovation~841a2be5-b0c7-499e-b5c5-65752a38171b/request/27198897-f8718d2a-fd23-4855-a56b-4fb8dd8ea0f6

추가적으로 API를 한눈에 보기 위해서는 서버를 켜고 아래 링크에 접속하면 Swagger Api 문서를 확인할 수 있습니다. 

http://localhost:8080/swagger-ui/index.html#/

# 예시

`영수증 OCR` API를 이용하여http://localhost:8080/api/v1/receipt  에 

Auth (API Key 방식) 헤더에 X-API-KEY 를 key 값으로, 실제 ClientApiKey값을 value 로 넣고 

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/60af02e3-3960-4ce1-bd3b-784de6473de2/4959f086-6e3d-46ca-8df2-6861ae770fb9/image.png)

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/60af02e3-3960-4ce1-bd3b-784de6473de2/c61f041a-01b9-46bd-a6ff-f13ca40a8a53/image.png)

사진과 RequestBody는  form-data로 다음과 같이 입력해서 요청을 보내면 됩니다. 

**꼭 사진을 다시 넣어줘야합니다. 

> 포스트맨 상에 사진이 있더라도 시간이 지나면 이름만 남고 실제 파일은 안보내지기 때문에 사진 파일은 꼭 다시 넣어줘야합니다.
> 

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/60af02e3-3960-4ce1-bd3b-784de6473de2/ebff532a-429f-4307-b195-ac50a2c84741/image.png)

workspace에서 우측 상단 환경변수 lim env가 잘 설정되어있는지 확인해 주세요

환경변수 설정이 잘 되어있어야 Auth와 header에서 client-id, client-key 가 잘들어갑니다. 

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/60af02e3-3960-4ce1-bd3b-784de6473de2/482a57f8-bb49-41a2-9d10-23fce8d8d8b2/image.png)

만약을 위해서 cliend-id, client-key , admin-id, admin-key 값도 따로 첨부하겠습니다. 

# LLM 활용

영수증 리뷰 시스템을 구축하고자 하는 스타트업 개발자를 위해 Developer 문서와 함께 API를 개발했습니다. 

앞선 `영수증 OCR` API 에서 보낸 사진을 LLM 기반 Key Information Extraction 기술을 활용해 Data를 추출했습니다. 

영수증 정보에서 리뷰 시스템에 필요한 데이터만 가공해서 객체기반으로 안전하게 관리하고 사업자번호, 중복검사, 승인번호에 대한 유효성 검사를 진행 후 저장 & 리턴해주었습니다.
