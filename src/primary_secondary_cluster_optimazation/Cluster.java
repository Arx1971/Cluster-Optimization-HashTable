package primary_secondary_cluster_optimazation;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public abstract class Cluster {

	protected long n;
	long seed_value = 98760053;
	protected final long array_size = 1048576;
	int probing = 0; // Counter for number of probing performed to insert the data
	protected List<Integer> totalemptyClusterList; // Empty Cluster List
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

		RandomNumberGenereator myRandom = new RandomNumberGenereator();

		Hashtable<Long, Integer> hashtable = new Hashtable<Long, Integer>();

		long value = seed_value;

		for (int i = 1; i <= n; i++) {

			myRandom = new RandomNumberGenereator(value);

			value = myRandom.pseduRandom();

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

	private int[] cluster() {	// Clustering the non-empty ProbeSequnce

		Hashtable<Long, Integer> hashtable = ProbeSequence();

		int arr[] = new int[(int) (array_size)];

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

	private int[] emptycluster() {	//// Clustering the empty ProbeSequnce

		Hashtable<Long, Integer> hashtable = ProbeSequence();

		totalemptyClusterList = new ArrayList<Integer>();

		int arr[] = new int[(int) (array_size)];

		int counter = 0;

		for (int i = 1; i <= array_size; i++) {
			try {
				if (hashtable.get((long) i) == 1) {
					if (counter != 0)
						totalemptyClusterList.add(counter);
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
		System.out.println("load factor = " + loadfactor());
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

		int emptycluster[] = emptycluster();

		List<Integer> emptyclusterList = new ArrayList<Integer>();

		int numberofemptycluster = 0;

		for (int i = 1; i < array_size; i++) {

			if (emptycluster[i] > 0) {
				numberofemptycluster += emptycluster[i];
				emptyclusterList.add(i);
			}
		}

		for (int i = 1; i < array_size; i++) {
			if (emptycluster[i] > 0) {
				System.out.println(
						i + ", " + emptycluster[i] + ", " + (double) (emptycluster[i] / (double) numberofemptycluster));

			}
		}

		System.out.println("total # of empty clusters = " + numberofemptycluster);
		System.out.println("average empty cluster size = " + calculateAvg(totalemptyClusterList));
		System.out.println("standard deviation of empty cluster sizes = " + calculateSD(totalemptyClusterList));
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

	double loadfactor() {
		return n / (double) array_size;
	}

	public double calculateAvg(List<Integer> numbers) {	//The mean (average) of the xi is μ = (Σ1 ≤ i ≤ nxi)/n
		int length = numbers.size();
		int total = 0;
		for (int i = 0; i < length; i++)
			total += numbers.get(i);

		double average = (1.0 * total) / length;

		return average;
	}

}
