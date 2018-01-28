package satisfaction;

import static org.junit.Assert.*;

import org.junit.Test;

public class SatisfactionIndexTest {

	@Test
	public void testPercent() {
		assertEquals(33.33f, new SatisfactionIndex(20000, 6666).percentage(), 0.1f);
	}

}
