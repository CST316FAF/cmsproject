package application;

import static org.junit.Assert.*;

import org.junit.Test;

public class WorkersJunit {

	WorkersJunit myUnit = new WorkersJunit();
	@Test
	public void test() {
		System.out.println("Test!");
	}


@Test
public void string() {
	String theQuery = "SELECT * FROM technicians";
    assertEquals(12, theQuery.length());
}

@Test
public void testSetTime() throws
Throwable
{
	WorkersJunit myUnit = new WorkersJunit();
myUnit.wait(5);
}
}
