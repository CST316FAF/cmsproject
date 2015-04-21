package application;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

public class Main extends Application {

	// variable specifying amount of auto technicians and customers to be
	// created
	static final int SIZE = 500;

	final int WIN_W = 800;// window width
	final int WIN_H = 500;// window height

	// creates a new arrays for filling the comboboxes of technicians and
	// customers
	String[] technicians = new String[SIZE];
	String[] customers = new String[SIZE];

	// Components to be added to the Window -----------------
	TextArea jobDescriptionTA, recordsTA;

	Label techLabel, custLabel, tempLabel;

	ComboBox<String> techniciansCB;
	ComboBox<String> customersCB;

	DatePicker datePicker;

	Button addJobButton;

	// SQL Stuff
	static final String DB_URL = "jdbc:mysql://localhost:3306/database1";
	public static final String USER = "root";
	public static final String PASSWORD = "Welcome1";
	private final String TABLE_NAME = "CREATEJOBS";
	String driverName = "com.mysql.jdbc.Driver"; // for MySql
	Connection conn = null;

	@Override
	public void start(Stage primaryStage) {
		try {

			// loop for filling the technicians combobox
			for (int i = 0; i < SIZE; i++) {
				technicians[i] = "Tech " + i;
			}
			// loop for filling the customer combobox
			for (int i = 0; i < SIZE; i++) {
				customers[i] = "Cust " + i;
			}

			// The to level Vertical Box that holds all the other components to
			// be displayed
			VBox root = new VBox();

			// top label
			Label addJobScreenLabel = new Label("Creating Job");
			addJobScreenLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

			// Horizontal box used to hold the 2 comboboxes
			HBox dropBoxesHB = new HBox();

			// labels for each combobox
			techLabel = new Label("Technician");
			double techLabelSize = TextUtils.computeTextWidth(
					techLabel.getFont(), techLabel.getText(), 0.0D);
			//sets size of the component
			techLabel.setPrefWidth(techLabelSize + 10);
			custLabel = new Label("Customer");
			double custLabelSize = TextUtils.computeTextWidth(
					custLabel.getFont(), custLabel.getText(), 0.0D);
			custLabel.setPrefWidth(custLabelSize + 10);

			// technician combobox and settings to set it's size
			techniciansCB = new ComboBox<String>();
			techniciansCB.setPrefWidth(WIN_W / 2 - techLabel.getPrefWidth()
					- 15 / 2);
			techniciansCB.getItems().addAll(technicians);
			techniciansCB.setValue("Choose a Technician");

			// customers combobox and settings to set it's size
			customersCB = new ComboBox<String>();
			customersCB.setPrefWidth(WIN_W / 2 - custLabel.getPrefWidth() - 15
					/ 2);
			customersCB.getItems().addAll(customers);
			customersCB.setValue("Choose a Customer");

			// spaced items
			dropBoxesHB.setSpacing(5.0);

			// add the comboboxes to the Hbox
			dropBoxesHB.getChildren().addAll(techLabel, techniciansCB,
					custLabel, customersCB);

			// Horizontal box for holding description and date
			HBox descriptionAndDateHB = new HBox();

			Label descriptionLabel = new Label("Job Description");

			// Text area used for for adding description
			jobDescriptionTA = new TextArea();

			Label datePickerLabel = new Label("Date");

			// date picker
			datePicker = new DatePicker();

			descriptionAndDateHB.setSpacing(5.0);

			// add the description and date picker items
			descriptionAndDateHB.getChildren().addAll(descriptionLabel,
					jobDescriptionTA, datePickerLabel, datePicker);

			// button for adding a new job record
			addJobButton = new Button("Add Job");

			// action handler for the button
			addJobButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

					// SQL for adding a new record when the button is clicked
					ResultSet rs = null;
					conn = null;
					try {

						// sql setup
						Class.forName(driverName).newInstance();
						conn = DriverManager.getConnection(DB_URL, USER,
								PASSWORD);
						Statement stmt = conn.createStatement();
						// SQL statement for adding the new record
						String newEntryStatement = "INSERT INTO " + TABLE_NAME
								+ " VALUES (NULL, '" + techniciansCB.getValue()
								+ "','" + customersCB.getValue() + "','"
								+ jobDescriptionTA.getText() + "','"
								+ datePicker.getValue() + "')";

						// execute the update command
						stmt.executeUpdate(newEntryStatement);

						// set the text of the sql display textarea (recordsTA)
						recordsTA.setText(recordsTA.getText() + "\t"
								+ techniciansCB.getValue() + "\t"
								+ customersCB.getValue() + "\t"
								+ jobDescriptionTA.getText() + "\t"
								+ datePicker.getValue() + "\n");

					} catch (Exception e) {
						//error message for errors
						System.out.println(e.toString());
					}
				}
			});

			//spacing of root (top level Vbox)
			root.setSpacing(20.0);

			//text area for showing the sql data
			recordsTA = new TextArea();
			recordsTA.setPrefWidth(WIN_W);

			//add all the items to the root VBox
			root.getChildren().addAll(addJobScreenLabel, dropBoxesHB,
					descriptionAndDateHB, addJobButton, recordsTA);

			// SQL code for adding the data from the database when the program first starts
			ResultSet rs = null;
			conn = null;
			try {

				//SQL stuff
				Class.forName(driverName).newInstance();
				conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
				Statement stmt = conn.createStatement();

				// Check to see if database already exists
				DatabaseMetaData md = conn.getMetaData();
				rs = md.getTables(null, null, TABLE_NAME, null);
				int count = 0;
				while (rs.next()) {
					count++;
				}

				// Database does not exist so create one
				if (count == 0) {
					System.out
							.println("creating new table since it doens't exist");
					//SQL create table command for creating a new table since it doesn't exist
					String sql = "CREATE TABLE "
							+ TABLE_NAME
							+ " (ID int NOT NULL AUTO_INCREMENT, technician VARCHAR(255) NOT NULL, customer VARCHAR(255),description VARCHAR(255), date VARCHAR(255), PRIMARY KEY (ID))";
					//execute command to create the ta le
					stmt.executeUpdate(sql);
				} else {
					System.out
							.println("table already exists, no need to create new");
				}

				// SQL FOR SELECTING ALL RECORDS
				String SQL = "SELECT * from " + TABLE_NAME;
				// ResultSet
				rs = conn.createStatement().executeQuery(SQL);

				while (rs.next()) {
					//add the entry to the records Text Area for display
					recordsTA.setText(recordsTA.getText() + "\t"
							+ rs.getString(2) + "\t" + rs.getString(3) + "\t"
							+ rs.getString(4) + "\t" + rs.getString(5) + "\n");
				}

			} catch (Exception e) {
				//error case
			}

			Scene scene = new Scene(root, WIN_W, WIN_H);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
