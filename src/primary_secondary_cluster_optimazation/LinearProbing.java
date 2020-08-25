package primary_secondary_cluster_optimazation;

public class LinearProbing extends Cluster { // extend Cluster Class


	public long HashFunction(long value, int i) { // h(k, i) = (h'(k) + i) mod m, where m is array size

		long newHash = HashFunction(value);

		return (newHash + i) % array_size;

	}

	private long HashFunction(long value) {	// h'(k) = f(k, m)

		double a = 0.6180339887498949;

		double frac = a * value;

		long v = (long) frac;

		frac = frac - v;

		return (long) Math.floor(frac * array_size);

	}

}
