package primary_secondary_cluster_optimazation;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;

public abstract class SeparateChaining {

	protected long n;
	long seed_value = 98760053; // seed value
	protected final long array_size = 1000003; // bucket size, m
	protected List<Integer> totalLoad; // to store all the data that inserting into bucket

	public abstract long HashFunction(long value); // Abstract method for HashFunction

	public long getN() {
		return n;
	}

	public void setN(long n) {
		this.n = n;
	}

	public Hashtable<Long, Integer> Chaining() {

		RandomNumberGenerator myRandom = new RandomNumberGenerator();

		Hashtable<Long, Integer> hashtable = new Hashtable<>();

		long value = seed_value;

		totalLoad = new ArrayList<Integer>();

		for (int i = 0; i < array_size; i++) // initializing arraylist with zero
			totalLoad.add(0);

		for (int i = 1; i <= n; i++) { // replace totalLoad value with value's that generated from
										// RandomNumberGenerator

			myRandom = new RandomNumberGenerator(value);

			value = myRandom.pseudoRandom();

			totalLoad.set((int) HashFunction(value), totalLoad.get((int) HashFunction(value)) + 1);

		}

		for (int i = 1; i <= n; i++) { // Inserting keys into hashtable using RandomNumberGenerator class and keep
										// tracking of Collision

			myRandom = new RandomNumberGenerator(value);

			value = myRandom.pseudoRandom();

			if (hashtable.containsKey(HashFunction(value))) {
				hashtable.put(HashFunction(value), hashtable.get(HashFunction(value)) + 1);
			} else {
				hashtable.put(HashFunction(value), 1);
			}
		}

		return hashtable;

	}

	public void display() {

		Hashtable<Long, Integer> hashtable = Chaining(); // retrieving the data that inserted into hashtable in
															// Chaining Method

		System.out.println("array size = total # of buckets = " + array_size);
		System.out.println("# of elements inserted = " + n);
		System.out.println("The following is the distribution of bucket sizes.");
		System.out.println("Display format is: bucket size, # of buckets, (# of buckets)/(total # of buckets)");

		long value = seed_value;

		int arr[] = new int[(int) array_size + 1];

		RandomNumberGenerator myRandom = new RandomNumberGenerator();

		for (Entry<Long, Integer> itr : hashtable.entrySet()) {

			myRandom = new RandomNumberGenerator(value);

			value = myRandom.pseudoRandom();

			arr[itr.getValue()]++; // number of value added into same key

		}
		long sum = 0;

		for (int i = 0; i < arr.length; i++) {

			if (arr[i] > 0) {
				sum += arr[i];
			}
		}

		arr[0] = (int) (array_size - sum);

		List<Integer> loadList = new ArrayList<Integer>();

		for (int i = 0; i < arr.length; i++) {

			if (arr[i] > 0) {
				loadList.add(i);
				System.out.println(i + ", " + arr[i] + ", " + (double) (arr[i] / (double) array_size));
			}

		}

		System.out.println();

		System.out.println("load factor = " + loadFactor());

		System.out.println("standard deviation of bucket sizes from load factor = " + calculateSD(totalLoad));

	}

	double loadFactor() {
		return n / (double) array_size;
	}

	public double calculateSD(List<Integer> list) { // The standard deviation is s = [(Σ1 ≤ i ≤ n(xi−μ)2)/(n−1)]1/2

		double standardDeviation = 0.0;

		int length = list.size();

		for (double num : list) {
			standardDeviation += Math.pow(num - loadFactor(), 2);
		}

		return Math.sqrt(standardDeviation / (length - 1));
	}

	public double calculateAvg(List<Integer> numbers) { // The mean (average) of the xi is μ = (Σ1 ≤ i ≤ nxi)/n
		int length = numbers.size();
		int total = 0;
		for (int i = 0; i < length; i++)
			total += numbers.get(i);

		double average = (1.0 * total) / length;

		return average;
	}
}
