package innlevering1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UtleierTest {
	Utleier utleier;
	
	@Before
	public void setup () {
		utleier = new Utleier(); 
	}
	
	@Test
	public void testAddCar() {
		Leiebil car = new Leiebil ("NO1");
		utleier.addCar (car);
		assertTrue (utleier.getCarsAvailable().contains(car));
	}

	@Test
	public void testLeie() {
		fail("Not yet implemented");
	}

	@Test
	public void testLevereInn() {
		fail("Not yet implemented");
	}

}
