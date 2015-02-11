import javax.swing.JFrame;


public class MainWindow {

	public static void main(String[] args) {

		JFrame mw = new JFrame();
		mw.setSize(800,600);
		mw.add(new MainPanel());
		mw.setVisible(true);

	}

}
