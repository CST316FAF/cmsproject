package application;

import static org.junit.Assert.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import org.junit.Test;

public class TextUtilsTest {

	double result1 = 94.0;
	double result2 = 105.0;
	double result3 = 0.0;
	
	@Test
	public void testComputeTextWidth1() {
		assertEquals(result1, TextUtils.computeTextWidth(Font.font("Arial", FontWeight.BOLD, 20),"Customer",0.0D),0.0D);
		
		// to test code coverage, this condition will fail and the statement inside if condition is not executed
		// after adding this condition code coverage increased from 15.6% to 17.7%
		if(result3 == TextUtils.computeTextWidth(Font.font("Arial", FontWeight.BOLD, 20),"Customer",0.0D)){
			fail("Not yet implemented");
		}
		//assertTrue(TextUtils.computeTextWidth(Font.font("Arial", FontWeight.BOLD, 20),"Customer",0.0D) == result1);
		//fail("Not yet implemented");
	}
	
	@Test
	public void testComputeTextWidth2() {
		assertEquals(result2, TextUtils.computeTextWidth(Font.font("Arial", FontWeight.BOLD, 20),"Technician",0.0D),0.0D);
		//assertTrue(TextUtils.computeTextWidth(Font.font("Arial", FontWeight.BOLD, 20),"Technician",0.0D) == result2);
		//fail("Not yet implemented");
	}

}
