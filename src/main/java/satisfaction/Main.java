package satisfaction;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) throws ValidationException, IOException {
		
		DistributionCalculator calculator = new SimpleCalculator();
		SatisfactionIndexCalculator satisfactionIndexCalculator = new AllOrNothingSatisfactionIndexCalculator();
		
		String input = readFile(args[0], Charset.forName("UTF-8"));		
		
		Specification specification = Specification.parseSpecification(input);
		
		Distribution distribution = calculator.calculateFor(specification);
		
		SatisfactionIndex index = satisfactionIndexCalculator.satisfactionIndexOf(specification, distribution);
		
		float satisfactionPercent = index.percentage();
		
		System.out.println(distribution.toString());
		System.out.println("" + satisfactionPercent + "%");
				
	}
	
	static String readFile(String path, Charset encoding) 
			  throws IOException {
	  byte[] encoded = Files.readAllBytes(Paths.get(path));
	  return new String(encoded, encoding);
	}

}
