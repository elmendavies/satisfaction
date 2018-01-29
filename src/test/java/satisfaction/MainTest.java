package satisfaction;

import static org.junit.Assert.*;

import org.junit.Test;

public class MainTest {

	/**
	 * This tests is needed to check that the {@link Main} class is where gradle excepts it. 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testMainClassIsThere() throws ClassNotFoundException {
		Class.forName("satisfaction.Main");
	}

}
