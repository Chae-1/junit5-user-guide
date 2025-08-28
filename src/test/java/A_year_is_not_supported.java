import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class A_year_is_not_supported {
	@Test
	void if_it_is_zero() {
	}

	@DisplayName("A negative value for year is not supported by leap year computation.")
	@ParameterizedTest(name = "For Example, year {0} is not supported")
	@ValueSource(ints = {-1, -4})
	void if_it_is_negative(int year) {
		Assertions.assertSame(year, -1, "oh my god");
	}
}
