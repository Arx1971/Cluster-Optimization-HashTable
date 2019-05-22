package primary_secondary_cluster_optimazation;

public class RandomNumberGenereator {

	public long seed;

	public RandomNumberGenereator() {
		// default constructor
	}

	public RandomNumberGenereator(long seed) {
		this.seed = seed;
	}

	public long pseduRandom() { // random number generator
		long a = 16807;
		long mod = 2147483647;
		long ans = (a * seed) % mod;
		return ans;
	}

}
