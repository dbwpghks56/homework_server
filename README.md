# Homework_server

### Front Url
https://homework-web.netlify.app

### Technologies used:

• Kotlin  
• Spring Boot 2.7.13    
• Swagger 3.0        
• OkHttp   

### OkHttp      
• OEmbed 를 이용해 사용자에게 받은 url에 대한 정보를 얻어야함으로 OkHttp 를 이용해 OEmbed에 요청을 보냅니다.    
• OEmbed가 가지고 있는 url 정보들을 이용하기 위해서 OEmbed Provider 에 대한 정보를 조회해 가지고 있습니다.

### Swagger 3.0

Swagger 를 추가해 Api 에대한 명세를 쉽게 눈으로 확인할 수 있도록 만듭니다.     
@shema @Tag 등을 이용해서 swagger 에 대한 명세서를 더욱 구체적으로 작성합니다.

## Installation

```bash
$ git clone https://github.com/dbwpghks56/homework_server.git
```

## Running the app

```
HomeworkServerApplication.kt
```
접근한 뒤 main 을 실행해주면 됩니다. <br>
build gradle 이 필요한 경우도 있습니다.
