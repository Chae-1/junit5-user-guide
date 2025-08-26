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

### Annotations
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
  - 