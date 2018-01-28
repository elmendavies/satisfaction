package satisfaction;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import satisfaction.Specification.Group;
import satisfaction.Specification.PassengerPreference;

public class GroupTest {


	PassengerId[][] passengers_1 = new PassengerId[3][4];
	
	@Before
	public void SetUp() throws ValidationException {
		passengers_1[1][0] = PassengerId.of(10);
		passengers_1[1][2] = PassengerId.of(11);
		passengers_1[1][3] = PassengerId.of(12);
	}
	
	@Test
	public void testParseFromString() throws ValidationException {
		Group group = Group.parseGroup("1 3W 4");
		assertEquals(3, group.size());
		assertEquals(new PassengerPreference(PassengerId.of(1), false), group.get(0));
		assertEquals(new PassengerPreference(PassengerId.of(3), true), group.get(1));
		assertEquals(new PassengerPreference(PassengerId.of(4), false), group.get(2));
	}	
	
}
