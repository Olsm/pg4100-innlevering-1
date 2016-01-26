package innlevering1;

import java.util.Scanner;

public class RentalCarApp {
	private static CarRentalAgency rentalAgency = new CarRentalAgency();
	
	public RentalCarApp () {
		rentalAgency = new CarRentalAgency();
	}
	
	public static void main(String[] args) {
		rentalAgency.printCarStatus();
		Scanner in = new Scanner(System.in);
		for (int i = 1; i <= 10; i++) {
			//System.out.println("Velg kunde " + i);
			Customer customer = new Customer(rentalAgency, in.nextLine());
			new Thread(customer).start();
		}
		in.close();
	}
}
