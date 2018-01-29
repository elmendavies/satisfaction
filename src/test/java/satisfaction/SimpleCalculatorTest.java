package satisfaction;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SimpleCalculatorTest {

	/**
	 * Test the provided example.
	 * @throws ValidationException
	 */
	@Test
	public void testTheExampleProvided() throws ValidationException {
		assertCorrect("4 4\n" + 
				"1W 2 3\n" + 
				"4 5 6 7\n" + 
				"8\n" + 
				"9 10 11W\n" + 
				"12W\n" + 
				"13 14\n" + 
				"15 16", Distribution.of(4, 
							PassengerId.createList(
									1, 2, 3, 8, 
									4, 5, 6, 7, 
									11, 9, 10, 12, 
									13, 14, 15,16)));		
	}

	/**
	 * Test what happends if there is a small group of three passengers where
	 * two of them want window. 
	 * @throws ValidationException
	 */
	@Test
	public void testTwoWindowsInSmallGroup() throws ValidationException {
		assertCorrect("4 4\n" + 
				"1W 2 3W\n" + 
				"4 5 6 7\n" + 
				"8\n" + 
				"9 10 11W\n" + 
				"12W\n" + 
				"13 14\n" + 
				"15 16", Distribution.of(4, 
							PassengerId.createList(
									4, 5, 6, 7, 
									8, 2, 1, 3,
									11, 9, 10, 12, 
									13, 14, 15,16)));		
	}

	/**
	 * Test big plain dimensions.
	 * @throws ValidationException
	 */
	@Test
	public void testBigPlain() throws ValidationException {
		assertCorrect("3 5\n" + 
				"1W 2 3\n" + 
				"4 5\n" + 
				"8\n" + 
				"9 10 11W\n" + 
				"12W\n" + 
				"15 16", Distribution.of(5, 
							PassengerId.createList(
									1, 2, 3, 4, 5,  
									8, 15, 16, 12, null, 
									11, 9, 10, null, null)));		
	}


	/**
	 * Test small plain dimensions.
	 * @throws ValidationException
	 */
	@Test
	public void testSmallPlain() throws ValidationException {
		assertCorrect("2 3\n" + 
				"1W 2 3\n" + 
				"4 5\n" + 
				"8\n" + 
				"9 10 11W\n" + 
				"12W\n" + 
				"15 16", Distribution.of(3, 
							PassengerId.createList(
									2, 3, 1,  
									4, 5, 8)));		
	}
	
	private void assertCorrect(String input, Distribution expected) throws ValidationException {
		DistributionCalculator calculator = new SimpleCalculator();
		SatisfactionIndexCalculator satisfactionIndexCalculator = new AllOrNothingSatisfactionIndexCalculator();
		
		Specification specification = Specification.parseSpecification(
				input);
		
		Distribution distribution = calculator.calculateFor(specification);

		assertEquals(expected, distribution);
	}
}
