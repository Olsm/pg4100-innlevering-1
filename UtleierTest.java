package innlevering1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

public class UtleierTest {
	Utleier utleier;
	Utleier mockUtleier;
	Leiebil car;
	
	@Before
	public void setup () {
		utleier = new Utleier(); 
		mockUtleier = mock (Utleier.class);
		car = utleier.getCarsAvailable().get(0);
	}

	@Test
	public void testLeieLevereInn () {
		// Test leie
		assertEquals(car, utleier.leie());
		assertFalse(utleier.getCarsAvailable().contains(car));
		assertTrue(utleier.getCarsRented().contains(car));
		
		// Test levereInn
		utleier.levereInn(car);
		assertTrue(utleier.getCarsAvailable().contains(car));
		assertFalse(utleier.getCarsRented().contains(car));
	}
	
	@Test
	public void testLeieNotAvailable () {
		when(mockUtleier.getCarsAvailable()).thenReturn(new ArrayList<Leiebil>());
		assertEquals(null, mockUtleier.leie());
	}
	
	@Test
	public void testLevereInnCarNotInRented () {
		car = new Leiebil("NotInUtleier");
		utleier.levereInn(car);
		assertFalse(utleier.getCarsAvailable().contains(car));
		assertFalse(utleier.getCarsRented().contains(car));
	}

}
