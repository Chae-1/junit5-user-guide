import static org.junit.jupiter.api.Assertions.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@DisplayName("Calculator Test")
class CalculateTest {
	private final Calculator calculator = new Calculator();

	@Test
	@Fast
	@DisplayName("ὣ")
	void addition() {
		assertEquals(2, calculator.add(1, 1));
	}

}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Tag("Fast")
@interface Fast {}