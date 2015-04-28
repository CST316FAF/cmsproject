package crm_faf;
	
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Popup;


public class Technician extends TransitionScene{

        final TextField firstnameField = new TextField();
        final TextField lastnameField = new TextField();
        final TextField phoneField = new TextField();
        final TextField emailField = new TextField();
        final TextField locationField = new TextField();
        final TextField stateField = new TextField();
        final TextField cityField = new TextField();
        final TextField zipField = new TextField();
        final TextField techIDField = new TextField();
	
        private WindowTools toolbar;
        private WindowToolbar bar;
        VBox windowTopBox = new VBox();
    
        ResultSet myResultSet;
        Statement myStatement;
        Stage primaryStage = new Stage();
        
        public void start(Stage primaryStage) {
		
                primaryStage = this.primaryStage;
		primaryStage.setTitle("Add Technician Form");
		BorderPane pane2 = new BorderPane();
                GridPane pane = new GridPane();
                
                pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(25, 25, 25, 25));
		Scene scene = new Scene(pane2, 600, 600);
		
                
                toolbar = new WindowTools();
                bar = new WindowToolbar(scene, primaryStage);
                windowTopBox.getChildren().addAll(bar, toolbar);
                pane2.setTop(windowTopBox);
                
                
		Text sceneTitle = new Text("Enter New Technician Information:");
		sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL,20));
		pane.add(sceneTitle, 0, 0, 2, 1);
                
                pane.add(firstnameField, 1, 1);
		Label firstName = new Label("First name:");
		pane.add(firstName, 0, 1);
		
		pane.add(lastnameField, 1, 2);
		Label lastName = new Label("Last name:");
		pane.add(lastName, 0, 2);
		
		pane.add(phoneField, 1, 3);
		Label phone = new Label("Phone Number:");
		pane.add(phone, 0, 3);
		
		pane.add(emailField, 1, 4);
		Label email = new Label("Email:");
		pane.add(email, 0, 4);
		
                pane.add(locationField, 1, 5);
		Label location = new Label("Location:");
		pane.add(location, 0, 5);
                
                pane.add(cityField, 1, 6);
		Label city = new Label("City:");
		pane.add(city, 0, 6);
                
                pane.add(stateField, 1, 7);
		Label state = new Label("State:");
		pane.add(state, 0, 7);
                
                pane.add(zipField, 1, 8);
		Label zip = new Label("Zip Code:");
		pane.add(zip, 0, 8);
                
                pane.add(techIDField, 1, 9);
                Label techID = new Label("Tech ID:");
                pane.add(techID, 0, 9);
		
		Button submitButton = new Button("Submit Info");
		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.BOTTOM_RIGHT);
		hbox.getChildren().add(submitButton);
		pane.add(hbox, 1, 10);
		
		
		final Text submitMessage = new Text();
		pane.add(submitMessage, 1, 10);
		//HBox box = new HBox();
                //box.getChildren().add(pane);
                pane2.setCenter(pane);
                
		submitButton.setOnAction(new EventHandler<ActionEvent>() {
			
			
			
			@Override
			public void handle(ActionEvent event) {
			// TODO : Add if statement to test for each TextField
                            String techFName = firstnameField.getText();
                            String techLName = lastnameField.getText();
                            String techPhone = phoneField.getText();
                            String techEmail = emailField.getText();
                            String techLocation = locationField.getText();
                            String techState = stateField.getText();
                            String techCity = cityField.getText();
                            String techZip = zipField.getText();
                            String techID = techIDField.getText();
                            String jobsID = "0";
                            String P_ID = "1";
                            String type = "None";
                            String appointment = "0000-00-00";
                            Label errormsg = new Label("Fill out all");
     
                //Check and Validate that all fields are entered.
                if(techFName.equals("") || techLName.equals("") || techPhone.equals("") || techEmail.equals("") 
                        || techLocation.equals("") || techState.equals("") || techCity.equals("")
                        || techZip.equals("") || techID.equals("")) {
                    
                        final Popup popup = new Popup(); 
                        popup.setX(300); popup.setY(200);
                        popup.getContent().add(new HBox(8));
                        HBox hbox = new HBox(8); // spacing = 8
                        hbox.getChildren().add(errormsg);
                        //popup.show(primaryStage);
                        submitMessage.setText("Please fill in all fields.");
                        }
                
                        else {
                            try {
                                //DbConnection connection = new DbConnection();
                                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cmsdb",
                                "root", "");
                                Statement myStatement = connection.createStatement();
                                String checkSQL = "select * from technician"; 
                                
                                ResultSet rs = myStatement.executeQuery(checkSQL);
                               
                                System.out.println("YOUUUU MADEEE IT!" + techFName + techLName);
                                while(rs.next()) {
                                    System.out.println(rs.getString("P_ID"));
                                    if(rs.getString("techID").equals(techID)) {
                                        System.out.println("There is already a technician with that ID number");
                                    } else {
                                        System.out.println("Add the new technician");
                                        
                                        myStatement.execute("insert into technician (P_ID, techID, jobsID, techFName, "
                                                + "techLName, techPhone, techemail, Location, tstate, tcity, tzip, Type, Appointment) "
                                        + "values ('"+P_ID+"','"+techID+"','"+jobsID+"','"+techFName+"','"+techLName+"','"+techPhone+"',"
                                                + "'"+techEmail+"','"+techLocation+"','"+techState+"','"+techCity+"','"+techZip+"'"
                                                + ",'"+type+"','"+appointment+"')");
                                        
                                        System.out.println("Successfully added job!");
                                    }
                                    
                                } 
                               
                            } catch (SQLException ex) {
                                Logger.getLogger(CustomerForm.class.getName()).log(Level.SEVERE, null, ex);
                            }
                         
                        }
                
                    }
            });
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
        //isDigit Method checks to see if entered parameter is an Int or not.
	public static boolean isDigit(String numberToCheck) {
            try {
                Integer.parseInt(numberToCheck);
                
            } catch (NumberFormatException e) {
                
                return false;
            }
            return true;
        }
        
        private int generateId() {
            int idNum = -1;
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cmsdb",
                                    "root", "");
                Random rand = new Random();
                boolean generated = false;
                idNum = rand.nextInt((1000 - 1) + 1) + 1;
            
                while(!generated) {
                    Statement myStatement = connection.createStatement();
                    ResultSet rs = myStatement.executeQuery("SELECT 1 FROM jobs WHERE jobsID = \"" 
                    + idNum + "\" and P_ID = \"" + 1 + "\"");
                    if(!rs.first()) {
                    generated = true;
                    
                    }
                       
                }
                
            } catch (Exception ex) {
                Logger.getLogger(CustomerForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return idNum;
        }
}






//package crm_faf;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.geometry.Insets;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.SelectionMode;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.stage.Stage;
//
//public class Technician extends TransitionScene{
//
//	static final String DB_URL = "jdbc:mysql://localhost:3306/database1";
//	public static final String USER = "root";
//	public static final String PASSWORD = "Welcome1";
//        private VBox windowTopBox = new VBox();
//        private final BorderPane pane = new BorderPane();
//	private final HBox hb = new HBox();
//	private final HBox hb2 = new HBox();
//	private final TableView<Person> table = new TableView<>();
//	private ObservableList<Person> data = null;
//        private Button cancel;
//
//	private void buildTableData() {
//
//		ArrayList<Person> people = new ArrayList<Person>();
//
//		
//		String driverName = "com.mysql.jdbc.Driver";
//		try {
//			
//			Class.forName(driverName).newInstance();
//			Connection conn = DriverManager.getConnection(DB_URL, USER,
//					PASSWORD);
//
//			Statement stmt = conn.createStatement();
//			String theQuery = "SELECT * FROM technicians";
//
//			ResultSet rs = stmt.executeQuery(theQuery);
//
//			while (rs.next()) {
//				Person temp = new Person(rs.getString(1), rs.getString(2),
//						rs.getString(3), rs.getString(4), rs.getString(5),
//						rs.getString(6), rs.getString(7));
//				people.add(temp);
//			}
//
//			ObservableList<Person> oPeople = FXCollections
//					.observableArrayList(people);
//
//			data = oPeople;
//
//			conn.close();
//		} catch (Exception e) {
//			
//			System.out.println("Problem : " + e.getMessage());
//
//		}
//
//	}
//
//
//	public void start(Stage primaryStage, WindowTools tBar) {
//		try {
//			
//
//			buildTableData();
//
//			int addUpWidth = 50;
//
//			Scene scene = new Scene(new Group());
//			primaryStage.setHeight(650);
//			primaryStage.setWidth(1000);
//
//			final Label label = new Label("Technician List");
//			label.setFont(new Font("Arial", 20));
//			
//			Label message = new Label("");
//			message.setFont(new Font("Ariel", 20));
//			message.setTextFill(Color.RED);
//
//			table.setEditable(false);
//			table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//
//			TableColumn idCol = new TableColumn("ID");
//			idCol.setMinWidth(60);
//			idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
//			addUpWidth += idCol.getMinWidth();
//
//			TableColumn firstNameCol = new TableColumn("First Name");
//			firstNameCol.setMinWidth(120);
//			firstNameCol.setCellValueFactory(new PropertyValueFactory<>(
//					"firstName"));
//			addUpWidth += firstNameCol.getMinWidth();
//
//			TableColumn lastNameCol = new TableColumn("Last Name");
//			lastNameCol.setMinWidth(120);
//			lastNameCol.setCellValueFactory(new PropertyValueFactory<>(
//					"lastName"));
//			addUpWidth += lastNameCol.getMinWidth();
//
//			TableColumn phoneCol = new TableColumn("Phone");
//			phoneCol.setMinWidth(120);
//			phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
//			addUpWidth += phoneCol.getMinWidth();
//
//			TableColumn emailCol = new TableColumn("Email");
//			emailCol.setMinWidth(120);
//			emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
//			addUpWidth += emailCol.getMinWidth();
//
//			TableColumn contactCol = new TableColumn("Emergency Contact");
//			contactCol.setMinWidth(120);
//			contactCol
//					.setCellValueFactory(new PropertyValueFactory<>("contact"));
//			addUpWidth += contactCol.getMinWidth();
//
//			TableColumn workHoursCol = new TableColumn("Work Hours");
//			workHoursCol.setMinWidth(80);
//			workHoursCol.setCellValueFactory(new PropertyValueFactory<>(
//					"workHours"));
//			addUpWidth += workHoursCol.getMinWidth();
//
//			table.setItems(data);
//			table.getColumns().addAll(idCol, firstNameCol, lastNameCol,
//					phoneCol, emailCol, contactCol, workHoursCol);
//
//			final TextField addId = new TextField();
//			addId.setPromptText("ID");
//			addId.setPrefWidth(idCol.getMinWidth());
//			final TextField addFirstName = new TextField();
//			addFirstName.setPromptText("First Name");
//			addFirstName.setPrefWidth(firstNameCol.getMinWidth());
//			final TextField addLastName = new TextField();
//			addLastName.setPrefWidth(lastNameCol.getMinWidth());
//			addLastName.setPromptText("Last Name");
//			final TextField addPhone = new TextField();
//			addPhone.setPrefWidth(phoneCol.getMinWidth());
//			addPhone.setPromptText("Phone");
//			final TextField addEmail = new TextField();
//			addEmail.setPrefWidth(emailCol.getMinWidth());
//			addEmail.setPromptText("Email");
//			final TextField addContact = new TextField();
//			addContact.setPrefWidth(contactCol.getMinWidth());
//			addContact.setPromptText("Emergency Contact");
//			final TextField addWorkHours = new TextField();
//			addWorkHours.setPrefWidth(workHoursCol.getMinWidth());
//			addWorkHours.setPromptText("Work Hours");
//                        
//                        final Button cancel = new Button("Cancel");
//                        cancel.setMinWidth(50);
//                        cancel.setOnAction((ActionEvent e) -> {
//                            primaryStage.setScene(new StatusPage().start(primaryStage, new WindowTools()));
//                        });
//                        
//			final Button addButton = new Button("Add New Technician");
//			addButton.setMinWidth(50);
//			addButton.setOnAction((ActionEvent e) -> {
//                            
//                        
//                        
//				
//				
//				
//				if (!addId.getText().equals("")
//						&& !addFirstName.getText().equals("")
//						&& !addLastName.getText().equals("")
//						&& !addPhone.getText().equals("")
//						&& !addEmail.getText().equals("")
//						&& !addContact.getText().equals("")
//						&& !addWorkHours.getText().equals("")) {
//					
//					for(Person p : data) {
//						
//						if(p.getId().equals(addId.getText())) {
//							message.setText("Id Already Exists, make sure the ID is unique before adding a new tech");
//							return;
//						}
//					}
//
//					String driverName = "com.mysql.jdbc.Driver"; // for MySql
//					try {
//						
//						Class.forName(driverName);
//						Connection conn = DriverManager.getConnection(DB_URL,
//								USER, PASSWORD);
//
//						Statement stmt = conn.createStatement();
//						String theQuery = "INSERT INTO technicians VALUES (";
//
//						theQuery += addId.getText() + ",'"
//								+ addFirstName.getText() + "','"
//								+ addLastName.getText() + "','"
//								+ addPhone.getText() + "','"
//								+ addEmail.getText() + "','"
//								+ addContact.getText() + "','"
//								+ addWorkHours.getText() + "')";
//
//						stmt.execute(theQuery);
//
//						conn.close();
//
//				
//				} catch (Exception ee) {
//				
//					System.out.println("Problem : " + ee.getMessage());
//
//				}
//
//				data.add(new Person(addId.getText(), addFirstName.getText(),
//						addLastName.getText(), addPhone.getText(), addEmail
//								.getText(), addContact.getText(), addWorkHours
//								.getText()));
//				addId.clear();
//				addFirstName.clear();
//				addLastName.clear();
//				addPhone.clear();
//				addEmail.clear();
//				addContact.clear();
//				addWorkHours.clear();
//				message.setText("");
//
//			}else {
//				message.setText("Information was not updated: make sure all fields are filled out");
//			}
//		})	;
//
//			final Label addId1 = new Label();
//			addId1.setText("ID");
//			addId1.setPrefWidth(idCol.getMinWidth());
//			addId1.setPrefHeight(25);
//			final TextField addFirstName1 = new TextField();
//			addFirstName1.setPromptText("First Name");
//			addFirstName1.setPrefWidth(firstNameCol.getMinWidth());
//			final TextField addLastName1 = new TextField();
//			addLastName1.setPrefWidth(lastNameCol.getMinWidth());
//			addLastName1.setPromptText("Last Name");
//			final TextField addPhone1 = new TextField();
//			addPhone1.setPrefWidth(phoneCol.getMinWidth());
//			addPhone1.setPromptText("Phone");
//			final TextField addEmail1 = new TextField();
//			addEmail1.setPrefWidth(emailCol.getMinWidth());
//			addEmail1.setPromptText("Email");
//			final TextField addContact1 = new TextField();
//			addContact1.setPrefWidth(contactCol.getMinWidth());
//			addContact1.setPromptText("Emergency Contact");
//			final TextField addWorkHours1 = new TextField();
//			addWorkHours1.setPrefWidth(workHoursCol.getMinWidth());
//			addWorkHours1.setPromptText("Work Hours");
//
//			final Button editButton = new Button("Update Selected");
//			editButton.setMinWidth(50);
//			editButton.setOnAction((ActionEvent e) -> {
//
//				
//				if (!addFirstName1.getText().equals("")
//						&& !addLastName1.getText().equals("")
//						&& !addPhone1.getText().equals("")
//						&& !addEmail1.getText().equals("")
//						&& !addContact1.getText().equals("")
//						&& !addWorkHours1.getText().equals("")) {
//
//					String driverName = "com.mysql.jdbc.Driver"; // for MySql
//					try {
//						// Load the JDBC driver
//						Class.forName(driverName);
//						Connection conn = DriverManager.getConnection(DB_URL,
//								USER, PASSWORD);
//
//						Statement stmt = conn.createStatement();
//						String theQuery = "REPLACE INTO technicians VALUES (";
//
//						theQuery += addId1.getText() + ",'"
//								+ addFirstName1.getText() + "','"
//								+ addLastName1.getText() + "','"
//								+ addPhone1.getText() + "','"
//								+ addEmail1.getText() + "','"
//								+ addContact1.getText() + "','"
//								+ addWorkHours1.getText() + "')";
//
//						stmt.execute(theQuery);
//
//						conn.close();
//
//
//
//				} catch (Exception ee) {
//					
//					System.out.println("Problem : " + ee.getMessage());
//
//				}
//
//					int rowSelected = table.getSelectionModel().getSelectedIndex();
//					
//				data.set(rowSelected, (new Person(addId1.getText(), addFirstName1.getText(),
//						addLastName1.getText(), addPhone1.getText(), addEmail1
//								.getText(), addContact1.getText(), addWorkHours1
//								.getText())));
//				message.setText("");
//			}else {
//				message.setText("Information was not updated: make sure all update fields are filled out");
//			}
//				
//				
//			});
//
//			final Button deleteButton = new Button("Delete Selected");
//			deleteButton.setMinWidth(50);
//			deleteButton.setOnAction((ActionEvent e) -> {
//				if (table.getSelectionModel().getSelectedItem() != null) {
//
//					String driverName = "com.mysql.jdbc.Driver"; 
//					try {
//					
//						Class.forName(driverName).newInstance();
//						Connection conn = DriverManager.getConnection(DB_URL,
//								USER, PASSWORD);
//
//						
//					String theQuery = "DELETE FROM technicians WHERE ID = ?";
//					PreparedStatement ps = conn.prepareStatement(theQuery);
//					ps.setString(1, ((Person) table.getSelectionModel()
//							.getSelectedItem()).getId());
//					ps.execute();
//
//					conn.close();
//				} catch (Exception ee) {
//					
//					System.out.println("Problem : " + ee.getMessage());
//
//				}
//
//				int rowSelected = table.getSelectionModel().getSelectedIndex();
//				data.remove(rowSelected);
//				
//				message.setText("");
//
//			}
//
//		})	;
//
//			hb.getChildren().addAll(addId, addFirstName, addLastName, addPhone,
//					addEmail, addContact, addWorkHours, addButton);
//
//			hb.setSpacing(0);
//
//			hb2.getChildren().addAll(addId1, addFirstName1, addLastName1,
//					addPhone1, addEmail1, addContact1, addWorkHours1,
//					editButton);
//
//			hb2.setSpacing(0);
//
//			final VBox vbox = new VBox();
//			vbox.setSpacing(5);
//			vbox.setPadding(new Insets(10, 0, 0, 10));
//			vbox.getChildren().addAll(label, hb, table, hb2, deleteButton, message);
//                        
//                        
//                        WindowToolbar bar = new WindowToolbar(scene, primaryStage);
//                        bar.setToolbar(tBar);
//                        windowTopBox.getChildren().addAll(bar, tBar);
//                        pane.setTop(windowTopBox);
//                        pane.setCenter(vbox);
//			((Group) scene.getRoot()).getChildren().addAll(pane);
//                        
//			addUpWidth += addButton.getMinWidth();
//			addUpWidth += editButton.getMinWidth();
//			addUpWidth += deleteButton.getMinWidth();
//
//			table.getSelectionModel().selectedItemProperty()
//					.addListener(new ChangeListener() {
//
//						@Override
//						public void changed(ObservableValue observable,
//								Object oldValue, Object newValue) {
//							// TODO Auto-generated method stub
//
//							if (table.getSelectionModel().getSelectedItem() != null) {
//
//								addId1.setText(((Person) table
//										.getSelectionModel().getSelectedItem())
//										.getId());
//								addFirstName1.setText(((Person) table
//										.getSelectionModel().getSelectedItem())
//										.getFirstName());
//								addLastName1.setText(((Person) table
//										.getSelectionModel().getSelectedItem())
//										.getLastName());
//								addPhone1.setText(((Person) table
//										.getSelectionModel().getSelectedItem())
//										.getPhone());
//								addEmail1.setText(((Person) table
//										.getSelectionModel().getSelectedItem())
//										.getEmail());
//								addContact1.setText(((Person) table
//										.getSelectionModel().getSelectedItem())
//										.getContact());
//								addWorkHours1.setText(((Person) table
//										.getSelectionModel().getSelectedItem())
//										.getworkHours());
//								
//
//							} else {
//								addId1.setText("ID");
//								addFirstName1.clear();
//								addLastName1.clear();
//								addPhone1.clear();
//								addEmail1.clear();
//								addContact1.clear();
//								addWorkHours1.clear();
//							}
//							message.setText("");
//						}
//					});
//
//			
//
//			primaryStage.setScene(scene);
//			primaryStage.show();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static class Person {
//		private final SimpleStringProperty id;
//		private final SimpleStringProperty firstName;
//		private final SimpleStringProperty lastName;
//		private final SimpleStringProperty phone;
//		private final SimpleStringProperty email;
//		private final SimpleStringProperty contact;
//		private final SimpleStringProperty workHours;
//
//		public SimpleStringProperty idProperty() {
//			return id;
//		}
//
//		public SimpleStringProperty firstNameProperty() {
//			return firstName;
//		}
//
//		public SimpleStringProperty lastNameProperty() {
//			return lastName;
//		}
//
//		public SimpleStringProperty phoneProperty() {
//			return phone;
//		}
//
//		public SimpleStringProperty emailProperty() {
//			return email;
//		}
//
//		public SimpleStringProperty contactProperty() {
//			return contact;
//		}
//
//		public SimpleStringProperty workHoursProperty() {
//			return workHours;
//		}
//
//		public void setId(String theId) {
//			id.set(theId);
//		}
//
//		public void setFirstName(String fName) {
//			firstName.set(fName);
//		}
//
//		public void setLastName(String lName) {
//			lastName.set(lName);
//		}
//
//		public void setPhone(String p) {
//			phone.set(p);
//		}
//
//		public void setEmail(String e) {
//			email.set(e);
//		}
//
//		public void setContact(String c) {
//			contact.set(c);
//		}
//
//		public void setworkHours(String ht) {
//			workHours.set(ht);
//		}
//
//		public String getId() {
//			return id.get();
//		}
//
//		public String getFirstName() {
//			return firstName.get();
//		}
//
//		public String getLastName() {
//			return lastName.get();
//		}
//
//		public String getPhone() {
//			return phone.get();
//		}
//
//		public String getEmail() {
//			return email.get();
//		}
//
//		public String getContact() {
//			return contact.get();
//		}
//
//		public String getworkHours() {
//			return workHours.get();
//		}
//
//		private Person(String id, String fName, String lName, String phone,
//				String email, String contact, String workHours) {
//			this.id = new SimpleStringProperty(id);
//			this.firstName = new SimpleStringProperty(fName);
//			this.lastName = new SimpleStringProperty(lName);
//			this.phone = new SimpleStringProperty(phone);
//			this.email = new SimpleStringProperty(email);
//			this.contact = new SimpleStringProperty(contact);
//			this.workHours = new SimpleStringProperty(workHours);
//		}
//
//	}
//
//}
