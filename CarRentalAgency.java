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
	
	// Provide available car to customer and move to carsRented
	public RentalCar rent () {
		RentalCar car = null;
		
		if (getCarsAvailable().size() > 0) {
			car = carsAvailable.get(0);
			carsAvailable.remove(car);
			carsRented.add(car);
		}
		
		return car;
	}
	
	// Deliver a rented car and move to carsAvailable
	public void deliver (RentalCar car) {
		if (carsRented.contains(car)) {
			carsRented.remove(car);
			carsAvailable.add(car);
		}
	}
	
	// Print status for the cars
	public void printCarStatus () {
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
	
	// Getters and setters
	
	public ArrayList<RentalCar> getCarsAvailable () {
		return carsAvailable;
	}
	
	public ArrayList<RentalCar> getCarsRented () {
		return carsRented;
	}
}
