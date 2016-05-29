package innlevering1;

import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class RentalCarApp {
	private CarRentalAgency rentalAgency;
	private CountDownLatch latch;
	private InputStream in;
	
	public RentalCarApp () {
		this(new CarRentalAgency(), System.in);
	}

	public RentalCarApp (CarRentalAgency rentalAgency, InputStream in) {
		this.rentalAgency = rentalAgency;
		this.in = in;
		// Use a CountDownLatch to make sure 5 threads have been started before they run
		this.latch = new CountDownLatch(5);
	}
	
	public static void main(String[] args) {
		new RentalCarApp().runApp(System.in);
	}
	
	public void runApp() {
		this.runApp(in);
	}
	
	public void runApp(InputStream in) {		
		this.rentalAgency.printCarStatus("");
		
		Scanner input = new Scanner(in);
		
		for (int i = 1; i <= 10; i++) {
			this.createCustomerThread(input.nextLine()).start();
		}
		
		input.close();
	}
	
	public Thread createCustomerThread(String customerName) {
		Customer customer = new Customer(rentalAgency, latch, customerName);
		return new Thread(customer);
	}
}
