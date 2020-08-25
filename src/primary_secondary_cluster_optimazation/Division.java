package primary_secondary_cluster_optimazation;

public class Division extends SeparateChaining{	// extend SeparateChaining class

	public long HashFunction(long value) { // Division Method

		return value % array_size;

	}
	
	
}
