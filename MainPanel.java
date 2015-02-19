import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel{

	JTextField cardNumberTF,expMonthTF,expYearTF, cvvTF, nameTF;
	JTextField addressTF, cityTF, stateTF, zipTF, phoneTF;
	JComboBox countryCB, stateCB;
	JButton payB, cancelB;
	
	JRadioButton visaRB,  mastercardRB, discoverRB;
	
	JLabel invoiceL, taxL, totalAmountL, invoiceAmountL;
	
	JPanel left,right;
	
	public MainPanel(){
		
		setLayout(new GridLayout(1,2));
		
		String empty = "";
		
		left = new JPanel();
		left.setLayout(new GridLayout(18,4));
		left.add(new JLabel("Enter Credit Card Details"));
		left.add(new JLabel(empty));
		left.add(new JLabel(empty));
		left.add(new JLabel("* = Mandatory Fields"));
		
		left.add(new JLabel("* Card Number"));
		cardNumberTF = new JTextField();
		left.add(cardNumberTF);
		left.add(new JLabel(empty));
		left.add(new JLabel(empty));
		
		left.add(new JLabel("* Card type"));
		left.add(new JLabel("Visa"));
		left.add(new JLabel("Mastercard"));
		left.add(new JLabel("Discover"));
		
		left.add(new JLabel(empty));
		visaRB = new JRadioButton();
		mastercardRB = new JRadioButton();
		discoverRB = new JRadioButton();
		left.add(visaRB);
		left.add(mastercardRB);
		left.add(discoverRB);
		
		left.add(new JLabel("* Experiation Date"));
		expMonthTF = new JTextField();
		expYearTF = new JTextField();
		left.add(expMonthTF);
		left.add(new JLabel("/"));
		left.add(expYearTF);
		
		left.add(new JLabel(empty));
		left.add(new JLabel("mm"));
		left.add(new JLabel(empty));
		left.add(new JLabel("yy"));
		
		left.add(new JLabel("* CVV"));
		cvvTF = new JTextField();
		left.add(cvvTF);
		left.add(new JLabel(empty));
		left.add(new JLabel(empty));
		
		left.add(new JLabel("Cardholder name"));
		nameTF = new JTextField();
		left.add(nameTF);
		left.add(new JLabel(empty));
		left.add(new JLabel(empty));
		
		left.add(new JLabel("Billing Information"));
		left.add(new JLabel(empty));
		left.add(new JLabel(empty));
		left.add(new JLabel(empty));
		
		left.add(new JLabel("* Address 1"));
		addressTF = new JTextField();
		left.add(addressTF);
		left.add(new JLabel(empty));
		left.add(new JLabel(empty));
		
		left.add(new JLabel("City"));
		cityTF = new JTextField();
		left.add(cityTF);
		left.add(new JLabel(empty));
		left.add(new JLabel(empty));
		
		left.add(new JLabel("* Country"));
		countryCB = new JComboBox();
		left.add(countryCB);
		left.add(new JLabel(empty));
		left.add(new JLabel(empty));
		
		left.add(new JLabel("* State"));
		stateCB = new JComboBox();
		left.add(stateCB);
		left.add(new JLabel(empty));
		left.add(new JLabel(empty));
		
		left.add(new JLabel("* Zip"));
		zipTF = new JTextField();
		left.add(zipTF);
		left.add(new JLabel(empty));
		left.add(new JLabel(empty));
		
		left.add(new JLabel("* Phone"));
		phoneTF = new JTextField();
		left.add(phoneTF);
		left.add(new JLabel(empty));
		left.add(new JLabel(empty));
		
		right = new JPanel();
		right.setLayout(new GridLayout(2,5));
		
		right.add(new JLabel("Payment details"));
		right.add(new JLabel(empty));
		
		invoiceL = new JLabel("Invoice");
		right.add(invoiceL);
		invoiceAmountL = new JLabel("0.00");
		right.add(invoiceAmountL);
		
		right.add(new JLabel("Total Tax:"));
		taxL = new JLabel("0.00");
		right.add(taxL);
		
		right.add(new JLabel("Total Amount:"));
		totalAmountL = new JLabel("0.00");
		right.add(totalAmountL);
		
		cancelB = new JButton("Cancel");
		right.add(cancelB);
		payB = new JButton("Pay");
		right.add(payB);
		
		add(left);
		add(right);
	
	}
	
}
