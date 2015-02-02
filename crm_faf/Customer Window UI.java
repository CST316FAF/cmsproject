package customer_window_ui

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

//button stuff
public class Customer Window UI extends Frame implements WindowListener,ActionListener {
        TextField text = new TextField(20);
        Button b;
        private int numClicks = 0;

        public static void main(String[] args) {
                AL myWindow = new AL("My First Window");
                myWindow.setSize(350,100);
                myWindow.setVisible(true);
        }

        public AL(String title) {

                super(title);
                setLayout(new FlowLayout());
                addWindowListener(this);
                b = new Button(“New Customer“);
                add(b);
                add(text);
                b.addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
                numClicks++;
                text.setText("Button Clicked " + numClicks + " times");
        }

        public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
        }

        public void windowOpened(WindowEvent e) {}
        public void windowActivated(WindowEvent e) {}
        public void windowClosed(WindowEvent e) {}

}

//customer window

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Customer Page extends JFrame {

    private static final long serialVersionUID = 1L;

    public CreateNewJTextField() {

        //set flow layout for the frame
        this.getContentPane().setLayout(new FlowLayout());

        //create empty JTextField
        JTextField field1 = new JTextField();
        field1.setText(“Employee Name”);

        //create empty JTextField
        JTextField field2 = new JTextField();
		field2.setText(“Company Name & Address”);

        //create empty JTextField
        JTextField field3 = new JTextField();
		field3.setText(“Phone Number & Email Address”);

		//create empty JTextField
        JTextField field4 = new JTextField();
		field4.setText(“Company Info”);

		//create empty JTextField
        JTextField field5 = new JTextField();
		field5.setText(“Miscellaneous);

        //add textfields to frame
        add(field1);
        add(field2);
        add(field3);
        add(field4);
		add(field5);

    }

	private static void createAndShowGUI() {

  	//create and set up the window

  	JFrame frame = new CreateNewJTextField();

  	//display the window

  	frame.pack();

  	frame.setVisible(true);

  	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {

  //Schedule a job for the event-dispatching thread:

  //creating and showing this application's GUI.

  javax.swing.SwingUtilities.invokeLater(new Runnable() {

public void run() {

    createAndShowGUI();

}

  });
    }

}
