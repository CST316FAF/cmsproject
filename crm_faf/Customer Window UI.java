package crm_faf;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
/* too much red also to much pasta
//button stuff
public class CustomerWindowUI extends Frame implements WindowListener,ActionListener {
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

//possible option for customer window
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

//another possible option for customer page
private void newCustomer() {

    JLabel labelCode = new JLabel("Customer Code *");
    JTextField jTextFieldCode = new JTextField();
    JLabel labelName = new JLabel("Customer Name *");
    JTextField jTextFieldName = new JTextField();
    JLabel labelPrincipalAddress = new JLabel("Principal Address *");
    JTextField jTextFieldPrincipalAddress = new JTextField();
    JLabel labelSaleAddress = new JLabel("Sale Address *");
    JTextField jTextFieldSaleAddress = new JTextField();
    JLabel labelActivity = new JLabel("Activity ");
    JTextField jTextFieldActivity = new JTextField();

    JLabel labelDiscountC = new JLabel("Discount Code *");

    JLabel labelCountryC = new JLabel("Country Code *");
    JComboBox<String> jComboBoxCountryC = new JComboBox<>(Locale.getISOCountries());
    jComboBoxCountryC.setSelectedIndex(-1);

    JPanel panel = new JPanel();

    panel.add(labelCode);
    panel.add(jTextFieldCode);
    panel.add(labelName);
    panel.add(jTextFieldName);
    panel.add(labelPrincipalAddress);
    panel.add(jTextFieldPrincipalAddress);
    panel.add(labelSaleAddress);
    panel.add(jTextFieldSaleAddress);
    panel.add(labelActivity);
    panel.add(jTextFieldActivity);

    panel.add(labelCountryC);
    panel.add(jComboBoxCountryC);
    panel.setLayout(new GridLayout(8, 2, 1, 3));

    for (Component c : panel.getComponents()) {
        c.setFont(new Font("", Font.PLAIN, 14));
        c.setPreferredSize(new Dimension(150, 25));
    }

    if (JOptionPane.showConfirmDialog(this, panel, "New Customer",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {


            Customer newCustomer = new Customer();
            newCustomer.setCode(jTextFieldCode.getText());
            newCustomer.setName(jTextFieldName.getText());
            newCustomer.setPrincipalAddress(labelPrincipalAddresss.getText());
            newCustomer.setShippingAddress(jTextFieldSaleAddress.getText());
            newCustomer.setActivity(jTextFieldActivity.getText());
             newCustomer.setCountry_code((String) jComboBoxCountryC.getSelectedItem());

            MainFrame.customerSBRemote.addCustomer(newCustomer);
            customersList.add(newCustomer);
            this.customer = newCustomer;
            jTableCustomer.changeSelection(customersList.indexOf(newCustomer), 0, false, false);
            this.hashcode = customer.hashCode();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }

}
*/