package satisfaction;

/**
 * A simple interface that any distribution calculation algorithm should implement.
 * @author mme
 *
 */
@FunctionalInterface
public interface DistributionCalculator {
	/**
	 * Calculates a distribution for the specification provided.
	 * @param specification A valid specification.
	 * @return The distribution calculated.
	 */
	Distribution calculate(Specification specification);
}
