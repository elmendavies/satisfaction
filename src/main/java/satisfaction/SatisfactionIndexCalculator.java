package satisfaction;

/**
 * Interface to allow diferent ways to measure the satisfaction index.
 * @author mme
 *
 */
public interface SatisfactionIndexCalculator {
	
	/**
	 * Satisfaction index of the given distribution, for a specification.
	 * @param specification
	 * @param distribution
	 * @return
	 */
	SatisfactionIndex satisfactionIndexOf(Specification specification, Distribution distribution);
}
