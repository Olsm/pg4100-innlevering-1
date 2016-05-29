package innlevering1;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Customer implements Runnable {
	final int maxSecondsToWait;
	private CarRentalAgency rentalAgency;
	private CountDownLatch latch;
	private String customerName;
	private Random rng;
	
	public Customer (CarRentalAgency rentalAgency, CountDownLatch latch, String customerName, int maxSecondsToWait) {
		this.maxSecondsToWait = maxSecondsToWait;	// default is max 10 seconds
		this.rentalAgency = rentalAgency;
		this.latch = latch;
		this.customerName = customerName;
		rng = new Random();
	}
	
	public String getCustomerName () {
		return customerName;
	}
	
	public void run () {
		
		// Make sure enough threads has been started before running
		latch.countDown();
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (true) {
			// sleep before renting car
			sleep(rng.nextInt(maxSecondsToWait)+1);
			
			// Try to rent a car
			RentalCar car = rentalAgency.rent(this);
			
			// If no car was available, wait for one to become available
			if (car == null) {
				while (rentalAgency.getCarsAvailable().size() == 0)
					sleep(1);
				continue;
			} else {
				// Sleep before delivering car
				sleep(rng.nextInt(Math.max(1,maxSecondsToWait/3))+1);	// default is 1-3 seconds
				
				// Deliver the car and print status
				rentalAgency.deliver(this, car);
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
