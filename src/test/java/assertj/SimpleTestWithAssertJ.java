package assertj;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.function.Consumer;

import org.assertj.core.description.Description;
import org.junit.jupiter.api.Test;

class SimpleTestWithAssertJ {

	@Test
	void a_few_simple_assertions() {
		// 필요한 만큼 assertions를 체이닝할 수 있다.
		// 검증이 필요한 값을 assertThat() 메서드의 파라미터로 전달한다.
		assertThat("The Lord of the Rings")
			.isNotNull()
			.startsWith("The")
			.contains("Lord")
			.endsWith("Rings");
	}

	@Test
	void manyOfTypesSupportOnAssertJ() {
		assertThat(BigDecimal.ONE)
			.as("The Two")
			.isNull();
	}

	@Test
	void consumingDescription() {
		final StringBuilder descriptionReportBuilder = new StringBuilder(String.format("Assertions:%n"));
		Consumer<Description> descriptionConsumer = desc -> descriptionReportBuilder.append(String.format("-- %s%n", desc));
		setDescriptionConsumer(descriptionConsumer);

	}

}

record Person(String name) {

}
