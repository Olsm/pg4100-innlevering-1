package innlevering1;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Customer implements Runnable {
	final int maxSecondsToWait = 10;	// default is max 10 seconds
	private CarRentalAgency rentalAgency;
	private CountDownLatch latch;
	private String customerName;
	private Random rng;
	
	public Customer (CarRentalAgency rentalAgency, CountDownLatch latch, String customerName) {
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
			RentalCar car = rentalAgency.rent();
			
			// If no car was available, wait for one to become available
			if (car == null) {
				while (rentalAgency.getCarsAvailable().size() == 0)
					sleep(1);
				continue;
			} else {
				// Print status and sleep before delivering car
				System.out.println(getCustomerName() + " har leid " + car.getRegNumber());
				rentalAgency.printCarStatus();
				sleep(rng.nextInt(maxSecondsToWait/3)+1);	// default is 1-3 seconds
				
				// Deliver the car and print status
				rentalAgency.deliver(car);
				System.out.println(getCustomerName() + " har levert inn " + car.getRegNumber());
				rentalAgency.printCarStatus();
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