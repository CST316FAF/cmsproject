
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.sql.*;

public class MainPanel extends JPanel{

	static final String DB_URL = "jdbc:mysql://localhost:3306/database";
	public static final String USER = "root";
    public static final String PASSWORD = "password";
	
	JTextField nameTF;
	JTextField addyTF;
	JTextField phoneTF;
	JTextField custNameTF;
	JTextField custAddyTF;
	JTextField custPhoneTF;
	JTextField sTF;
	JTextField rTF;
	JTextField cTF;
	JTextField iTF;
	JTextField invoiceNumberSearchTF;
	JButton addInvoiceB;
	JButton loadInvoiceB;
	//JTextArea invoicesTA;
	
	public MainPanel(){
		
		setLayout(new GridLayout(10,1));
		
		JPanel filler = new JPanel();
		filler.setSize(10,10);

		JPanel companyDetailsInfoJP = new JPanel();
		companyDetailsInfoJP.setLayout(new GridLayout(3,2));
		nameTF = new JTextField();
		addyTF = new JTextField();
		phoneTF = new JTextField();
		companyDetailsInfoJP.add(new JLabel("Name"));
		companyDetailsInfoJP.add(nameTF);
		companyDetailsInfoJP.add(new JLabel("Address"));
		companyDetailsInfoJP.add(addyTF);
		companyDetailsInfoJP.add(new JLabel("Phone"));
		companyDetailsInfoJP.add(phoneTF);
		
		
		
		
		
		JPanel customerDetailsInfoJP = new JPanel();
		customerDetailsInfoJP.setLayout(new GridLayout(3,2));
		custNameTF = new JTextField();
		custAddyTF = new JTextField();
		custPhoneTF = new JTextField();
		customerDetailsInfoJP.add(new JLabel("Name"));
		customerDetailsInfoJP.add(custNameTF);
		customerDetailsInfoJP.add(new JLabel("Address"));
		customerDetailsInfoJP.add(custAddyTF);
		customerDetailsInfoJP.add(new JLabel("Phone"));
		customerDetailsInfoJP.add(custPhoneTF);
	
		
		
		JPanel serviceInformationJP = new JPanel();
		serviceInformationJP.setLayout(new GridLayout(4,2));
		sTF = new JTextField();
		rTF = new JTextField();
		cTF = new JTextField();
		iTF = new JTextField();
		serviceInformationJP.add(new JLabel("Service Provided"));
		serviceInformationJP.add(sTF);
		serviceInformationJP.add(new JLabel("Hourly Rate"));
		serviceInformationJP.add(rTF);
		serviceInformationJP.add(new JLabel("Total Cost"));	
		serviceInformationJP.add(cTF);
		serviceInformationJP.add(new JLabel("Invoice Number"));
		serviceInformationJP.add(iTF);
		
		
		
		addInvoiceB = new JButton("Add Invoice");
		
		addInvoiceB.addMouseListener(new MouseListener (){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				 String driverName = "com.mysql.jdbc.Driver"; //for MySql
				try {
				// Load the JDBC driver
				Class.forName(driverName);
				Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
				
				Statement stmt = conn.createStatement();
				String theQuery = "INSERT INTO invoices VALUES (";
				theQuery+=iTF.getText()+",'"+nameTF.getText()+"','"+addyTF.getText();
				theQuery+="','"+phoneTF.getText()+"','"+custNameTF.getText()+"','"+custAddyTF.getText()+"','";
				theQuery+=custPhoneTF.getText()+"','"+sTF.getText()+"','"+rTF.getText()+"','"+cTF.getText()+"')";
				
				stmt.execute(theQuery);
				
				conn.close();
				} catch (Exception e) {
				// Could not find the database driver
				System.out.println("Problem : "+e.getMessage());
				
				}
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			
		
		});

		
		//invoicesTA = new JTextArea();
		
		
		JPanel invoicesSearchJP = new JPanel();
		
		invoicesSearchJP.setLayout(new GridLayout(1,3));
		invoicesSearchJP.add(new JLabel("Invoice Number"));
		invoiceNumberSearchTF = new JTextField();
		invoicesSearchJP.add(invoiceNumberSearchTF);
		loadInvoiceB = new JButton("Load");
		
		loadInvoiceB.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				 String driverName = "com.mysql.jdbc.Driver"; //for MySql
				try {
				// Load the JDBC driver
				Class.forName(driverName).newInstance();
				Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
				
				Statement stmt = conn.createStatement();
				String theQuery = "SELECT * FROM invoices WHERE ID = " + invoiceNumberSearchTF.getText();

				ResultSet rs = stmt.executeQuery(theQuery);
				
				if(rs.next()){
					nameTF.setText(rs.getString(2));
					 addyTF.setText(rs.getString(3));
					 phoneTF.setText(rs.getString(4));
					 custNameTF.setText(rs.getString(5));
					 custAddyTF.setText(rs.getString(6));
					 custPhoneTF.setText(rs.getString(7));
					 sTF.setText(rs.getString(8));
					 rTF.setText(rs.getString(9));
					 cTF.setText(rs.getString(10));
					 iTF.setText(rs.getString(1));

				}
				
				conn.close();
				} catch (Exception e) {
				// Could not find the database driver
				System.out.println("Problem : "+e.getMessage());
				
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}});
		
		invoicesSearchJP.add(loadInvoiceB);
		
		add(new JLabel("Invoices"));
		//add(filler);
		add(new JLabel("Company Details"));
		add(companyDetailsInfoJP);
		add(new JLabel("Customer Details"));
		add(customerDetailsInfoJP);		
		add(new JLabel("Service Information"));
		add(serviceInformationJP);
		
		add(addInvoiceB);
		
		//add(invoicesTA);
		
		add(invoicesSearchJP);
		


		
		
	}
	
}
