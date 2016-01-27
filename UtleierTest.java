package innlevering1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

public class UtleierTest {
	CarRentalAgency rentalAgency;
	CarRentalAgency mockRentalAgency;
	RentalCar car;
	
	@Before
	public void setup () {
		rentalAgency = new CarRentalAgency(); 
		mockRentalAgency = mock (CarRentalAgency.class);
		car = rentalAgency.getCarsAvailable().get(0);
	}

	@Test
	public void testRentAndDeliver () {
		// Test rent
		assertEquals(car, rentalAgency.rent());
		assertFalse(rentalAgency.getCarsAvailable().contains(car));
		assertTrue(rentalAgency.getCarsRented().contains(car));
		
		// Test deliver
		rentalAgency.deliver(car);
		assertTrue(rentalAgency.getCarsAvailable().contains(car));
		assertFalse(rentalAgency.getCarsRented().contains(car));
	}
	
	@Test
	public void testRentNotAvailable () {
		when(mockRentalAgency.getCarsAvailable()).thenReturn(new ArrayList<RentalCar>());
		assertEquals(null, mockRentalAgency.rent());
	}
	
	@Test
	public void testDeliverCarNotInRented () {
		car = new RentalCar("NotInUtleier");
		rentalAgency.deliver(car);
		assertFalse(rentalAgency.getCarsAvailable().contains(car));
		assertFalse(rentalAgency.getCarsRented().contains(car));
	}

}
