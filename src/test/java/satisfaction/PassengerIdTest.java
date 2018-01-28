package satisfaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class PassengerIdTest {
	/**
	 * Test that passenger ID is checked by method factory.
	 */
	@Test
	public void testThatOfFailForInvalidValues() {
		testCaseFor(0);
		testCaseFor(-1);
	}

	private void testCaseFor(int passengerId) {
		try {
			PassengerId.of(passengerId);
			fail("Shouldn't arrive here");
		} catch(ValidationException e) {
			// NOOP
		}
	}

	/**
	 * Value object tests.
	 * @throws ValidationException 
	 */
	@Test
	public void testEqualsAndHashCode() throws ValidationException {
		PassengerId a = PassengerId.of(10);
		PassengerId b = PassengerId.of(10);
		
		assertEquals(a.hashCode(), b.hashCode());
		assertEquals(a, b);
	}

}
