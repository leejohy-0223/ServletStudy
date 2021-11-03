# [Servlet Study](https://www.youtube.com/watch?v=drCj2k50j_k&list=PLq8wAnVUcTFVOtENMsujSgtv2TOsMy8zd)
- 유튜브 뉴렉쳐님 Servlet 강의 실습
  <br><br>
  
### 개발 환경
- 기본 환경
    - IDE: Eclipse
    - OS: Mac OS X

### 실습 내용
1. Servlet(웹 서버 응용 프로그램) 구현(IDE 없는 상태)
   - 모든 서블릿은 WAS에 의해서 실행됨.
   - WAS는 HttpServlet 추상 클래스를 확장한 Servlet을 실행하게 되며, 정의된 service 함수를 반드시 구현해야 함.
   - Tomcat에 의해서 Servlet 코드가 실행되도록 매핑하는 작업을 해줘야 한다. web.xml 내에 이를 작성해준다.
   - 그리고 web-inf/classes directory에 생성된 서블릿의 클래스 파일을 넣어준다.
   - 일일이 설정해줘야 하는 번거로움이 있다.

2. IDE 환경에서 구현(Eclipse)
   - 코드 수정 + 컴파일 + 배포 + 톰캣 서버 재시작 + 브라우저로 요청하는 과정을 쉽게 도와주는 IDE를 사용하였다.
   - 마찬가지로 class 파일 매핑을 위해 web.xml이 필요하다.

3. 어노테이션을 이용한 URL 매핑
   - 외부 파일(web.xml) 말고, annotation으로 매핑하는 방법이다.
   - 이 방법으로 객체에 붙어있는 주석 정보를 활용할 수 있다.
   - 훨씬 가독성이 좋아진다.
   
4. response setting
   - 정상적인 response를 브라우저로 보내기 위해 response의 메서드를 이용하여 인코딩 방식 및 컨텐츠 타입을 지정해준다.

5. request setting
   - 사용자의 입력(요청)을 처리하는 방법을 알아본다. request.getParameter를 통해 쿼리스트링으로 전달된 값을 추출할 수 있다.
   - 입력할 내용이 많을 경우 POST 요청이 효율적이다. 일정한 양식을 통해 내용을 전달받을 수 있으며, GET과 비교하여 url에 정보가 노출되지 않으므로 보안상 더 좋다.

6. Servlet filter
   - 클라이언트로부터의 요청 사항 내용이 깨지게 되는 경우가 있다. 
   - 서버쪽의 인코딩 방식 문제일 수 있는데 이 경우 일반적으로 톰캣 서브의 설정을 변경하면 안된다.(server.xml) 해당 설정변경을 통해 다른 사이트도 영향을 받으면 안되기 때문이다.
   - WAS는 사용자 요청이 들어오면 servlet container로부터 적절한 소프트웨어(servlet)를 실행해준다. 즉 WAS는 여러 어플리케이션을 서비스하는 하나의 컨테이너이다.
   - 특정 요청을 위해 WAS 자체의 설정을 바꾸기에는 부담스럽다. 따라서 request 단위로 적용이 가능한데, 이 때 filter를 이용할 수 있다.
   - Filter를 구현 후 doFilter 메서드를 오버라이딩 하여 사용할 수 있다.

7. 상태 유지를 위한 방법 - application, session, cookie
   - application 객체 : 상태를 저장하기 위해 해당 객체를 사용한다. 하지만 객체 사용하지 않는 시점에서 메모리에서 사라지게 된다.
      - 서블릿 컨텍스트 : 사전 그대로 '문맥'이며, 책갈피로 생각하면 된다. 서블릿들이 서로간에 문맥을 이어가기 위한 저장소라고 생각하면 된다.
      - 이를 application 저장소라고도 한다.


   - Session : application과 결과는 동일하나, session은 지역 범위 내에서만 사용이 가능하다. 즉 현재 접속한 사용자를 의미하며, 사용자 별로 공간이 달라질 수 있다는 것을 의미한다.
      - 브라우저가 달라지면 다른 사용자로 인식한다. 따라서 저장된 value를 공유하지 못한다.
      - WAS는 sid를 통해 사용자를 구별할 수 있다. 즉 WAS 내의 Session 영역을 servlet이 끝날 때 사용자에게 sid로 부여한다. 이후 접근 시 해당 값을 통해 Session 영역에 접근할 수 있게된다.
      - http request 시, Cookie에 부여된 SessionID를 함께 가져가서 식별할 수 있도록 한다.
      - Session method를 통해 유지 시간 등을 설정할 수 있다.

   - Cookie : 상태 값을 클라이언트(브라우저)에서 가지고 있는 걸 쿠키라고 한다.
      - 쿠키 값은 반드시 url을 탈 수 있는 문자형태로 보내야 한다. 따라서 String.valueOf를 통해 String으로의 변환이 필요하다.
      - json / xml을 이용하여 다양한 객체를 문자열로 만들어 쿠키 저장이 가능하다.
      - 쿠키 설정 시 setPath 메서드를 통해 url을 지정하여 해당하는 서블릿에만 전달되도록 범주 지정이 가능하다.

   - 정리 : 
      - application : 전역 범위에서 사용하는 저장 공간 / WAS가 시작해서 종료할 때 까지 사용 가능 / WAS의 메모리에 저장됨
      - Session : 세션 범위에서 사용하는 저장 공간 / 세션이 시작해서 종료할 때 까지 사용 가능 / WAS의 메모리에 저장됨
      - Cookie : Browser별 지정한 path 범주 공간 / Browser에 전달한 시간부터 만료시간까지 / Browser의 메모리 또는 파일에 저장됨

8. Redirect
   - response.sendRedirect를 통해 브라우저가 페이지를 요청한 것처럼 효과를 낼 수 있다.

9. 동적인 페이지(서버 페이지)의 필요성 
   - 이미 만들어진 페이지가 아닌, 요청 시의 내용을 동적으로 만들어서 출력해야 한다.
   - 이를 동적인 페이지라 한다.
   - 최초 1회 html을 서블릿 내에서 작성한다. html의 모든 문장 앞에 out.write()를 추가하여, 동적으로 html 페이지를 생성한다.
   - 매우 노가다이다. JSP 또는 템플릿 엔진을 통해 이를 용이하게 할 수 있다.






