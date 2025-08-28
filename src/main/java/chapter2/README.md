테스트 작성하기
==
```java
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculateTest {
	private final Calculator calculator = new Calculator();

	@Test
	void addition() {
		assertEquals(2, calculator.add(1, 1));
	}

}
```
- 가장 기본적인 테스트를 작성한 예시이다.

## Annotations
Junit Jupiter는 테스트를 구성하고 프레임워크를 확장하기 위한 애노테이션을 제공한다.

거의 대부분의 코어 애노테이션은 `org.junit.jupiter` 모듈의 `org.junit.jupiter.api` 패키지에 존재한다.

목록은 다음과 같다.

- `@Test`
  - 해당 메서드가 테스트 메서드임을 명시한다.
  - 이전 버전의 @Test 애노테이션과 달리, 어떠한 속성도 정의하지 않는다.
    - Junit Jupiter는 이러한 테스트 확장을 전용 애노테이션을 사용하기 때문이다.
- `@ParameterizedTest`
  - 해당 테스트가 **매개화 테스트**임을 나타낸다.
- `@RepeatedTest`
  - 해당 테스트가 **반복자 테스트**임을 나타낸다.
- `@TestFactory`
  - 해당 메서드가 **동적 테스트**를 위한 테스트 팩토리임을 나타낸다.
- `@TestTemplate`
  - 해당 메서드가 테스트 케이스를 위한 템플릿임을 나타낸다.
- `@DisplayName`
  - 테스트 클래스, 메서드 디스플레이 이름을 정의할 때 사용한다.
- `@BeforeEach`
  - 각 테스트 메서드를 실행하기 전에 실행될 메서드를 나타낼 때 사용한다.
- `@AfterEach`
  - 각 테스트 메서드를 실행한 이후에 실행되는 메서드를 나타낼 때 사용한다.
- `@BeforeAll`
  - 모든 테스트를 실행하기 전에 실행되는 메서드를 나타낼 때 사용한다.
- `@AfterAll`
  - 모든 테스트를 실행한 후에 실행되는 메서드를 나타낼 때 사용한다.
- `@ExtendWith`
  - 선언적으로 확장 모듈을 등록할 때 사용한다.

### Meta Annotations and Composed Annotations
Jupiter의 애노테이션은 메타 애노테이션으로 사용이 가능하다.

애노테이션을 위한 애노테이션으로 활용이 가능하다.

즉, Jupiter에서 제공해주는 애노테이션을 활용하여 커스텀 애노테이션을 정의할 수 있다.

예를 들어, @Tag("fast")라는 Tag 애노테이션을 코드 베이스 전반에 복붙하는 대신, Fast라는 애노테이션을 정의해서 사용할 수 있다.
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Tag("Fast")
@interface Fast {}

@Test
@Fast
void addition() {
  assertEquals(2, calculator.add(1, 1));
}

```
이렇게 작성된 애노테이션은 Junit이 Tag 애노테이션으로 인식하게 된다. 

## Definitions
- Platform Concepts
  - Containers (ex. 테스트 클래스)
    - 테스트 트리 내에 존재하는 노드.
    - 다른 Container를 포함하거나 테스트들이 자식으로서 존재한다.
  - Test
    - 테스트 트리 내에 존재하는 노드.
    - 실행됐을 때 예상되는 행위를 검증하는 노드.(@Test 메서드)
- Jupiter Concepts
  - Lifecycle Method
    - `@BeforeAll`, `@BeforeEach`, `@AfterAll`, `@AfterEach` 애노테이션이 붙은 메서드를 생애주기 메서드라고 부른다.
  - Test Class
    - `@Test` 애노테이션이 붙은 메서드가 하나라도 존재하는 모든 static, top, @Nested 클래스를 Test Class라고 한다.
    - abstract 일 수 없고, 반드시 하나의 생성자를 가져야한다.
    - java record를 지원한다.
    - Container에 해당한다.
  - Test Method
    - `@Test` 관련 애노테이션이 붙어있는 모든 인스턴스 메서드를 Test Method라고 한다.
    - `@Test` 애노테이션을 제외한 나머지 테스트 관련 애노테이션은 테스트 트리를 그룹화하거나 잠재적으로 다른 컨테이너를 생성한다.

## Test Classes and Methods
테스트 메서드와 생애주기 메서드는 현재 테스트 클래스 로컬에 선언할 수 있다.

테스트 메서드는 반드시 아무 값도 반환할 수 없고, abstract 여서는 안된다. (단, @TestFactory 처럼 retrun value를 요구할 경우를 제외한다.)

### Notice
- Class and Method visibility
  - public을 요구하지는 않지만, private이어서는 안된다.
  - 기술적인 이유가 존재하지 않는다면, class, test method, lifecycle method의 modifiter는 public을 사용하지 않는 것이 권장된다.
    - Java 모듈 시스템을 사용할 경우 테스트를 단순화하기 위해서 public으로 만든다.
    - 다른 패키지에 있는 테스트 클래스를 상속해서 사용할 경우.
- Field and method inheritance
  - 테스트 클래스에 있는 필드들은 상속된다.
  - package-private인 경우, 재정의할 수 없기 때문에 그대로 적용된다.

## Display Names
테스트 클래스나 테스트 메서드에 선언할 수 있는 표시되는 이름을 정의할 수 있는 애노테이션.

test runner에 의해 test reports나 ide에 표시되는 이름이다.

### Display Name Generator
@DisplayNameGeneration 애노테이션을 사용하여 설정할 수 있도록 지원한다.

만약, 표시 이름을 변경해야한다면, DisplayNameGenerator API를 구현하면 된다.

## Assertions
Jupiter는 람다와 사용하기 좋은 많은 종류의 assertion method가 함께 존재한다.

모든 Assertions는 org.junit.jupiter.api.Assertions 클래스에 정적 메서드 형태로 존재한다.

Assertions 메서드에는 assertion message를 세 번째 파라미터로 받을 수 있는데, String 또는 Supplier<String>를 받을 수 있다.

### Preemptive Timeouts with assertTimeoutPreemptively
assertTimeoutPreemptively 시리즈는 별도의 스레드를 사용해서 전달한 람다를 호출한다.

람다 내부(executable, Supplier)에서 ThreadLocal과 같은 스토리지를 사용하면 사이드 이펙트를 발생시킬 수 있다.

스프링 프레임워크에서 트랜잭션 테스트를 수행할 때, 테스트 실행 전에 Transaction Status를 현재 스레드에 바인딩 시킨다.

따라서, assertTimeoutPreemptively를 호출할 경우, 스프링이 관리하는 트랜잭션에 참여하는 컴포넌트와 테스트가 관리하는 스레드가 다른 스레드라서, 어떤 작업이 일어나더라도 스프링 관리 트랜잭션이 롤백되지 않을 수 있다.


### Third party Assertions Libraries
Jupiter에 정의되어 있는 Assertions이 충분하지 않은 경우도 존재하고 더욱 강력한 기능을 지닌 Thrid-party 라이브러리가 존재한다.

그래서, Junit Team도 해당 라이브러리들을 사용하는 것을 권장하고 있는데 대표적인 라이브러리는 다음과 같다. 
- AssertJ
- Hamcrest
- Truth

## Assumptions
Assumption은 더 이상 테스트를 진행해봐야 의미가 없는 경우, 사용한다.

현재 런타임 환경에 없는 것을 요구하는 경우, 테스트를 진행하는 것이 의미가 없는데, 이럴 때 사용한다.

## Exception Handling
Jupiter는 예외를 테스트하기 위한 