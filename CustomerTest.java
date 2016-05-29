package innlevering1;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import static org.mockito.Matchers.*;

public class CustomerTest {

	@Test
	public void testCustomerRentCarAndDeliver() throws InterruptedException {
		CarRentalAgency rentalAgency = mock(CarRentalAgency.class);
		CountDownLatch latch = new CountDownLatch(0);
		String customerName = "test";
		
		Customer customer = new Customer(rentalAgency, latch, customerName, 1);
		new Thread(customer).start();
		
		RentalCar car = new RentalCar("test");	
		when(rentalAgency.rent(customer)).thenReturn(car);
		//when(rentalAgency.getCarsAvailable()).thenReturn(new ArrayList<RentalCar>());
		
		Thread.sleep(2001);
		
		verify(rentalAgency, times(1)).rent(customer);
		verify(rentalAgency, times(1)).deliver(customer, car);
	}

}
