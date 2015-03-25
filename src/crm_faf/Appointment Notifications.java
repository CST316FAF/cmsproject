public void newMessage()
{

    final Stage newConnDialog = new Stage();
    newConnDialog.initStyle(StageStyle.UNDECORATED);
    newConnDialog.initModality(Modality.WINDOW_MODAL);

    //set position
    newConnDialog.setX(150); //secondStage.setX(primaryStage.getX() + 250);
    newConnDialog.setY(150);

    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(5);
    grid.setVgap(5);
    grid.setPadding(new Insets(20, 20, 20, 20));

    //text
    Text productName = new Text(“Notification of Upcoming Appointment”);
    productName.setFont(Font.font(“Times New Roman”, 12));
    grid.add(productName, 0, 2);

    //configure dialog size and background color
    Scene aboutDialogScene = new Scene(grid, 200, 100, Color.WHITE);
    newConnDialog.setScene(aboutDialogScene);
    newConnDialog.show();
}