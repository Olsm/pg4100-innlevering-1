package innlevering1;

import java.util.Random;

public class Kunde implements Runnable {
	final int maxSecondsToWait = 10;	// default is max 10 seconds
	private Utleier utleier;
	private String kundeNavn;
	private Random rng;
	
	public Kunde (Utleier utleier, String kundeNavn) {
		this.utleier = utleier;
		this.kundeNavn = kundeNavn;
		rng = new Random();
	}
	
	public String getKundeNavn () {
		return kundeNavn;
	}
	
	// TODO: Only run if 5 threads has been created
	public void run () {
		
		while (true) {
			// sleep before renting car
			sleep(rng.nextInt(maxSecondsToWait)+1);
			
			// Try to rent a car
			Leiebil car = utleier.leie();
			
			// If no car was available, wait for one to become available
			if (car == null) {
				while (utleier.getCarsAvailable().size() == 0)
					sleep(1);
				continue;
			} else {
				// Print status and sleep before delivering car
				System.out.println(getKundeNavn() + " har leid " + car.getRegNumber());
				utleier.printCarStatus();
				sleep(rng.nextInt(maxSecondsToWait/3)+1);	// default is 1-3 seconds
				
				// Deliver the car and print status
				utleier.levereInn(car);
				System.out.println(getKundeNavn() + " har levert inn " + car.getRegNumber());
				utleier.printCarStatus();
			}
		}
	}
	
	private void sleep (int secondsToWait) {
		try {
			Thread.sleep(secondsToWait * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
