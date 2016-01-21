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
	
	// TODO: Only run if 5 threads has been created
	public void run () {
		sleep(rng.nextInt(maxSecondsToWait)+1);	// sleep before start
		
		while (true) {
			// Try to rent a car
			Leiebil car = utleier.leie();
			
			// If no car was available, wait for one to become available
			if (car == null) {
				while (utleier.getCarsAvailable().size() == 0)
					sleep(1);
				continue;
			}
			
			// Sleep before renting car again
			sleep(rng.nextInt(maxSecondsToWait/3)+1);	// default is 1-3 seconds
		}
	}
	
	private void sleep (int secondsToWait) {
		try {
			Thread.sleep(secondsToWait);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
