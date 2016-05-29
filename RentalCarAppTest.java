package innlevering1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import java.io.ByteArrayInputStream;

public class RentalCarAppTest {
	RentalCarApp app;

	@Before
	public void setUp() {
		app = Mockito.spy(RentalCarApp.class);
	}
	
	@Test
	public void testCustomerThreadsAreStarted() {
		ByteArrayInputStream in = new ByteArrayInputStream("Olav\nSimen\nSemet\nAnita\nEli\nValmir\nJonas\nJens\nNina\nMona".getBytes());
		app.runApp(in);
		
		verify(app, times(10)).createCustomerThread(anyString());
		assertTrue(Thread.activeCount() >= 10);
		
	}

}
