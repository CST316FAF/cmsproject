package TechTools.src.application;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.sun.javafx.collections.ObservableListWrapper;

import javafx.application.Application;
import javafx.application.Platform;
import static javafx.application.Application.launch;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class Main extends Application {

	private ObservableList<Map> tableData;
	private TableView<Map> tableview;
	Callback<TableColumn<Map, String>, TableCell<Map, String>> cellFactoryForMap;

	static final String DB_URL = "jdbc:mysql://localhost:3306/database1";
	public static final String USER = "root";
	public static final String PASSWORD = "Welcome1";
	private String FIRST_COLUMN_NAME = "";
	private final String TABLE_NAME = "TECHTOOLS";
	String driverName = "com.mysql.jdbc.Driver"; // for MySql
	Connection conn = null;
	// Load the JDBC driver

	final HBox hbTop = new HBox();
	final HBox hb = new HBox();

	private ObservableList<Map> buildDataFromDatabase() {
			
		ResultSet rs = null;
		conn = null;
		try {

			Class.forName(driverName).newInstance();
			conn = DriverManager.getConnection(DB_URL, USER,PASSWORD);
			Statement stmt = conn.createStatement();

			/* For debugging and testing: Drops (deletes) a database.
			 * String drop = "drop TABLE "+TABLE_NAME+"";
			 * stmt.executeUpdate(drop);
			 */

			//Check to see if database already exists
			DatabaseMetaData md = conn.getMetaData();
			rs = md.getTables(null, null, TABLE_NAME, null);
			int count = 0;
			while (rs.next()) {
				count++;
			}
			
			// Database does not exist so create one
			if (count == 0) {
				System.out.println("creating new table since it doens't exist");
				String sql = "CREATE TABLE "+TABLE_NAME+" (technician VARCHAR(255) NOT NULL, sampletool INTEGER, PRIMARY KEY (technician))";
				stmt.executeUpdate(sql);
				for (int i = 0; i < 10; i++) {
					sql = "INSERT INTO "+TABLE_NAME+" VALUES (' Technician#" + i
							+ "'," + 0 + ")";
					stmt.executeUpdate(sql);
				}
				System.out.println("create new");
			}else {
				System.out.println("table already exists, no need to create new");
			}

			// SQL FOR SELECTING ALL OF CUSTOMER
			String SQL = "SELECT * from "+TABLE_NAME;
			// ResultSet
			rs = conn.createStatement().executeQuery(SQL);
			
			//Create columns and cell value factories for each
			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
				
				String columnName = rs.getMetaData().getColumnName(i + 1);
				
				//set first column name. Used for error checking when editing cells
				if(i==0)
					FIRST_COLUMN_NAME = columnName;
				
				//create and name the column according to database info for that column
				TableColumn c = new TableColumn(columnName);
				
				//create and set the current columns CellValueFactory (use column name for the column's key for the map)
				c.setCellValueFactory(new MapValueFactory(columnName));
				
				//add column to the table
				tableview.getColumns().add(c);
				
				//condition so that the first column is not editable
				//if(i != 0)
				c.setCellFactory(cellFactoryForMap);
				
			}
			
			//Create the data structure etc that holds the actual data. It will be stored in an observable list
			//to take advantage of the built in features of javafx's MapValueFactory etc
			
			// List that holds individual "rows" / maps of the data for the table. Each row is really just a hashmap
			// with each of these hashmap's keys being the column names of the columns which guarantees a unique 
			// value for each column since the column names are unique
			tableData = FXCollections.observableArrayList();
			
			while (rs.next()) {
				// Iterate Row
				ObservableList<String> row = FXCollections
						.observableArrayList();
				
				// Row we are building and are going to add on this iteration
				Map<String,String> dataRow = new HashMap<>();
				
				// Iterate Column to add data to all columns for this row
				for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
					dataRow.put(rs.getMetaData().getColumnName(i+1), rs.getString(i+1));
				}
				tableData.add(dataRow);
			}
			
			System.out.print("----");
			
			tableview.setItems(tableData);
			
			
		}catch(Exception e) {System.out.println("SQL exception: "+ e.toString());}
		finally {
			if(rs != null) {
				try {
				rs.close();
				}catch(SQLException se) {System.out.println("could not close connection");}
			}
			if(conn != null) {
				try{
					conn.close();
				}catch(SQLException se) {System.out.println("could not close connection");}
			}
		}
		
		return null;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage stage) throws Exception {
		// TableView

		tableview = new TableView<>();
		
		//create cell factory for the cells	within each column so it can build the table	
		cellFactoryForMap = new Callback<TableColumn<Map, String>, TableCell<Map, String>>() {
			@SuppressWarnings("unchecked")
			@Override
			public TableCell call(TableColumn p) {

				TextFieldTableCell temp = new TextFieldTableCell(
						new StringConverter() {
							@Override
							public String toString(Object t) {
								return t.toString();
							}

							@Override
							public Object fromString(String string) {
								return string;
							}
						}) {

					@Override
					public void commitEdit(Object o) {
						String textToCommit = (String) o;
						if (textToCommit.length() > 0) {

							try {
								//error check columns that take numbers values by doing a parse int
								//this should look at all columns except the first (technician name)
									//Integer.parseInt(textToCommit);
								
								Map row = tableData
										.get(tableview.getSelectionModel().getSelectedIndex());

								row.put(p.getText(), o.toString());
								
								tableData.set(tableview.getSelectionModel().getSelectedIndex(), row);
								
								//SQL entry for this edit
								
								conn = null;
								try {

									Class.forName(driverName).newInstance();
									conn = DriverManager.getConnection(DB_URL, USER,PASSWORD);
									Statement stmt = conn.createStatement();
									
									int columnSelected = tableview.getSelectionModel().getSelectedCells().get(0).getColumn();
									String columnName = tableview.getColumns().get(columnSelected).getText();
								
									 String changeStatement = "UPDATE "+TABLE_NAME+" SET " + columnName + " = "+ o.toString() + " WHERE "+FIRST_COLUMN_NAME+" = '";
									 changeStatement += (String) tableData.get(tableview.getSelectionModel().getSelectedIndex()).get(FIRST_COLUMN_NAME);
									 changeStatement += "'";

									 
									 stmt.executeUpdate(changeStatement);
								}catch(Exception ee) {
									
									
								}finally {
									
									if(conn != null) {
										try{
											conn.close();
										}catch(SQLException se) {System.out.println("could not close connection");}
									}
									
								}
								
								
								Platform.runLater(new Runnable()
								{
								    @Override
								    public void run()
								    {
								        tableview.requestFocus();
								        tableview.getFocusModel().focus(tableview.getSelectionModel().getSelectedIndex(),tableview.getEditingCell().getTableColumn());

								    }
								});

							} catch (NumberFormatException e) {
								Platform.runLater(new Runnable()
								{
								    @Override
								    public void run()
								    {
								        tableview.requestFocus();
								        tableview.getFocusModel().focus(tableview.getSelectionModel().getSelectedIndex(),tableview.getEditingCell().getTableColumn());

								    }
								});
							}
						}else {
							Platform.runLater(new Runnable()
							{
							    @Override
							    public void run()
							    {
							        tableview.requestFocus();
							        tableview.getFocusModel().focus(tableview.getSelectionModel().getSelectedIndex(),tableview.getEditingCell().getTableColumn());

							    }
							});
						}
					}
				};

				return temp;
			}
		};
		
		buildDataFromDatabase();
		tableview.setEditable(true);
		tableview.getSelectionModel().setCellSelectionEnabled(true);
		tableview.setMinHeight(400);
		//buildData();

		final TextField addName = new TextField();
		addName.setPromptText("Name");
		// addName.setMaxWidth(nameCol.getPrefWidth());

		final Button addButton = new Button("Add New Technician");
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				String newTechnicianName = addName.getText();
				
				//make sure technician's name doesn't already exist. If it does just return so that duplicate is not created
				for(int i = 0;i<tableData.size();i++) {
					if(tableData.get(i).get(FIRST_COLUMN_NAME).equals(newTechnicianName))
						return;
				}
				
				//make sure it's not blank
				if(newTechnicianName.length() > 0) {
				
				Map<String,String> dataRow = new HashMap<>();
				dataRow.put(tableview.getColumns().get(0).getText(),newTechnicianName);
				for(int i=1;i<tableview.getColumns().size();i++) {
					dataRow.put(tableview.getColumns().get(i).getText(), "0");
				}
				
				tableData.add(dataRow);
				
				//SQL for adding new technician entry in database
				
				conn = null;
				try {

					Class.forName(driverName).newInstance();
					conn = DriverManager.getConnection(DB_URL, USER,PASSWORD);
					Statement stmt = conn.createStatement();
				
					 String newEntryStatement = "INSERT INTO "+TABLE_NAME+" VALUES ('"+ newTechnicianName + "'";
					 
						for(int i=1;i<tableview.getColumns().size();i++) {
							newEntryStatement += ", 0";
						}
						
						newEntryStatement += ")";
					 
					 stmt.executeUpdate(newEntryStatement);
				}catch(Exception ee) {
					
					
				}finally {
					
					if(conn != null) {
						try{
							conn.close();
						}catch(SQLException se) {System.out.println("could not close connection");}
					}
					
				}
				
				addName.clear();
				}
			}
		});

		final Button deleteTechButton = new Button("Delete Selected Technician");
		deleteTechButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				//get the name of the technician for the row selected
				String selectedTechnicianName = (String) tableData.get(tableview.getSelectionModel().getSelectedIndex()).get(FIRST_COLUMN_NAME);
		
				tableData.remove(tableview.getSelectionModel().getSelectedIndex());

				//SQL statement to remove this entry
				
				conn = null;
				try {

					Class.forName(driverName).newInstance();
					conn = DriverManager.getConnection(DB_URL, USER,PASSWORD);
					Statement stmt = conn.createStatement();
				
					 String newEntryStatement = "DELETE FROM "+TABLE_NAME+" WHERE " + FIRST_COLUMN_NAME + " = '" + selectedTechnicianName + "'";
					 
					 stmt.executeUpdate(newEntryStatement);
				}catch(Exception ee) {
					
					
				}finally {
					
					if(conn != null) {
						try{
							conn.close();
						}catch(SQLException se) {System.out.println("could not close connection");}
					}					
				}
			}
		});

		hbTop.getChildren().addAll(addName, addButton, deleteTechButton);
		hbTop.setSpacing(3);

		final TextField addTool = new TextField();
		addTool.setPromptText("Tool Name");
		// addTool.setMaxWidth(nameCol.getPrefWidth());

		final Button addToolButton = new Button("Add Tool");
		addToolButton.setOnAction(new EventHandler<ActionEvent>() {
			@SuppressWarnings("unchecked")
			@Override
			public void handle(ActionEvent e) {

				String newColumnName = addTool.getText();
				
				//make sure tool doesn't already exist as a row. It it does just return
				for(int i = 1;i<tableview.getColumns().size();i++) {
					if(tableview.getColumns().get(i).getText().equals(newColumnName))
						return;
				}

				if (newColumnName.length() > 0) {

					TableColumn c = new TableColumn(newColumnName);
					// create and set the current columns CellValueFactory (use
					// column name for the column's key for the map)
					c.setCellValueFactory(new MapValueFactory(newColumnName));

					tableview.getColumns().add(c);

					c.setCellFactory(cellFactoryForMap);

					for (int i = 0; i < tableview.getItems().size(); i++) {
						Map row = tableData.get(i);
						row.put(newColumnName, "0");
					}		
					
					//SQL Database update for new column...
					conn = null;
					try {

						Class.forName(driverName).newInstance();
						conn = DriverManager.getConnection(DB_URL, USER,PASSWORD);
						Statement stmt = conn.createStatement();
					
						 String addTheColumn = "ALTER TABLE "+TABLE_NAME+" ADD " + newColumnName + " INTEGER DEFAULT 0";
						 stmt.executeUpdate(addTheColumn);
					}catch(Exception ee) {
						
						
					}finally {
						
						if(conn != null) {
							try{
								conn.close();
							}catch(SQLException se) {System.out.println("could not close connection");}
						}
						
					}

					
					addTool.clear();					
				}
			}
		});

		final Button deleteToolButton = new Button("Delete Selected Tool");
		deleteToolButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				int columnSelected = tableview.getSelectionModel().getSelectedCells().get(0).getColumn();
				
				if(columnSelected > 0) {
				
				String columnName = tableview.getColumns().get(columnSelected).getText();
				
				System.out.println("Selected column is: " + columnSelected);
				System.out.println("Column name is: " + tableview.getColumns().get(columnSelected).getText());
				
				tableview.getColumns().remove(columnSelected);
				
				//SQL Database update for removing this column
				conn = null;
				try {

					Class.forName(driverName).newInstance();
					conn = DriverManager.getConnection(DB_URL, USER,PASSWORD);
					Statement stmt = conn.createStatement();
				
					 String dropTheColumn = "ALTER TABLE "+TABLE_NAME+" DROP COLUMN " + columnName;
					 stmt.executeUpdate(dropTheColumn);
				}catch(Exception ee) {
					
					
				}finally {
					if(conn != null) {
						try{
							conn.close();
						}catch(SQLException se) {System.out.println("could not close connection");}
					}
					
				}
				
				//delete data from table? probably no need
				
				}
			}
		});

		hb.getChildren().addAll(addTool, addToolButton, deleteToolButton);
		hb.setSpacing(3);

		VBox vb = new VBox();

		vb.getChildren().addAll(hbTop, tableview, hb);

		vb.setMinSize(900, 450);
		// Main Scene
		Scene scene = new Scene(vb);

		stage.setScene(scene);
		stage.show();
		
		
		tableview.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {
					@Override
					public void changed(ObservableValue observableValue,
							Object oldValue, Object newValue) {
						// Check whether item is selected and set value of
						// selected item to Label
						if (tableview.getSelectionModel().getSelectedItem() != null) {
							TableViewSelectionModel selectionModel = tableview
									.getSelectionModel();
							ObservableList selectedCells = selectionModel
									.getSelectedCells();
							TablePosition tablePosition = (TablePosition) selectedCells
									.get(0);
							Object val = tablePosition.getTableColumn()
									.getCellData(newValue);
							System.out.println("Selected Value " + val);
							System.out.println("Selected row " + newValue);
						}
					}
				});

	
	}

	public static void main(String[] args) {
		launch(args);
	}

}