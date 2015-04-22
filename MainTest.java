package application;

import static org.junit.Assert.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JavaFxJUnit4ClassRunner.class)
public class MainTest  {
	double result = 51.0;
	//TextUtils myUtil = new TextUtils();

	@Test
	public void testStartStage() {
		assertEquals(result, TextUtils.computeTextWidth(Font.font("Arial", FontWeight.BOLD, 20),"Customer",0.0D),0.0D);
		//fail("Not yet implemented");
	}

}
