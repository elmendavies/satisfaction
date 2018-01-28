package satisfaction;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import satisfaction.Specification.PassengerPreference;

public class PassengerPreferenceTest {

	@Test
	public void testNotWindow() throws ValidationException {
		PassengerPreference p = PassengerPreference.parsePassanger("1");
		assertEquals(PassengerId.of(1), p.passangerId());
		assertEquals(false, p.windowPrefered());
	}

	@Test
	public void testWindow() throws ValidationException {
		PassengerPreference p = PassengerPreference.parsePassanger("200");
		assertEquals(PassengerId.of(200), p.passangerId());
		assertEquals(false, p.windowPrefered());
	}
}
