package primary_secondary_cluster_optimazation;

public class QudraticProbing extends Cluster { // extend Cluster class

	public long HashFunction(long value, int i) { // h(k, i) = (h'(k) + i(i+1)/2) mod m, where m is the array size

		long newHash = HashFunction(value);

		return (newHash + (i * (i + 1)) / 2) % array_size;

	}

	private long HashFunction(long value) { // h'(k) = f(k, m)

		double a = 0.6180339887498949;

		double frac = a * value;

		long v = (long) frac;

		frac = frac - v;

		return (long) Math.floor(frac * array_size);

	}
}
