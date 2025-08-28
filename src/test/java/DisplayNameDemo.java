import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DisplayNameDemo {
	@Test
	@DisplayName("Custom test name containing spaces")
	void testWithDisplayNameContainingSpaces() {
	}

	@Test
	@DisplayName("╯°□°）╯")
	void testWithDisplayNameContainingSpecialCharacters() {
	}

	@Test
	@DisplayName("ὣ")
	void testWithDisplayNameContainingEmoji() {
	}

}
