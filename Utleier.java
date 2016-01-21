package innlevering1;

import java.util.ArrayList;

public class Utleier {
	private ArrayList<Leiebil> carsAvailable;
	private ArrayList<Leiebil> carsRented;
	
	public Utleier () {
		carsAvailable = new ArrayList<Leiebil> (10);
		carsRented = new ArrayList<Leiebil> (10);
	}
	
	public void leie () {
		
	}
	
	public void levereInn () {
		
	}
	
	public ArrayList<Leiebil> getCarsAvailable () {
		return carsAvailable;
	}
	
	public ArrayList<Leiebil> getCarsRented () {
		return carsRented;
	}
}
