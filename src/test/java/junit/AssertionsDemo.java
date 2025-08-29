package junit;

import static java.time.Duration.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CountDownLatch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AssertionsDemo {

	private final Calculator calculator = new Calculator();

	private final Person person = new Person("Jane", "Doe");

	@Test
	void standardAssertions() {
		Assertions.assertEquals(2, calculator.add(1, 1));
		Assertions.assertEquals(4, calculator.multiply(2, 2),
			"The optional failure message is now the last parameter");

		// 실패 시 보여지는 message이다.
		// string 타입을 사용하면 성공 시에도 해당 메서드를 호출한다.
		// Supplier를 사용하게 되면 지연 평가가 일어나, 테스트가 실패하면 실패 메시지를 생성하지 않아 낭비를 없앨 수 있다.
		assertTrue('a' < 'b', () -> generateFailureMessage('a', 'b'));

	}

	@Test
	void groupedAssertions() {
		// 모든 Assert가 실핻된다. 하나라도 실패하면 해당 Assert는 실패한다.
		assertAll("person",
			() -> assertEquals("Jane", person.firstName()),
			() -> assertEquals("Doe", person.lastName())
		);
	}

	@Test
	void dependentAssertions() {
		// 코드 블록안에서 assert가 실패하면, 이후 코드 블록은 스킵된다.
		assertAll("properties",
			() -> {
				String firstName = person.firstName();
				assertNotNull(firstName);
				// 이전 assert가 성공 했을 경우에만 호출된다.
				assertAll("first name",
					() -> assertTrue(firstName.startsWith("J")),
					() -> assertTrue(firstName.endsWith("e"))
				);
			},
			() -> {
				// 그룹화된 Assertion, 그래서 firstname assertion 결과에 상관 없이 독립적으로 수행된다.
				String lastName = person.lastName();
				assertNotNull(lastName);
				// Executed only if the previous assertion is valid.
				assertAll("last name",
					() -> assertTrue(lastName.startsWith("D")),
					() -> assertTrue(lastName.endsWith("e"))
				);
			}
		);
	}

	@Test
	void exceptionTesting() {
		Exception exception = assertThrows(ArithmeticException.class, () ->
			calculator.divide(1, 0));
		assertEquals("/ by zero", exception.getMessage());
	}

	@Test
	void timeoutNotExceeded() {
		// 테스트가
		assertTimeout(ofMinutes(2), () -> {
			// 2분 이내에 실행되면 성공
			Thread.sleep(100);
		});
	}


	@Test
	void timeoutNotExceededWithResult() {
		// 해당 테스트가 성공하면 Supplier에서 리턴한 오브젝트를 획득할 수 잇다.
		String actualResult = assertTimeout(ofMinutes(2), () -> "a result");
		assertEquals("a result", actualResult);
	}

	@Test
	void timeoutNotExceededWithMethod() {
		// 메서드 참조를 호출하여 오브젝트를 획득할 수 있다.
		String actualGreeting = assertTimeout(ofMinutes(2), AssertionsDemo::greeting);
		assertEquals("Hello, World!", actualGreeting);
	}

	@Test
	void timeoutExceeded() {
		// The following assertion fails with an error message similar to:
		// execution exceeded timeout of 10 ms by 91 ms
		assertTimeout(ofMillis(10), () -> {
			// Simulate task that takes more than 10 ms.
			Thread.sleep(100);
		}, "실패");
	}

	@Test
	void timeoutExceededWithPreemptiveTermination() {
		// The following assertion fails with an error message similar to:
		// execution timed out after 10 ms
		assertTimeoutPreemptively(ofMillis(10), () -> {
			// Simulate task that takes more than 10 ms.
			new CountDownLatch(1).await();
		});
	}


	private static String greeting() {
		return "Hello, World!";
	}

	private static String generateFailureMessage(char a, char b) {
		return "Assertion messages can be lazily evaluated -- "
			+ "to avoid constructing complex messages unnecessarily." + (a < b);
	}

}


record Person(String firstName, String lastName) {}
