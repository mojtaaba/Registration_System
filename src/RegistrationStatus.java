/*
	* Author : AHMED ALMOAIRFI,Mojtaba Alamer
	* This Class used to implement the User Interface (Window) for the RegistrationStatus Function
	* The library Used for GUI in this class is javafx
	* it call getStatus() from Student Object To check the Login Information
*/


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class RegistrationStatus extends Application {

    @SuppressWarnings("unchecked")
	@Override
    public void start(Stage primaryStage) throws Exception{
        //Set Registration State
    	String State= Login._User.getStatus()==1 ? "✔" : "✖";

    	//Add the Title
        Label Title = new Label(" Registration Status\n\n");
        Title.setFont(Font.font(30));
        Title.setTextAlignment(TextAlignment.CENTER);

        //Add back button
        Label back = new Label("Back");
        back.setTextFill(Color.BLUE);
        back.setFont(Font.font(17));

        //Button back = new Button("Back");
        HBox btnB = new HBox();
        btnB.getChildren().add(back);


        //Add Registration information
        Label EarnedCredit = new Label("Earned Credit");
        Label Curriculum = new Label("Curriculum information");
        EarnedCredit.setFont(Font.font(20));
        Curriculum.setFont(Font.font(20));
        Label Statment1=new Label(State+"  You have no Holds which prevent registration.");
        Label Statment2=new Label(State+"  Your Academic Standing at the end of last term is Good Standing which permits registration.");
        Label Statment3=new Label(State+"  Your Student Status is which permits registration. ");
        //Get  registration purposes
        String purposes="";
        int credit =Login._User.gettotalCredit();
        if(credit<34)
        	purposes="Freshmen";
        else if(credit<61)
    	purposes="Sophomore";
        else if(credit<100)
        	purposes="Junior";
        else 
        	purposes="senior";
        Label Statment4=new Label(State+"  Your Class for registration purposes is "+purposes);



        //Generate table for earned credit
        HBox TableBox = new HBox();

        TableView<data> tableView = new TableView<data>();
        TableBox.getChildren().add(tableView);

        //Generate Columns for the table
        TableColumn LevelColumn = new TableColumn("Level");
        LevelColumn.setCellValueFactory(new PropertyValueFactory<>("Level"));

        TableColumn TypeColumn = new TableColumn("Type");
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));

        TableColumn CreditColumn = new TableColumn("Credit");
        CreditColumn.setCellValueFactory(new PropertyValueFactory<>("Cred"));

        //assign data to the table
        tableView.getColumns().addAll(LevelColumn, TypeColumn,CreditColumn);
        data row1 = new data("Preparatory","Institutional",29);
        data row2 = new data("Undergraduate","Institutional",Login._User.gettotalCredit());
        tableView.getItems().add(row1);
        tableView.getItems().add(row2);
        
        

        
        tableView.setPrefSize(282,100);


        Label curInfo = new Label("Bachelor of Science\r\n" + 
        		"Level:\t\t\tUndergraduate\r\n" +
        		"Program:\t\t\tBS in "+ Login._User.getMajor()+".\r\n" +
        		"Admit Term:\t\tSummer Session 2017\r\n" +
        		"Admit Type:\t\t"+purposes+" Admit\r\n" +
        		"Catalog Term:\t\tSummer Session 2017\r\n" +
        		"");


        //adding the main grid for coordinate the page
        GridPane grid = new GridPane();
        GridPane grid1 = new GridPane();
        GridPane grid2 = new GridPane();
        GridPane grid3 = new GridPane();

        grid.add(back,0,0);
        grid.add(grid1,0,1);
        grid.add(grid2,0,2);
        grid.add(grid3,0,3);


        //adding the rest grids
        grid1.add(Title,0,0);
        grid1.add(Statment1, 0, 1);
        grid1.add(Statment2, 0, 2);
        grid1.add(Statment3, 0, 3);
        grid1.add(Statment4, 0, 4);

        grid2.add(EarnedCredit,0,0);
        grid2.add(TableBox,0,1);

        grid3.add(Curriculum,0,0);
        grid3.add(curInfo,0,1);



        grid.setPadding(new Insets(10,10,10,10));
        LevelColumn.setPrefWidth(110);
        TypeColumn.setPrefWidth(110);
        CreditColumn.setPrefWidth(60);



        primaryStage.setTitle("Registration Status");
        primaryStage.setScene(new Scene(grid, 550, 450));
        primaryStage.show();


        //set back button action
        back.setOnMouseClicked((MouseEvent e) -> {
            MainView show = new MainView();
            try {
                show.start(primaryStage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });



    }

public class data {
    private String Level;
    private String Type;
    private int Cred;

    public data(String Level, String surname,int Cred) {
        this.Level = Level;
        this.Type = surname;
        this.Cred=Cred;
    }

    public String getLevel() {
        return Level;
    }

    public String getType() {
        return Type;
    }
    public int getCred() {
        return Cred;
    }
}


	public static void main(String[] args) {
        launch(args);
    }
}


