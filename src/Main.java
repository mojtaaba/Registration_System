
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Label Title = new Label("KFUPM REGISTRAR\nSYSTEM\n\n");
        Title.setFont(new Font(20));
        Title.setTextAlignment(TextAlignment.CENTER);

        TextField user = new TextField();
        PasswordField pass = new PasswordField();
        user.setPromptText("KFUPM ID");
        pass.setPromptText("PASSWORD");

        Button login = new Button("\tLogin\t");
        Label passWord = new Label("Forget Password ?");
        passWord.setTextFill(Color.BLUE);
        HBox btnB = new HBox();
        HBox PWB = new HBox();

        btnB.setAlignment(Pos.CENTER);
        btnB.getChildren().add(login);
        PWB.setAlignment(Pos.CENTER);
        PWB.getChildren().add(passWord);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5,20,5,20));

        GridPane grid1 = new GridPane();
        grid1.setVgap(5);
        GridPane grid2 = new GridPane();
        grid2.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        grid1.setAlignment(Pos.CENTER);
        grid2.setAlignment(Pos.CENTER);
       // grid.setHgap(5);
        grid.setVgap(20);
        grid.add(Title,0,1);
        grid.add(grid1,0,2);
        grid.add(grid2,0,3);
        grid1.add(user,0,0);
        grid1.add(pass,0,1);
        grid2.add(btnB,0,0);
        grid2.add(PWB,0,1);
       // grid.setGridLinesVisible(true);
        login.setOnAction((ActionEvent e) -> {
            
            try {
                
                int Name=Integer.parseInt(user.getText());
                String password=pass.getText();
                boolean Logged=DataBase.Login(Name, password);
                System.out.println(Logged);
               if(Logged) {
            	   MainView mainView = new MainView();
            	   mainView.start(primaryStage);}
               else{}

                
                
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
        primaryStage.setTitle("REGISTRAR SYSTEM");
        primaryStage.setScene(new Scene(grid, 250, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
