package satisfaction;

/**
 * Contains information about passenger satisfaction.
 * @author mme
 *
 */
public class SatisfactionIndex {
	final int total;
	final int satisfied;
	
	public SatisfactionIndex(int total, int satisfied) {
		super();
		this.total = total;
		this.satisfied = satisfied;
	}
	
	public float percentage() {
		return 100f * satisfied / total;
	}
}
