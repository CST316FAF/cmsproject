package crm_faf;

import java.awt.Dialog;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;
//import application.Main.Person;

public class Technician {

	static final String DB_URL = "jdbc:mysql://localhost:3306/database1";
	public static final String USER = "root";
	public static final String PASSWORD = "Welcome1";

	private final HBox hb = new HBox();
	private final HBox hb2 = new HBox();
	private final TableView<Person> table = new TableView<>();
	private ObservableList<Person> data = null;

	private void buildTableData() {

		ArrayList<Person> people = new ArrayList<Person>();

		
		String driverName = "com.mysql.jdbc.Driver";
		try {
			
			Class.forName(driverName).newInstance();
			Connection conn = DriverManager.getConnection(DB_URL, USER,
					PASSWORD);

			Statement stmt = conn.createStatement();
			String theQuery = "SELECT * FROM technicians";

			ResultSet rs = stmt.executeQuery(theQuery);

			while (rs.next()) {
				Person temp = new Person(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7));
				people.add(temp);
			}

			ObservableList<Person> oPeople = FXCollections
					.observableArrayList(people);

			data = oPeople;

			conn.close();
		} catch (Exception e) {
			
			System.out.println("Problem : " + e.getMessage());

		}

	}


	public void start(Stage primaryStage) {
		try {
			

			buildTableData();

			int addUpWidth = 50;

			Scene scene = new Scene(new Group());
			primaryStage.setHeight(650);
			primaryStage.setWidth(1000);

			final Label label = new Label("Technician List");
			label.setFont(new Font("Arial", 20));
			
			Label message = new Label("");
			message.setFont(new Font("Ariel", 20));
			message.setTextFill(Color.RED);

			table.setEditable(false);
			table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

			TableColumn idCol = new TableColumn("ID");
			idCol.setMinWidth(60);
			idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
			addUpWidth += idCol.getMinWidth();

			TableColumn firstNameCol = new TableColumn("First Name");
			firstNameCol.setMinWidth(120);
			firstNameCol.setCellValueFactory(new PropertyValueFactory<>(
					"firstName"));
			addUpWidth += firstNameCol.getMinWidth();

			TableColumn lastNameCol = new TableColumn("Last Name");
			lastNameCol.setMinWidth(120);
			lastNameCol.setCellValueFactory(new PropertyValueFactory<>(
					"lastName"));
			addUpWidth += lastNameCol.getMinWidth();

			TableColumn phoneCol = new TableColumn("Phone");
			phoneCol.setMinWidth(120);
			phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
			addUpWidth += phoneCol.getMinWidth();

			TableColumn emailCol = new TableColumn("Email");
			emailCol.setMinWidth(120);
			emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
			addUpWidth += emailCol.getMinWidth();

			TableColumn contactCol = new TableColumn("Emergency Contact");
			contactCol.setMinWidth(120);
			contactCol
					.setCellValueFactory(new PropertyValueFactory<>("contact"));
			addUpWidth += contactCol.getMinWidth();

			TableColumn workHoursCol = new TableColumn("Work Hours");
			workHoursCol.setMinWidth(80);
			workHoursCol.setCellValueFactory(new PropertyValueFactory<>(
					"workHours"));
			addUpWidth += workHoursCol.getMinWidth();

			table.setItems(data);
			table.getColumns().addAll(idCol, firstNameCol, lastNameCol,
					phoneCol, emailCol, contactCol, workHoursCol);

			final TextField addId = new TextField();
			addId.setPromptText("ID");
			addId.setPrefWidth(idCol.getMinWidth());
			final TextField addFirstName = new TextField();
			addFirstName.setPromptText("First Name");
			addFirstName.setPrefWidth(firstNameCol.getMinWidth());
			final TextField addLastName = new TextField();
			addLastName.setPrefWidth(lastNameCol.getMinWidth());
			addLastName.setPromptText("Last Name");
			final TextField addPhone = new TextField();
			addPhone.setPrefWidth(phoneCol.getMinWidth());
			addPhone.setPromptText("Phone");
			final TextField addEmail = new TextField();
			addEmail.setPrefWidth(emailCol.getMinWidth());
			addEmail.setPromptText("Email");
			final TextField addContact = new TextField();
			addContact.setPrefWidth(contactCol.getMinWidth());
			addContact.setPromptText("Emergency Contact");
			final TextField addWorkHours = new TextField();
			addWorkHours.setPrefWidth(workHoursCol.getMinWidth());
			addWorkHours.setPromptText("Work Hours");

			final Button addButton = new Button("Add New Technician");
			addButton.setMinWidth(50);
			addButton.setOnAction((ActionEvent e) -> {
				
				
				
				if (!addId.getText().equals("")
						&& !addFirstName.getText().equals("")
						&& !addLastName.getText().equals("")
						&& !addPhone.getText().equals("")
						&& !addEmail.getText().equals("")
						&& !addContact.getText().equals("")
						&& !addWorkHours.getText().equals("")) {
					
					for(Person p : data) {
						
						if(p.getId().equals(addId.getText())) {
							message.setText("Id Already Exists, make sure the ID is unique before adding a new tech");
							return;
						}
					}

					String driverName = "com.mysql.jdbc.Driver"; // for MySql
					try {
						
						Class.forName(driverName);
						Connection conn = DriverManager.getConnection(DB_URL,
								USER, PASSWORD);

						Statement stmt = conn.createStatement();
						String theQuery = "INSERT INTO technicians VALUES (";

						theQuery += addId.getText() + ",'"
								+ addFirstName.getText() + "','"
								+ addLastName.getText() + "','"
								+ addPhone.getText() + "','"
								+ addEmail.getText() + "','"
								+ addContact.getText() + "','"
								+ addWorkHours.getText() + "')";

						stmt.execute(theQuery);

						conn.close();

				
				} catch (Exception ee) {
				
					System.out.println("Problem : " + ee.getMessage());

				}

				data.add(new Person(addId.getText(), addFirstName.getText(),
						addLastName.getText(), addPhone.getText(), addEmail
								.getText(), addContact.getText(), addWorkHours
								.getText()));
				addId.clear();
				addFirstName.clear();
				addLastName.clear();
				addPhone.clear();
				addEmail.clear();
				addContact.clear();
				addWorkHours.clear();
				message.setText("");

			}else {
				message.setText("Information was not updated: make sure all fields are filled out");
			}
		})	;

			final Label addId1 = new Label();
			addId1.setText("ID");
			addId1.setPrefWidth(idCol.getMinWidth());
			addId1.setPrefHeight(25);
			final TextField addFirstName1 = new TextField();
			addFirstName1.setPromptText("First Name");
			addFirstName1.setPrefWidth(firstNameCol.getMinWidth());
			final TextField addLastName1 = new TextField();
			addLastName1.setPrefWidth(lastNameCol.getMinWidth());
			addLastName1.setPromptText("Last Name");
			final TextField addPhone1 = new TextField();
			addPhone1.setPrefWidth(phoneCol.getMinWidth());
			addPhone1.setPromptText("Phone");
			final TextField addEmail1 = new TextField();
			addEmail1.setPrefWidth(emailCol.getMinWidth());
			addEmail1.setPromptText("Email");
			final TextField addContact1 = new TextField();
			addContact1.setPrefWidth(contactCol.getMinWidth());
			addContact1.setPromptText("Emergency Contact");
			final TextField addWorkHours1 = new TextField();
			addWorkHours1.setPrefWidth(workHoursCol.getMinWidth());
			addWorkHours1.setPromptText("Work Hours");

			final Button editButton = new Button("Update Selected");
			editButton.setMinWidth(50);
			editButton.setOnAction((ActionEvent e) -> {

				
				if (!addFirstName1.getText().equals("")
						&& !addLastName1.getText().equals("")
						&& !addPhone1.getText().equals("")
						&& !addEmail1.getText().equals("")
						&& !addContact1.getText().equals("")
						&& !addWorkHours1.getText().equals("")) {

					String driverName = "com.mysql.jdbc.Driver"; // for MySql
					try {
						// Load the JDBC driver
						Class.forName(driverName);
						Connection conn = DriverManager.getConnection(DB_URL,
								USER, PASSWORD);

						Statement stmt = conn.createStatement();
						String theQuery = "REPLACE INTO technicians VALUES (";

						theQuery += addId1.getText() + ",'"
								+ addFirstName1.getText() + "','"
								+ addLastName1.getText() + "','"
								+ addPhone1.getText() + "','"
								+ addEmail1.getText() + "','"
								+ addContact1.getText() + "','"
								+ addWorkHours1.getText() + "')";

						stmt.execute(theQuery);

						conn.close();



				} catch (Exception ee) {
					
					System.out.println("Problem : " + ee.getMessage());

				}

					int rowSelected = table.getSelectionModel().getSelectedIndex();
					
				data.set(rowSelected, (new Person(addId1.getText(), addFirstName1.getText(),
						addLastName1.getText(), addPhone1.getText(), addEmail1
								.getText(), addContact1.getText(), addWorkHours1
								.getText())));
				message.setText("");
			}else {
				message.setText("Information was not updated: make sure all update fields are filled out");
			}
				
				
			});

			final Button deleteButton = new Button("Delete Selected");
			deleteButton.setMinWidth(50);
			deleteButton.setOnAction((ActionEvent e) -> {
				if (table.getSelectionModel().getSelectedItem() != null) {

					String driverName = "com.mysql.jdbc.Driver"; 
					try {
					
						Class.forName(driverName).newInstance();
						Connection conn = DriverManager.getConnection(DB_URL,
								USER, PASSWORD);

						
					String theQuery = "DELETE FROM technicians WHERE ID = ?";
					PreparedStatement ps = conn.prepareStatement(theQuery);
					ps.setString(1, ((Person) table.getSelectionModel()
							.getSelectedItem()).getId());
					ps.execute();

					conn.close();
				} catch (Exception ee) {
					
					System.out.println("Problem : " + ee.getMessage());

				}

				int rowSelected = table.getSelectionModel().getSelectedIndex();
				data.remove(rowSelected);
				
				message.setText("");

			}

		})	;

			hb.getChildren().addAll(addId, addFirstName, addLastName, addPhone,
					addEmail, addContact, addWorkHours, addButton);

			hb.setSpacing(0);

			hb2.getChildren().addAll(addId1, addFirstName1, addLastName1,
					addPhone1, addEmail1, addContact1, addWorkHours1,
					editButton);

			hb2.setSpacing(0);

			final VBox vbox = new VBox();
			vbox.setSpacing(5);
			vbox.setPadding(new Insets(10, 0, 0, 10));
			vbox.getChildren().addAll(label, hb, table, hb2, deleteButton, message);

			((Group) scene.getRoot()).getChildren().addAll(vbox);

			addUpWidth += addButton.getMinWidth();
			addUpWidth += editButton.getMinWidth();
			addUpWidth += deleteButton.getMinWidth();

			table.getSelectionModel().selectedItemProperty()
					.addListener(new ChangeListener() {

						@Override
						public void changed(ObservableValue observable,
								Object oldValue, Object newValue) {
							// TODO Auto-generated method stub

							if (table.getSelectionModel().getSelectedItem() != null) {

								addId1.setText(((Person) table
										.getSelectionModel().getSelectedItem())
										.getId());
								addFirstName1.setText(((Person) table
										.getSelectionModel().getSelectedItem())
										.getFirstName());
								addLastName1.setText(((Person) table
										.getSelectionModel().getSelectedItem())
										.getLastName());
								addPhone1.setText(((Person) table
										.getSelectionModel().getSelectedItem())
										.getPhone());
								addEmail1.setText(((Person) table
										.getSelectionModel().getSelectedItem())
										.getEmail());
								addContact1.setText(((Person) table
										.getSelectionModel().getSelectedItem())
										.getContact());
								addWorkHours1.setText(((Person) table
										.getSelectionModel().getSelectedItem())
										.getworkHours());
								

							} else {
								addId1.setText("ID");
								addFirstName1.clear();
								addLastName1.clear();
								addPhone1.clear();
								addEmail1.clear();
								addContact1.clear();
								addWorkHours1.clear();
							}
							message.setText("");
						}
					});

			

			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static class Person {
		private final SimpleStringProperty id;
		private final SimpleStringProperty firstName;
		private final SimpleStringProperty lastName;
		private final SimpleStringProperty phone;
		private final SimpleStringProperty email;
		private final SimpleStringProperty contact;
		private final SimpleStringProperty workHours;

		public SimpleStringProperty idProperty() {
			return id;
		}

		public SimpleStringProperty firstNameProperty() {
			return firstName;
		}

		public SimpleStringProperty lastNameProperty() {
			return lastName;
		}

		public SimpleStringProperty phoneProperty() {
			return phone;
		}

		public SimpleStringProperty emailProperty() {
			return email;
		}

		public SimpleStringProperty contactProperty() {
			return contact;
		}

		public SimpleStringProperty workHoursProperty() {
			return workHours;
		}

		public void setId(String theId) {
			id.set(theId);
		}

		public void setFirstName(String fName) {
			firstName.set(fName);
		}

		public void setLastName(String lName) {
			lastName.set(lName);
		}

		public void setPhone(String p) {
			phone.set(p);
		}

		public void setEmail(String e) {
			email.set(e);
		}

		public void setContact(String c) {
			contact.set(c);
		}

		public void setworkHours(String ht) {
			workHours.set(ht);
		}

		public String getId() {
			return id.get();
		}

		public String getFirstName() {
			return firstName.get();
		}

		public String getLastName() {
			return lastName.get();
		}

		public String getPhone() {
			return phone.get();
		}

		public String getEmail() {
			return email.get();
		}

		public String getContact() {
			return contact.get();
		}

		public String getworkHours() {
			return workHours.get();
		}

		private Person(String id, String fName, String lName, String phone,
				String email, String contact, String workHours) {
			this.id = new SimpleStringProperty(id);
			this.firstName = new SimpleStringProperty(fName);
			this.lastName = new SimpleStringProperty(lName);
			this.phone = new SimpleStringProperty(phone);
			this.email = new SimpleStringProperty(email);
			this.contact = new SimpleStringProperty(contact);
			this.workHours = new SimpleStringProperty(workHours);
		}

	}

}
