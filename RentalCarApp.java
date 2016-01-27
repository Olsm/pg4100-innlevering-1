package innlevering1;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class RentalCarApp {
	private static CarRentalAgency rentalAgency = new CarRentalAgency();
	
	public RentalCarApp () {
		rentalAgency = new CarRentalAgency();
	}
	
	public static void main(String[] args) {
		// Use a CountDownLatch to make sure 5 threads have been started before they run
		CountDownLatch latch = new CountDownLatch(5);
		rentalAgency.printCarStatus();
		Scanner in = new Scanner(System.in);
		for (int i = 1; i <= 10; i++) {
			//System.out.println("Velg kunde " + i);
			Customer customer = new Customer(rentalAgency, latch, in.nextLine());
			new Thread(customer).start();
		}
		in.close();
	}
}
