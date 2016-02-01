package innlevering1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

public class CarRentalAgencyTest {
	CarRentalAgency rentalAgency;
	CarRentalAgency mockRentalAgency;
	RentalCar car;
	Customer customer;
	
	@Before
	public void setup () {
		rentalAgency = new CarRentalAgency(); 
		mockRentalAgency = mock (CarRentalAgency.class);
		car = rentalAgency.getCarsAvailable().get(0);
		customer = new Customer(rentalAgency, null, "Olav");
	}

	@Test
	public void testRentAndDeliver () {
		// Test rent
		assertEquals(car, rentalAgency.rent(customer));
		assertFalse(rentalAgency.getCarsAvailable().contains(car));
		
		// Test deliver
		rentalAgency.deliver(customer, car);
		assertTrue(rentalAgency.getCarsAvailable().contains(car));
	}
	
	@Test
	public void testRentNotAvailable () {
		when(mockRentalAgency.getCarsAvailable()).thenReturn(new ArrayList<RentalCar>());
		assertEquals(null, mockRentalAgency.rent(customer));
	}
	
	@Test
	public void testDeliverCarNotInRented () {
		car = new RentalCar("NotInUtleier");
		rentalAgency.deliver(customer, car);
		assertFalse(rentalAgency.getCarsAvailable().contains(car));
	}

}
