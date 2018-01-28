package satisfaction;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import satisfaction.Specification.Group;

public class SpecificationTest {

	@Test
	public void testParse() throws ValidationException {
		
		Specification specification = Specification.parseSpecification(
				"4 4\n" + 
				"1W 2 3\n" + 
				"4 5 6 7\n" + 
				"8\n" + 
				"9 10 11W\n" + 
				"12W\n" + 
				"13 14\n" + 
				"15 16");
		
		assertEquals(4, specification.rows());
		assertEquals(4, specification.cols());
		
		List<Group> groups = specification.groups();
		assertEquals(7, groups.size());
		
		Group group1 = groups.get(0);
		
		assertEquals(3, group1.size());
	}

}
