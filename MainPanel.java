import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel{

	JTextField cardNumberTF,expMonthTF,expYearTF, cvvTF, nameTF;
	JTextField addressTF, cityTF, stateTF, zipTF, phoneTF;
	JComboBox countryCB, stateCB;
	JButton payB, cancelB;
	
	JRadioButton visaRB,  mastercardRB, discoverRB;
	
	ButtonGroup cardsButtonGroup;
	
	JLabel invoiceL, taxL, totalAmountL, invoiceAmountL;
	
	JPanel left,right;
	
	GridBagLayout gbl;
	
	String[] countryList = {"USA","Canada","UK","Saudi Arabia"};
	String[] usaStates = {"Alabama","Alaska","Arizona"};
	
	public MainPanel(){
		
		setLayout(new GridBagLayout());
		
		JPanel top = new JPanel();
		top.setLayout(new GridLayout(1,2));
		
		gbl = new GridBagLayout(); 

		left = new JPanel();
		left.setLayout(gbl);
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		// ------------------------------------------
		
		JLabel temp = new JLabel("Enter Credit Card Details");
		temp.setForeground(Color.RED);
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbl.setConstraints(temp, gbc);
		left.add(temp);

		temp = new JLabel("* = Mandatory Fields");
		gbc.anchor = GridBagConstraints.NORTHEAST;
		gbc.gridx = 1;
		gbc.gridwidth = 3;
		gbl.setConstraints(temp, gbc);
		left.add(temp);
		
		// ------------------------------------------

		temp = new JLabel("* Card Number");
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbl.setConstraints(temp, gbc);
		left.add(temp);
		
		cardNumberTF = new JTextField();
		gbc.gridx = 1;
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbl.setConstraints(cardNumberTF, gbc);
		left.add(cardNumberTF);
		
		// ------------------------------------------
		
		temp = new JLabel("* Card Type");
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbl.setConstraints(temp, gbc);
		left.add(temp);

		temp = new JLabel("Visa");
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbl.setConstraints(temp, gbc);
		left.add(temp);
		
		temp = new JLabel("Mastercard");
		gbc.gridx = 2;
		gbl.setConstraints(temp, gbc);
		left.add(temp);
		
		temp = new JLabel("Discover");
		gbc.gridx = 3;
		gbl.setConstraints(temp, gbc);
		left.add(temp);
		
		// ------------------------------------------
		
		visaRB = new JRadioButton();
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbl.setConstraints(visaRB, gbc);
		left.add(visaRB);
		
		mastercardRB = new JRadioButton();
		gbc.gridx = 2;
		gbl.setConstraints(mastercardRB, gbc);
		left.add(mastercardRB);
		
		discoverRB = new JRadioButton();
		gbc.gridx = 3;
		gbl.setConstraints(discoverRB, gbc);
		left.add(discoverRB);
		
		cardsButtonGroup = new ButtonGroup();
		cardsButtonGroup.add(visaRB);
		cardsButtonGroup.add(mastercardRB);
		cardsButtonGroup.add(discoverRB);
		
		// ------------------------------------------
		
		temp = new JLabel("* Experiation Date");
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbl.setConstraints(temp, gbc);
		left.add(temp);
		
		JPanel myP = new JPanel(new GridBagLayout());
		
		expMonthTF = new JTextField();
		expMonthTF.setPreferredSize(new Dimension(50,20));
		myP.add(expMonthTF);
		
		temp = new JLabel("  /  ");
		myP.add(temp);
		
		expYearTF = new JTextField();
		expYearTF.setPreferredSize(new Dimension(50,20));
		myP.add(expYearTF);
		
		gbc.gridx = 1;
		gbc.gridwidth = 3;
		gbl.setConstraints(myP, gbc);		
		left.add(myP);
		
		temp = new JLabel("mm");
		gbc.gridx = 0;
		gbc.gridy = 1;
		((GridBagLayout)myP.getLayout()).setConstraints(temp,gbc);
		myP.add(temp);
		
		temp = new JLabel("yy");
		gbc.gridx = 2;
		((GridBagLayout)myP.getLayout()).setConstraints(temp,gbc);
		myP.add(temp);
		
		// ------------------------------------------
		
		temp = new JLabel("* CVV");
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbl.setConstraints(temp, gbc);
		left.add(temp);
		
		cardNumberTF = new JTextField();
		cardNumberTF.setPreferredSize(new Dimension(50,20));
		gbc.gridx = 1;
		gbc.gridwidth = 1;
		//gbc.fill = GridBagConstraints.HORIZONTAL;
		gbl.setConstraints(cardNumberTF, gbc);
		left.add(cardNumberTF);
		
		// ------------------------------------------
		
		temp = new JLabel("* Cardholder name");
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 1;
		gbl.setConstraints(temp, gbc);
		left.add(temp);
		
		nameTF = new JTextField();
		gbc.gridx = 1;
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets.bottom = 20;
		gbl.setConstraints(nameTF, gbc);
		left.add(nameTF);
		//reset inset
		gbc.insets.bottom = 0;
		
		// ------------------------------------------
		
		temp = new JLabel("Billing Information");
		temp.setForeground(Color.RED);
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 1;
		gbl.setConstraints(temp, gbc);
		left.add(temp);
		
		// ------------------------------------------
		
		temp = new JLabel("* Address");
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbl.setConstraints(temp, gbc);
		left.add(temp);
		
		addressTF = new JTextField();
		gbc.gridx = 1;
		gbc.gridwidth = 3;
		gbl.setConstraints(addressTF, gbc);
		left.add(addressTF);
		
		// ------------------------------------------
		
		temp = new JLabel("* City");
		gbc.gridx = 0;
		gbc.gridy = 9;
		gbl.setConstraints(temp, gbc);
		left.add(temp);
		
		cityTF = new JTextField();
		gbc.gridx = 1;
		gbc.gridwidth = 3;
		gbl.setConstraints(cityTF, gbc);
		left.add(cityTF);
		
		
		// ------------------------------------------
		
		temp = new JLabel("* Country");
		gbc.gridx = 0;
		gbc.gridy = 10;
		gbl.setConstraints(temp, gbc);
		left.add(temp);
		
		countryCB = new JComboBox(countryList);
		gbc.gridx = 1;
		gbc.gridwidth = 3;
		gbl.setConstraints(countryCB, gbc);
		left.add(countryCB);
		
		// ------------------------------------------
		
		temp = new JLabel("* State");
		gbc.gridx = 0;
		gbc.gridy = 11;
		gbl.setConstraints(temp, gbc);
		left.add(temp);
		
		stateCB = new JComboBox(usaStates);
		gbc.gridx = 1;
		gbc.gridwidth = 3;
		gbl.setConstraints(stateCB, gbc);
		left.add(stateCB);
		
		// ------------------------------------------
		
		temp = new JLabel("* Zip");
		gbc.gridx = 0;
		gbc.gridy = 12;
		gbl.setConstraints(temp, gbc);
		left.add(temp);
		
		zipTF = new JTextField();
		gbc.gridx = 1;
		gbc.gridwidth = 3;
		gbl.setConstraints(zipTF, gbc);
		left.add(zipTF);
		
		// ------------------------------------------
		
		temp = new JLabel("* Phone");
		gbc.gridx = 0;
		gbc.gridy = 13;
		gbl.setConstraints(temp, gbc);
		left.add(temp);
		
		phoneTF = new JTextField();
		gbc.gridwidth = 3;
		gbc.gridx = 1;
		gbl.setConstraints(phoneTF, gbc);
		left.add(phoneTF);
		
		// ------------------------------------------
		JPanel filler = new JPanel();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridy = GridBagConstraints.LAST_LINE_END;
		gbc.gridheight = GridBagConstraints.REMAINDER;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbl.setConstraints(filler, gbc);
		left.add(filler);
		
		// ------------------------------------------
		// ------------------------------------------
		
		right = new JPanel();
		GridBagLayout gblr = new GridBagLayout();
		gbc = new GridBagConstraints();
		right.setLayout(gblr);
		
		// ------------------------------------------
		
		temp = new JLabel("Payment Details");
		temp.setForeground(Color.RED);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets.left = 20;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 0;
		//gbc.gridwidth = 4;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gblr.setConstraints(temp, gbc);
		right.add(temp);
		
		// ------------------------------------------
		
		temp = new JLabel("Invoice #");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gblr.setConstraints(temp, gbc);
		right.add(temp);
		
		invoiceL = new JLabel("XXXX");
		//gbc.anchor = GridBagConstraints.WEST;
		gbc.insets.left = 0;
		gbc.gridx = 2;
		gblr.setConstraints(invoiceL, gbc);
		right.add(invoiceL);
		
		invoiceAmountL = new JLabel("$0.00");
		gbc.gridx = 3;
		gbc.anchor = GridBagConstraints.EAST;
		gblr.setConstraints(invoiceAmountL, gbc);
		right.add(invoiceAmountL);
		
		// ------------------------------------------
		
		temp = new JLabel("Total tax:");
		gbc.insets.left = 20;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gblr.setConstraints(temp, gbc);
		right.add(temp);
		
		taxL = new JLabel("$0.00");
		gbc.insets.left = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.gridx = 3;
		gblr.setConstraints(taxL, gbc);
		right.add(taxL);
		
		// ------------------------------------------
		
		temp = new JLabel("Total amount:");
		gbc.insets.left = 20;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gblr.setConstraints(temp, gbc);
		right.add(temp);
		
		totalAmountL = new JLabel("$0.00");
		gbc.insets.left = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.gridx = 3;
		gblr.setConstraints(totalAmountL, gbc);
		right.add(totalAmountL);
		
		// ------------------------------------------
		
		filler = new JPanel();
		//filler.setBackground(Color.blue);
		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridy = GridBagConstraints.LAST_LINE_END;
		gbc.gridwidth = 2;
		gbc.gridheight = GridBagConstraints.REMAINDER;
		gbc.weighty = 1;
		gblr.setConstraints(filler, gbc);
		right.add(filler);
		
		top.add(left);
		top.add(right);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		((GridBagLayout)getLayout()).setConstraints(top,gbc);
		add(top);
		
		JPanel bottom = new JPanel();
		bottom.setLayout(new GridBagLayout());
		
		cancelB = new JButton("Cancel");
		cancelB.setPreferredSize(new Dimension(80,20));
		bottom.add(cancelB);
		payB = new JButton("Next");
		payB.setPreferredSize(new Dimension(80,20));
		bottom.add(payB);

		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.SOUTHEAST;
		((GridBagLayout)getLayout()).setConstraints(bottom,gbc);
		add(bottom);
	
	}
	
}
