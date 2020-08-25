package primary_secondary_cluster_optimazation;

public class RandomNumberGenerator {

	public long seed;

	public RandomNumberGenerator() {
		// default constructor
	}

	public RandomNumberGenerator(long seed) {
		this.seed = seed;
	}

	public long pseudoRandom() { // random number generator
		long a = 16807;
		long mod = 2147483647;
		long ans = (a * seed) % mod;
		return ans;
	}

}
