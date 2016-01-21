package innlevering1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UtleierTest {
	Utleier utleier;
	Leiebil car;
	
	@Before
	public void setup () {
		utleier = new Utleier(); 
		car = new Leiebil ("NO1");
		utleier.addCar (car);
	}
	
	@Test
	public void testAddCar() {
		car = new Leiebil ("NO2");
		utleier.addCar(car);
		assertTrue (utleier.getCarsAvailable().contains(car));
		assertEquals (2, utleier.getCarsAvailable().size());
	}

	@Test
	public void testLeie() {
		assertEquals(car, utleier.leie());
		assertEquals(0, utleier.getCarsAvailable().size());
	}

	@Test
	public void testLevereInn() {
		fail("Not yet implemented");
	}

}
