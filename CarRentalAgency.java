package innlevering1;

import java.util.ArrayList;

public class CarRentalAgency {
	private ArrayList<RentalCar> carsAvailable;
	private ArrayList<RentalCar> carsRented;
	private ArrayList<Customer> customers;
	
	public CarRentalAgency () {
		// Create lists for cars available and rented
		carsAvailable = new ArrayList<RentalCar> (5);
		carsRented = new ArrayList<RentalCar>  (5);
		customers = new ArrayList<Customer>  (10);
		
		// CarRentalAgency will initially have 5 cars available
		for (int i = 1; i <= 5; i++) {
			carsAvailable.add(new RentalCar("NO" + i));
		}
	}
	
	// Print status for the cars
	public synchronized void printCarStatus (String status) {
		if (!status.isEmpty())
			System.out.println(status);
		
		System.out.println("*********** Status for utleiebilene *****************");
		
		System.out.print("Ledig: ");
		for (RentalCar car : carsAvailable) {
			System.out.print(car.getRegNumber() + ", ");
		}
		
		System.out.print("\nUtleid: ");
		for (int i = 0; i < carsRented.size(); i++) {
			RentalCar car = carsRented.get(i);
			Customer customer = customers.get(i);
			System.out.print(car.getRegNumber() + " leid av " + customer.getCustomerName() + ", ");
		}
		System.out.println("\n*********** Status slutt *****************");
		System.out.println();
	}
	
	// Provide available car to customer and move to carsRented
	public synchronized RentalCar rent (Customer customer) {
		RentalCar car = null;
		
		if (carsAvailable.size() > 0) {
			car = carsAvailable.get(0);
			carsRented.add(car);
			customers.add(customer);
			carsAvailable.remove(car);
			
			printCarStatus(customer.getCustomerName() + " har leid " + car.getRegNumber());
		} else
			printCarStatus(customer.getCustomerName() + " forsøkte å leie en bil, men ingen var ledig");
		
		return car;
	}
	
	// Deliver a rented car and move to carsAvailable
	public synchronized void deliver (Customer customer, RentalCar car) {
		if (carsRented.contains(car)) {
			carsRented.remove(car);
			customers.remove(customer);
			carsAvailable.add(car);

			printCarStatus(customer.getCustomerName() + " har levert inn " + car.getRegNumber());
		}
		
	}
	
	// Getters and setters
	
	public synchronized ArrayList<RentalCar> getCarsAvailable () {
		return carsAvailable;
	}
}
