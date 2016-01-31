package innlevering1;

import java.util.ArrayList;

public class CarRentalAgency {
	private ArrayList<RentalCar> carsAvailable;
	private ArrayList<RentalCar> carsRented;
	
	public CarRentalAgency () {
		// Create lists for cars available and rented
		carsAvailable = new ArrayList<RentalCar> (5);
		carsRented = new ArrayList<RentalCar> (5);
		
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
		for (RentalCar car : carsRented) {
			System.out.print(car.getRegNumber() + ", ");
		}
		System.out.println("\n*********** Status slutt *****************");
		System.out.println();
	}
	
	// Provide available car to customer and move to carsRented
	public synchronized RentalCar rent () {
		RentalCar car = null;
		
		if (getCarsAvailable().size() > 0) {
			car = carsAvailable.get(0);
			carsAvailable.remove(car);
			carsRented.add(car);
		}
		
		if (car != null)
			printCarStatus(""/*getCustomerName() + " har leid " + car.getRegNumber()*/);
		
		return car;
	}
	
	// Deliver a rented car and move to carsAvailable
	public synchronized void deliver (RentalCar car) {
		if (carsRented.contains(car)) {
			carsRented.remove(car);
			carsAvailable.add(car);
		}
		
		printCarStatus(""/*getCustomerName() + " har levert inn " + car.getRegNumber()*/);
	}
	
	// Getters and setters
	
	public synchronized ArrayList<RentalCar> getCarsAvailable () {
		return carsAvailable;
	}
	
	public synchronized ArrayList<RentalCar> getCarsRented () {
		return carsRented;
	}
}
