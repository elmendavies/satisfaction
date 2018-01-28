package satisfaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import satisfaction.Distribution.Cursor;

public class DistributionTest {

	PassengerId[][] passengers_1 = new PassengerId[3][4];
	
	@Before
	public void SetUp() throws ValidationException {
		passengers_1[0][0] = PassengerId.of(10);
		passengers_1[2][3] = PassengerId.of(11);
		passengers_1[2][2] = PassengerId.of(12);
	}
	
	@Test
	public void testPassangersThatAreThere() throws ValidationException {
		
		Distribution distribution = new Distribution(passengers_1);
		
		checkPassangerAt(distribution, 0, 0);
		checkPassangerAt(distribution, 2, 3);
	}

	private void checkPassangerAt(Distribution distribution, int row, int column) {
		
		// Get a passenger to test
		PassengerId passengerId = passengers_1[row][column];
		
		Cursor coordinates = distribution.coordinatesOf(passengerId);
		
		assertEquals(row, coordinates.row());
		assertEquals(column, coordinates.column());
	}

	@Test
	public void testPassangersThatAreNotThere() throws ValidationException {
		
		Distribution distribution = new Distribution(passengers_1);
		
		assertNull(distribution.coordinatesOf(PassengerId.of(5)));
	}
	
	@Test
	public void testIsWindow() throws ValidationException {
		Distribution distribution = new Distribution(passengers_1);
		
		assertTrue(distribution.coordinatesOf(PassengerId.of(10)).isInWindow());
		assertTrue(distribution.coordinatesOf(PassengerId.of(11)).isInWindow());		
	}
}
