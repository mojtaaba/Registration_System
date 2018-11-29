

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

        import javafx.application.Application;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXMLLoader;
        import javafx.geometry.Insets;
        import javafx.geometry.Pos;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.*;
        import javafx.scene.layout.Border;
        import javafx.scene.layout.GridPane;
        import javafx.scene.layout.HBox;
        import javafx.scene.layout.StackPane;
        import javafx.scene.text.Font;
        import javafx.scene.text.FontPosture;
        import javafx.scene.text.FontWeight;
        import javafx.scene.text.TextAlignment;
        import javafx.stage.Stage;

public class AddDrop extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //StackPane root = new StackPane();
        Button back = new Button("Back");
        HBox btnB = new HBox();
        btnB.getChildren().add(back);
        HBox PWB = new HBox();
        HBox TableBox = new HBox();

        //...

        Label Title = new Label("Add and Drop");
        Title.setFont(new Font(20));
        Title.setTextAlignment(TextAlignment.CENTER);
        ComboBox Term = new ComboBox();


        Term.setPromptText("Term");
        //Course.setPromptText("Course");
        //section.setPromptText("section");

        String[] Terma =  {};
        String[] Coursea =  {};
        String[] sectionA =  {};

        Term.getItems().addAll(Terma);

        String[] state = {"Add","Drop"};


        TableView tableView = new TableView();
        TableBox.getChildren().addAll(tableView);
        TableBox.setAlignment(Pos.CENTER);
        TableColumn columnCourse = new TableColumn("Course");
        TableColumn columnCRN = new TableColumn("CRN");
        TableColumn columnDay    = new TableColumn("Day");
        TableColumn columnCredit  = new TableColumn("Credit");
        TableColumn columnState    = new TableColumn("State");


        TextField t1 = new TextField();
        TextField t2 = new TextField();
        TextField t3 = new TextField();
        TextField t4 = new TextField();
        TextField t5 = new TextField();
        TextField t6 = new TextField();
        TextField t7 = new TextField();
        TextField t8 = new TextField();

        Button btn1 = new Button("Submit");
        Button btn2 = new Button("search");
        Button btn3 = new Button("reset");



        tableView.getColumns().addAll(columnCourse,columnCRN,columnDay,columnCredit,columnState);
        columnCourse.setPrefWidth(100);
        columnCRN.setPrefWidth(75);
        columnDay.setPrefWidth(75);
        columnCredit.setPrefWidth(100);
        columnState.setPrefWidth(150);


        tableView.setPrefSize(500,150);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(20);
        GridPane grid1 = new GridPane();
        grid1.setAlignment(Pos.CENTER);
        //grid1.setHgap(100);
        GridPane grid2 = new GridPane();
        grid2.setAlignment(Pos.CENTER);
        GridPane grid3 = new GridPane();
        grid3.setAlignment(Pos.CENTER);
        GridPane grid4 = new GridPane();
        //grid4.setAlignment(Pos.CENTER);
        GridPane grid5 = new GridPane();
        grid5.setAlignment(Pos.CENTER);
        GridPane grid6 = new GridPane();
        //grid6.setAlignment(Pos.CENTER);

        grid.add(btnB,0,0);
        grid.add(Title,0,1);

        grid.add(Term,0,2);
        grid.add(TableBox,0,3);
        grid.add(grid2,0,4);

        //grid1.add(Term,0,0);
        //grid1.add(Course,1,0);
       // grid1.add(section,2,0);
        grid3.setVgap(10);

        grid4.add(new Label("Add Course CRN"),0,0);

        grid5.setHgap(10);
        grid6.setHgap(10);

        grid5.add(t1,0,0);
        grid5.add(t2,1,0);
        grid5.add(t3,2,0);
        grid5.add(t4,3,0);
        grid5.add(t5,4,0);
        grid5.add(t6,5,0);
        grid5.add(t7,6,0);
        grid5.add(t8,7,0);


        grid6.add(btn1,0,0);
        grid6.add(btn2,1,0);
        grid6.add(btn3,2,0);

        grid3.add(grid4,0,0);
        grid3.add(grid5,0,1);
        grid3.add(grid6,0,2);

        grid5.setPadding(new Insets(0,10,0,5));

        grid.add(grid3,0,5);
        grid.setPadding(new Insets(10,10,10,10));

        back.setOnAction((ActionEvent e) -> {
            MainView show = new MainView();
            try {
                show.start(primaryStage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });







        //grid.setGridLinesVisible(true);

       /* Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Label userName = new Label("Username");
        Label passWord = new Label("Password");

        Button login = new Button("Login");
        Button cancel = new Button("Cancel");
        cancel.cancelButtonProperty();

        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(30);



        cancel.setAlignment(Pos.CENTER);


        grid.add(userName,0,0);
        grid.add(passWord,0,1);
        grid.add(user,1,0);
        grid.add(pass,1,1);
        grid.add(login,0,2);
        grid.add(cancel,1,2);
        //grid.setPadding(new Insets(20,20,20,20));
        //grid.setGridLinesVisible(true);
        cancel.setAlignment(Pos.CENTER);
        */
        primaryStage.setTitle("ADD and DROP SYSTEM");
        primaryStage.setScene(new Scene(grid, 550, 450));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

