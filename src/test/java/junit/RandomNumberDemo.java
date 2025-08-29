package junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(RandomNumberExtension.class)
public class RandomNumberDemo {

	// static field에 주입할 수 있다.
	// BeforeAll, AfterEach 라이프 사이클 메서드에 포함된다.
	@Random
	private static Integer randomNumber0;

	// @BeforeEach, @AfterEach 라이프 사이클 메서드에 포함된다.
	@Random
	private int randomNumber1;

	RandomNumberDemo(@Random int randomNumber2) {
		System.out.println("randomNumber2 = " + randomNumber2);
	}

	@BeforeEach
	void beforeEach(@Random int randomNumber3) {
		System.out.println("randomNumber3 = " + randomNumber3);
	}

	@Test
	void test(@Random int randomNumber4) {
		System.out.println("randomNumber4 = " + randomNumber4);
	}
}
