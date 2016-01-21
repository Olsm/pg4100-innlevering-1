package innlevering1;

import java.util.ArrayList;

public class Utleier {
	private ArrayList<Leiebil> carsAvailable;
	private ArrayList<Leiebil> carsRented;
	
	public Utleier () {
		carsAvailable = new ArrayList<Leiebil> ();
		carsRented = new ArrayList<Leiebil> ();
	}
	
	public Leiebil leie () {
		Leiebil car = null;
		
		if (carsAvailable.size() > 0) {
			// Remove a car from carsAvailable and add to carsRented
			car = carsAvailable.get(0);
			carsAvailable.remove(car);
			carsRented.add(car);
		}
		
		// Return the car if one was picked, or null if no cars available
		return car;
	}
	
	public void levereInn () {
		
	}
	
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
