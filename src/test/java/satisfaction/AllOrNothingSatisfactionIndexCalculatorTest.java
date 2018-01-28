package satisfaction;

import static org.junit.Assert.*;

import org.junit.Test;

public class AllOrNothingSatisfactionIndexCalculatorTest {

	@Test
	public void test() throws ValidationException {
		PassengerId[][] assignments = new PassengerId[3][4];
		
		Specification specification = Specification.parseSpecification(
				"4 4\n" + 
				"1W 2 3\n" + 
				"4 5 6 7\n" + 
				"8\n" + 
				"9 10 11W\n" + 
				"12W\n" + 
				"13 14\n" + 
				"15 16");		
		
		Distribution distribution = Distribution.of(4, PassengerId.createList(
				1, 2, 3, 8,
				4, 5, 6, 7,
				11, 9, 10, 12,
				13, 14, 15, 16));
		
		AllOrNothingSatisfactionIndexCalculator calculator = new AllOrNothingSatisfactionIndexCalculator();
		
		assertEquals(100f, calculator.satisfactionIndexOf(specification, distribution).percentage(), 0.1f);
	}

}
