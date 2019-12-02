# Spring
Spring을 공부하는 Repository
  
## Spring
* **POJO (Plain Old Java Object) 방식의 프레임워크**  
    특정한 인터페이스를 구현하거나 상속을 받을 필요가 없어 기존에 존재하는 라이브러리들을 지원하기 용이하고 객체가 가볍다.
  
* **제어 반전 IoC (Inversion of Controll)**  
    컨트롤의 제어권이 사용자가 아니라 프레임워크에게 있어 필요에 따라서 Spring이 사용자 코드를 호출
  
* **의존성 주입 DI (Dependency Injection)**  
    각각의 계층이나 서비스 간에 의존성이 존재할 경우 프레임워크가 연결해줌
  
* **관점 지향 프로그래밍 AOP (Aspect Oriented Programming)**  
    트렌잭션이나 로깅, 보안 여러 모듈에서 공통적으로 사용하는 기능을 분리하여 관리 가능

### 모듈
* **제어 반전 컨테이너**  
    자바의 `Reflection` 을 이용해 객체의 생명주기를 관리하고 `의존성 주입 (Dependency Injection)`를 통해 각 계층이나 서비스간의 의존성을 맞춰줌, 주로 XML파일에 의해 설정되고 수행됨
  
* **관점 지향 프로그래밍 프레임워크**  
    `AspectJ`를 내부적으로 사용 가능
    Spring에서 자체적으로 실행시에 조합하는 방식 또한 지원
  
* **데이터 액세스 프레임워크**  
    `JDBC`, `iBatis(MyBatis)`, `Hibernate` 와 같은 프레임워크를 지원
  
* **트랜젝션 관리 프레임워크**  
    XML 설정 파일을 이용한 방식과 프로그래밍을 통한 방식을 지원
  
* **MVC 패턴**  
    `DispatcherServlet`이 `Controller`의 역할을 담당
  
* **배치 프레임워크**  
    `Quartz` 기반으로 특정 시간대에 실행하거나 대용량 자료를 처리하는데 쓰이는 일괄 처리를 지원
### Spring의 MVC
Model -> DAO, VO(DTO), Service  
View -> JSP  
Controller -> Controller(Servlet)

#### Servlet
Web.xml에 매핑된 자바 파일을 `Servlet`으로 변환 시키고 `Tomcat`은 이 `Servlet`을 실행  
**Servlet으로 변환되기 위한 조건**  
* `Tomcat`에서 만들어둔 `HttpServlet`을 상속  
* `Get`방식과 `Post`방식을 처리하기 위한 `doGet(HttpServletRequest, HttpServletResponse)`, `doPost(HttpServletRequest, HttpServletResponse)` 메서드가 있어야함  
  
예)  
``` java
@WebServlet("/Sample") // Annotation을 사용해 Web.xml에 매핑하지 않아도 됨
public class Sample extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served - ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse) throws ServletException, IOException {
        doGet(request, response);
    }
}
```
  
#### DAO (Data Acess Object)
`DAO`는 DB에 있는 데이터에 접근하는 `transaction` 객체이다.  
`DAO`는 보통 `Database`하나당 하나씩 가지고 있다. (`Database`로 부터 매번 Driver를 로드하고 Connection하지 않기 위함)  
`Domain Logic`으로부터 `Persistence` 계층을 감추기 위해 사용함  
**Persistence 계층** : `Database`에 `CRUD`를 하는 계층  
  
#### VO (Value Object)
일반적으로 로직을 지니지 않고 순수한 데이터만 지닌 객체이다.  
`getter` 메서드만 지니고 있다.  

#### DTO (Data Transfer Object)
기본적인 것은 `VO (Value Object)`와 같다.  
하지만 `DTO`는 `setter` 메서드를 지니고 있어 값을 수정할 수 있다.  
`to...`와 같이 데이터를 Entity로 변경하기 위한 메서드 또한 지닌다.    

#### Entity
실제 `Database`의 `Table`과 매칭될 객체이다.  
외부에서 `Entity`객체의 `getter`를 호출하지 않도록 `Entity`내부에서 필요한 `Logic`을 구현한다.  
`Domain Logic`만을 지니고 있어야 한다.  
  
#### MVC를 처리하는 과정
1. `Client`가 `Server`에 `Request`를 하면 `DispatcherServlet`이라는 객체가 요청을 받는다.  
2. `HandlerMapping`에게 어떤 컨트롤러에게 요청 정보를 전달할지 물어본다.  
3. `Mapping`된 `Controller`가 있다면 데이터를 전달  
4. `Controller`에서는 요청을 처리할 `Service`를 `의존성 주입(Dependency Injection)`을 받아 로직을 `Service`에게 위임한다.  
5. `Service`에서는 요청에 필요한 작업을 수행하며 DB관련된 처리는 `DAO`에 위임  
6. `DAO`는 `iBatis(MyBatis)`, `Hibernate`와 같은 것들을 이용하여 DB에서 정보를 받아 `Service`에게 전달한다. (보통 이 결과를 `VO(DTO)`에 담는다.)  
7. 작업을 끝낸 후 `Service`가 `Controller`에게 결과를 전달.  
8. `Controller`는 어떤 `View`에 정보를 띄울 것인지 `DispatcherServlet`에 알려준다.  
9. `DispatcherServlet`은 `ViewResolver`에게 정보를 넘긴다.  
10. `ViewResolver`는 해당되는 JSP파일을 찾아 `DispacherServlet`에게 전달한다.  
11. `DispatcherServlet`은 `View`에게 `Render`명령을 내리고 `View`는 관련 동작을 수행한다.  
12. `DispatcherServlet`이 `Client`에 `Render`된 `View`를 보여준다.  
  
## Issues
틀린점이 있으면 Issues로 부탁드립니다.  
