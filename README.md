# Spring-Study
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
Model -> DAO, VO(DTO)  
View -> JSP  
Servlet -> Controller  
#### Servlet
Web.xml에 매핑된 자바 파일을 `Servlet`으로 변환 시키고 `Tomcat`은 이 `Servlet`을 실행  
**Servlet으로 변환되기 위한 조건**  
* `Tomcat`에서 만들어둔 `HttpServlet`을 상속  
* `Get`방식과 `Post`방식을 처리하기 위한 `doGet(HttpServletRequest, HttpServletResponse)`, `doPost(HttpServletRequest, HttpServletResponse)` 메서드가 있어야함  
예)  
``` java
@WebServlet("/Sample") // 어노테이션을 사용해 Web.xml에 매핑하지 않아도 됨
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
`DAO`는 DB에 있는 Data에 접근하는 transaction 객체이다.  
Domain Logic으로부터 Persistence 계층을 감추기 위해 사용함
**Persistence 계층** : Database에 CRUD를 하는 계층
  
#### VO (Value Object) or DTO (Data Table Object)
VO 혹은 DTO라고도 부른다.  
한 테이블의 Column들을 멤버변수로 작성한 객체