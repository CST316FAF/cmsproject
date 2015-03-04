package application;

import static org.junit.Assert.*;
import javafx.scene.text.Text;

import org.junit.Test;

public class BillingJunit {
	
	BillingJunit b = new BillingJunit();
	@Test
	public void test() {
		System.out.println("Test");
	}

	
	@Test
	public void string()
	{
		String[] countryList = { "USA", "Canada"," h"," y"};
		String[] usaStates = { "Alabama", "Alaska", "Arizona", "Arkansas"};
		int a = countryList.length;
		int b = usaStates.length;
		assertEquals(a, b);
	}
	
		
	@Test
	public void string2()
	{
		String[] usaStates = { "Alabama", "Alaska", "Arizona", "Arkansas"};
		String[] provinces = {"Quebec","Ontario","British Columbia", "Alberta"};
		int a = usaStates.length;
		int b = usaStates.length;
		assertEquals(a, b);
	}
	
	
	

		          
}
