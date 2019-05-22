package primary_secondary_cluster_optimazation;

public class DoubleHashing extends Cluster { // extend cluster class

	public long HashFunction(long value, int i) { // h(k, i) = (h1(k) + i h2(k)) mod m, where m is arraysize

		long newhash_1 = HashFunction(value);
		long newhash_2 = HashFunction_2(value);

		return (newhash_1 + (i * newhash_2)) % array_size;

	}

	private long HashFunction_2(long value) { // h2(k) = k mod m if k mod m is odd, (k mod m)+1 if k mod m is even
		if (value % array_size != 0) {
			return value % array_size;
		}
		return (value % array_size) + 1;
	}

	private long HashFunction(long value) { // h1(k) = f(k, m),

		double a = 0.6180339887498949;

		double frac = a * value;

		long v = (long) frac;

		frac = frac - v;

		return (long) Math.floor(frac * array_size);

	}

}
