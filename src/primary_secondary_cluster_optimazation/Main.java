package primary_secondary_cluster_optimazation;

public class Main {

	public static void main(String args[]) {

		// Sample Output from the problem statement

		Division DV = new Division();
		DV.setN(2000000);
		DV.display();
		System.out.println();

		Multiplication ML = new Multiplication();
		ML.setN(2000000);
		ML.display();
		System.out.println();

		LinearProbing OALP = new LinearProbing();
		OALP.setN(800000);
		OALP.display();
		System.out.println();

		QudraticProbing OAQP = new QudraticProbing();
		OAQP.setN(800000);
		OAQP.display();
		System.out.println();

		DoubleHashing OADH = new DoubleHashing();
		OADH.setN(800000);
		OADH.display();
		System.out.println();

		// This part is for testing all given test cases
		long start = System.currentTimeMillis();
		int chainingarr[] = { 800000, 1000000, 2000000, 3000000 };
		int openadressingarr[] = { 500000, 800000, 1000000, 1048575 };

		division(chainingarr);
		multiplication(chainingarr);
		linearprobing(openadressingarr);
		quadraticprobing(openadressingarr);
		doublehashing(openadressingarr);
		long end = System.currentTimeMillis();

		System.out.println(end - start);

	}

	public static void division(int arr[]) {
		for (int i = 0; i < arr.length; i++) {
			Division DV = new Division();
			DV.setN(arr[i]);
			DV.display();
			System.out.println();
		}
		System.out.println("\n");
	}

	public static void multiplication(int arr[]) {
		for (int i = 0; i < arr.length; i++) {
			Multiplication ML = new Multiplication();
			ML.setN(arr[i]);
			ML.display();
			System.out.println();
		}
		System.out.println("\n");
	}

	public static void linearprobing(int arr[]) {

		for (int i = 0; i < arr.length; i++) {
			LinearProbing OALP = new LinearProbing();
			OALP.setN(arr[i]);
			OALP.display();
			System.out.println();
		}
		System.out.println("\n");
	}

	public static void quadraticprobing(int arr[]) {

		for (int i = 0; i < arr.length; i++) {
			QudraticProbing OAQP = new QudraticProbing();
			OAQP.setN(arr[i]);
			OAQP.display();
			System.out.println();
		}
		System.out.println("\n");
	}

	public static void doublehashing(int arr[]) {

		for (int i = 0; i < arr.length; i++) {
			DoubleHashing OADH = new DoubleHashing();
			OADH.setN(arr[i]);
			OADH.display();
			System.out.println();
		}
		System.out.println("\n");
	}

}