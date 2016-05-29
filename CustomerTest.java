package innlevering1;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import org.junit.Before;
import org.junit.Test;

public class CustomerTest {
	CarRentalAgency rentalAgency;
	Customer customer;
	CountDownLatch latch;
	RentalCar car;
	
	@Before
	public void setUp() {
		rentalAgency = mock(CarRentalAgency.class);
		latch = new CountDownLatch(0);
		customer = new Customer(rentalAgency, latch, "CustomerName", 1);
		car = new RentalCar("test");
	}

	@Test
	public void testCustomerRentCarAndDeliver() throws InterruptedException {
		when(rentalAgency.rent(customer)).thenReturn(car);

		new Thread(customer).start();
		Thread.sleep(3000);
		
		verify(rentalAgency, atLeast(1)).rent(customer);
		verify(rentalAgency, atLeast(1)).deliver(customer, car);
	}
	
	@Test
	public void testCustomerWaitsForCarAvailable() throws InterruptedException {	
		ArrayList<RentalCar> carAvailable = new ArrayList<>();
		carAvailable.add(car);
		
		when(rentalAgency.rent(customer)).thenReturn(null);
		when(rentalAgency.getCarsAvailable()).thenReturn(new ArrayList<RentalCar>())
											 .thenReturn(carAvailable);

		new Thread(customer).start();
		Thread.sleep(3000);
		
		verify(rentalAgency, atLeast(1)).rent(customer);
		verify(rentalAgency, atLeast(2)).getCarsAvailable();
	}
	
	@Test
	public void testWaitsForFiveThreads() throws InterruptedException {
		latch = new CountDownLatch(5);
		
		for (int i = 1; i <= 4; i++) {
			startNewCustomerThread();
		}

		Thread.sleep(2000);
		verify(rentalAgency, never()).rent(any(Customer.class));
		
		startNewCustomerThread();
		Thread.sleep(2000);
		verify(rentalAgency, atLeast(1)).rent(any(Customer.class));
	}
	
	private void startNewCustomerThread() {
		customer = new Customer(rentalAgency, latch, "CustomerName", 1);
		Thread thread = new Thread(customer);
		thread.start();
	}

}
