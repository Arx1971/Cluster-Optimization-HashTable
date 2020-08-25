package primary_secondary_cluster_optimazation;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public abstract class Cluster {

	protected long n;
	long seed_value = 98760053;
	protected final long array_size = 1048576;
	int probing = 0; // Counter for number of probing performed to insert the data
	protected List<Integer> totalEmptyClusterList; // Empty Cluster List
	protected List<Integer> totalClusterList; // Non-Empty Cluster List

	public long getN() {
		return n;
	}

	public void setN(long n) {
		this.n = n;
	}

	abstract public long HashFunction(long value, int i);

	private Hashtable<Long, Integer> ProbeSequence() { // This Method is inserting the key into HashTable and keep track
														// of collision

		RandomNumberGenerator myRandom = new RandomNumberGenerator();

		Hashtable<Long, Integer> hashtable = new Hashtable<Long, Integer>();

		long value = seed_value;

		for (int i = 1; i <= n; i++) {

			myRandom = new RandomNumberGenerator(value);

			value = myRandom.pseudoRandom();

			int collision = 0;

			if (hashtable.containsKey(HashFunction(value, collision))) {

				collision++;

				while (true) {
					probing++;
					if (hashtable.get(HashFunction(value, collision)) == null) {
						hashtable.put(HashFunction(value, collision), 1);
						break;
					} else
						collision++;
				}

			} else {
				hashtable.put(HashFunction(value, collision), 1);
			}
		}

		return hashtable;

	}

	private int[] cluster() {	// Clustering the non-empty ProbeSequences

		Hashtable<Long, Integer> hashtable = ProbeSequence();

		int[] arr = new int[(int) (array_size)];

		totalClusterList = new ArrayList<Integer>();

		int counter = 0;

		for (int i = 1; i <= array_size; i++) {
			try {
				if (hashtable.get((long) i) == 1) {
					counter++;
				}
			} catch (NullPointerException e) {
				if (counter != 0)
					totalClusterList.add(counter);
				arr[counter]++;
				counter = 0;
			}

		}
		return arr;
	}

	private int[] emptyCluster() {	//// Clustering the empty ProbeSequences

		Hashtable<Long, Integer> hashtable = ProbeSequence();

		totalEmptyClusterList = new ArrayList<Integer>();

		int arr[] = new int[(int) (array_size)];

		int counter = 0;

		for (int i = 1; i <= array_size; i++) {
			try {
				if (hashtable.get((long) i) == 1) {
					if (counter != 0)
						totalEmptyClusterList.add(counter);
					arr[counter]++;
					counter = 0;
				} else if (hashtable.get((long) i) == null) {
					counter++;
				}
			} catch (NullPointerException e) {
				counter++;
			}
		}
		return arr;
	}

	public void display() {

		int arr[] = cluster(); // Cluster Sizes

		System.out.println("array size = total # of buckets = " + array_size);
		System.out.println("# of elements inserted = " + n);
		System.out.println("load factor = " + loadFactor());
		System.out.println("average # of probes performed by insertion procedure = " + (1 + (probing / (double) n)));
		System.out.println("The following is the distribution of the clusters.");
		System.out.println("Display format is: cluster size, # of clusters, (# of clusters)/(total # of clusters)");

		int numberofcluster = 0;
		for (int i = 1; i < array_size; i++)
			if (arr[i] > 0)
				numberofcluster += arr[i];

		List<Integer> ClusterList = new ArrayList<Integer>();

		for (int i = 1; i < array_size; i++) {

			if (arr[i] > 0) {

				ClusterList.add(i);

				System.out.println(i + ", " + arr[i] + ", " + (double) (arr[i] / (double) numberofcluster));

			}
		}
		System.out.println();
		System.out.println("total # of clusters = " + numberofcluster);
		System.out.println("average cluster size = " + (double) n / (double) numberofcluster);
		System.out.println("standard deviation of cluster sizes = " + calculateSD(totalClusterList));

		System.out.println();

		int[] emptyCluster = emptyCluster();

		List<Integer> emptyClusterList = new ArrayList<Integer>();

		int numberOfEmptyCluster = 0;

		for (int i = 1; i < array_size; i++) {

			if (emptyCluster[i] > 0) {
				numberOfEmptyCluster += emptyCluster[i];
				emptyClusterList.add(i);
			}
		}

		for (int i = 1; i < array_size; i++) {
			if (emptyCluster[i] > 0) {
				System.out.println(
						i + ", " + emptyCluster[i] + ", " + (double) (emptyCluster[i] / (double) numberOfEmptyCluster));

			}
		}

		System.out.println("total # of empty clusters = " + numberOfEmptyCluster);
		System.out.println("average empty cluster size = " + calculateAvg(totalEmptyClusterList));
		System.out.println("standard deviation of empty cluster sizes = " + calculateSD(totalEmptyClusterList));
	}

	public double calculateSD(List<Integer> list) {	//The standard deviation is s = [(Σ1 ≤ i ≤ n(xi−μ)2)/(n−1)]1/2.

		double standardDeviation = 0.0;

		int length = list.size();

		double mean = calculateAvg(list);

		for (double num : list) {
			standardDeviation += Math.pow(num - mean, 2);
		}

		return Math.sqrt(standardDeviation / length);
	}

	double loadFactor() {
		return n / (double) array_size;
	}

	public double calculateAvg(List<Integer> numbers) {	//The mean (average) of the xi is μ = (Σ1 ≤ i ≤ nxi)/n
		int length = numbers.size();
		int total = 0;
		for (int i = 0; i < length; i++)
			total += numbers.get(i);

		return (1.0 * total) / length;
	}

}
