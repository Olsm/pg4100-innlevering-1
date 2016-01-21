package innlevering1;

import java.util.ArrayList;

public class Utleier {
	private ArrayList<Leiebil> carsAvailable;
	private ArrayList<Leiebil> carsRented;
	
	public Utleier () {
		carsAvailable = new ArrayList<Leiebil> ();
		carsRented = new ArrayList<Leiebil> ();
	}
	
	// Provide available car to customer and move to carsRented
	public Leiebil leie () {
		Leiebil car = null;
		
		if (carsAvailable.size() > 0) {
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
			addCar(car);
		}
	}
	
	// Getters and setters
	
	public ArrayList<Leiebil> getCarsAvailable () {
		return carsAvailable;
	}
	
	public ArrayList<Leiebil> getCarsRented () {
		return carsRented;
	}

	public void addCar(Leiebil car) {
		carsAvailable.add (car);
	}
}
