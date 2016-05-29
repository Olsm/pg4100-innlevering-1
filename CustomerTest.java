package innlevering1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import static org.mockito.Matchers.*;

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
		Thread.sleep(2001);
		
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
		Thread.sleep(3001);
		
		verify(rentalAgency, atLeast(1)).rent(customer);
		verify(rentalAgency, atLeast(2)).getCarsAvailable();
	}

}
