package innlevering1;

import java.util.ArrayList;

public class Utleier {
	private ArrayList<Leiebil> carsAvailable;
	private ArrayList<Leiebil> carsRented;
	
	public Utleier () {
		// Create lists for cars available and rented
		carsAvailable = new ArrayList<Leiebil> (5);
		carsRented = new ArrayList<Leiebil> (5);
		
		// Utleier will initially have 5 cars available
		for (int i = 1; i <= 5; i++) {
			carsAvailable.add(new Leiebil("NO" + i));
		}
	}
	
	// Provide available car to customer and move to carsRented
	public Leiebil leie () {
		Leiebil car = null;
		
		if (getCarsAvailable().size() > 0) {
			car = carsAvailable.get(0);
			carsAvailable.remove(car);
			carsRented.add(car);
		}
		
		return car;
	}
	
	// Deliver a rented car and move to carsAvailable
	public void levereInn (Leiebil car) {
		if (carsRented.contains(car)) {
			carsRented.remove(car);
			carsAvailable.add(car);
		}
	}
	
	// Print status for the cars
	public void printCarStatus () {
		System.out.println("*********** Status for utleiebilene *****************");
		
		System.out.print("Ledig: ");
		for (Leiebil car : carsAvailable) {
			System.out.print(car.getRegNumber() + ", ");
		}
		
		System.out.print("\nUtleid: ");
		for (Leiebil car : carsRented) {
			System.out.print(car.getRegNumber() + ", ");
		}
		System.out.println("\n*********** Status slutt *****************");
		System.out.println();
	}
	
	// Getters and setters
	
	public ArrayList<Leiebil> getCarsAvailable () {
		return carsAvailable;
	}
	
	public ArrayList<Leiebil> getCarsRented () {
		return carsRented;
	}
}
