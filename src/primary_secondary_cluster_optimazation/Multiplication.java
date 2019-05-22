package primary_secondary_cluster_optimazation;

public class Multiplication extends SeparateChaining { // extend SeparateChaining class

	public long HashFucntion(long value) {	// Multiplication Method for Separate Chaining

		double a = 0.6180339887498949;

		double frac = a * value;

		long v = (long) frac;

		frac = frac - v;

		return (long) Math.floor(frac * array_size);

	}

}
