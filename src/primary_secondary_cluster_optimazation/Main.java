package primary_secondary_cluster_optimazation;

public class Main {

	public static void main(String args[]) {

		// Sample Output from the problem statement

		Division division = new Division();
		division.setN(2000000);
		division.display();
		System.out.println();

		Multiplication multiplication = new Multiplication();
		multiplication.setN(2000000);
		multiplication.display();
		System.out.println();

		LinearProbing linearProbing = new LinearProbing();
		linearProbing.setN(800000);
		linearProbing.display();
		System.out.println();

		QudraticProbing qudraticProbing = new QudraticProbing();
		qudraticProbing.setN(800000);
		qudraticProbing.display();
		System.out.println();

		DoubleHashing doubleHashing = new DoubleHashing();
		doubleHashing.setN(800000);
		doubleHashing.display();
		System.out.println();

		// This part is for testing all given test cases
		long start = System.currentTimeMillis();
		int[] chainingArr = { 800000, 1000000, 2000000, 3000000 };
		int[] openAddressingArr = { 500000, 800000, 1000000, 1048575 };

		division(chainingArr);
		multiplication(chainingArr);
		linearProbing(openAddressingArr);
		quadraticProbing(openAddressingArr);
		doubleHashing(openAddressingArr);
		long end = System.currentTimeMillis();

		System.out.println(end - start);

	}

	public static void division(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			Division division = new Division();
			division.setN(arr[i]);
			division.display();
			System.out.println();
		}
		System.out.println("\n");
	}

	public static void multiplication(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			Multiplication multiplication = new Multiplication();
			multiplication.setN(arr[i]);
			multiplication.display();
			System.out.println();
		}
		System.out.println("\n");
	}

	public static void linearProbing(int[] arr) {

		for (int i = 0; i < arr.length; i++) {
			LinearProbing linearProbing = new LinearProbing();
			linearProbing.setN(arr[i]);
			linearProbing.display();
			System.out.println();
		}
		System.out.println("\n");
	}

	public static void quadraticProbing(int arr[]) {

		for (int i = 0; i < arr.length; i++) {
			QudraticProbing qudraticProbing = new QudraticProbing();
			qudraticProbing.setN(arr[i]);
			qudraticProbing.display();
			System.out.println();
		}
		System.out.println("\n");
	}

	public static void doubleHashing(int arr[]) {

		for (int i = 0; i < arr.length; i++) {
			DoubleHashing doubleHashing = new DoubleHashing();
			doubleHashing.setN(arr[i]);
			doubleHashing.display();
			System.out.println();
		}
		System.out.println("\n");
	}

}