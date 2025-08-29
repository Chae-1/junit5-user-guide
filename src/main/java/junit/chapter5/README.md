Extension Model
==

확장은 `@ExtendWith` 애노테이션을 통해 선언적으로 등록될 수 있다.

프로그래밍적으로는 `@RegisterExtension`을, 자동으로는 ServiceLoader 매커니즘을 사용한다.

### Declarative Extension Registration

테스트 클래스, 테스트 메서드, 혹은 커스컴 애너테이션에 `@ExtendWith`을 사용해서 하나 이상의 확장을 등록할 수 있다.

Junit 5.8 이후로는, ExtendWith이 테스트 클래스의 생성자, 필드, 라이프사이클 메서드, 테스트 메서드에 적용할 수 있다.


```java

@Test
@ExtendWith(WebServerExtension.class)
void getProductList(@WebServerUrl String serverUrl) {
  WebClient webClient = new WebClient();
  // Use WebClient to connect to web server using serverUrl and verify response
  assertEquals(200, webClient.get(serverUrl + "/products").getResponseStatus());
}

```

만약 위와 같이 WebServerExtension을 테스트 메서드에서 등록하기 위해서 다음과 같이 사용하면된다.

테스트를 실행하기 이전에, web server를 실행시키고, server의 url을 @WebServerUrl 애너테이션이 명시되어 있는 파라미터를 주입한다 가정해보자.

클래스 레벨에 적용한다면 모든 테스트 메서드와 서브 클래스에 적용할 수 있다.

커스텀 애너테이션을 만들어서 확장 모델을 여러 개 사용하는 `@ExtendWith` 애너테이션을 만들어 재사용할 수도 있다.

