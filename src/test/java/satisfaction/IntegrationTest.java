package satisfaction;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IntegrationTest {

	@Test
	public void testExampleProvidedByClient() throws ValidationException {
		
		DistributionCalculator calculator = new SimpleCalculator();
		SatisfactionIndexCalculator satisfactionIndexCalculator = new AllOrNothingSatisfactionIndexCalculator();
		
		Specification specification = Specification.parseSpecification(
				"4 4\n" + 
				"1W 2 3\n" + 
				"4 5 6 7\n" + 
				"8\n" + 
				"9 10 11W\n" + 
				"12W\n" + 
				"13 14\n" + 
				"15 16");
		
		Distribution distribution = calculator.calculate(specification);
		
		SatisfactionIndex index = satisfactionIndexCalculator.satisfactionIndexOf(specification, distribution);
		
		float satisfactionPercent = index.percentage();
		
		assertEquals(100f, satisfactionPercent, 0.1);
		
	}

}
