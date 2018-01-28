package satisfaction;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CoordinatesTest extends Distribution {

	public CoordinatesTest() {
		super(null);
	}

	/**
	 * Value object tests.
	 */
	@Test
	public void testEqualsAndHashCode() {
		
		Cursor a = new Cursor(5,100);
		Cursor b = new Cursor(5,100);
		
		assertEquals(a.hashCode(), b.hashCode());
		assertEquals(a, b);
	}

}
