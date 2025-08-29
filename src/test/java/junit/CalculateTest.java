package junit;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@DisplayName("junit.Calculator Test")
class CalculateTest {
	private final Calculator calculator = new Calculator();

	@Test
	@Fast
	@DisplayName("ὣ")
	void addition() {
		Assertions.assertEquals(2, calculator.add(1, 1));
	}

	@Test
	@Disabled
	void failsDueToUncaughtException() {
		// 테스트 코드내에서 예외가 발생하면, 테스트가 실패한다.
		calculator.divide(1, 0);
	}

	@Test
	void testExpectedExceptionIsThrow() {
		// 아래에 있는 assertion이 IllegalArgumentException을 던지고 있기 때문에, 아래 assertion은 성공한다.
		// 또한 내부에서 발생한 예외를 반환한다.
		IllegalArgumentException expectedException = assertThrows(IllegalArgumentException.class, () -> {
			throw new IllegalArgumentException("expected message");
		});
		assertEquals("expected message", expectedException.getMessage());

		// assertThrows는 서브 클래스 타입의 예외도 검증하기 때문에 해당 assertion은 성공한다.
		assertThrows(RuntimeException.class, () -> {
			throw new IllegalArgumentException("expected message");
		});

	}

	@Test
	void testExpectedExceptionIsThrown() {
		// 정확한 타입의 검증은 성공한다.
		IllegalArgumentException exception =
			assertThrowsExactly(IllegalArgumentException.class, () -> {
				throw new IllegalArgumentException("expected message");
			});
		assertEquals("expected message", exception.getMessage());

		// 정확한 타입이 아니기 때문에 assertion에 실패한다.
		assertThrowsExactly(RuntimeException.class, () -> {
			throw new IllegalArgumentException("expected message");
		});

	}


}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Tag("junit.Fast")
@interface Fast {}