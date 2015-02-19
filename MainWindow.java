import javax.swing.*;
import java.awt.*;

public class MainWindow {

	public static void main(String[] args){
		
		JFrame main = new JFrame();
		main.getContentPane().add(new MainPanel());
		main.setSize(800,800);
		main.setVisible(true);
		
	}
	
}
