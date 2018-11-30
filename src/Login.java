

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
public class Login extends Application {
public static Student _User=new Student();
public static int Current_term=181;	
    public void start(Stage primaryStage) throws Exception{

        Label Title = new Label("KFUPM REGISTRAR\nSYSTEM\n\n");
        Title.setFont(new Font(20));
        Title.setTextAlignment(TextAlignment.CENTER);

        TextField user = new TextField();
        PasswordField pass = new PasswordField();
        user.setPromptText("KFUPM ID");
        pass.setPromptText("PASSWORD");

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Wrong");
        alert.setHeaderText(null);

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
                DataBase Connecter=new DataBase();

                int ID=Integer.parseInt(user.getText());
                String password=pass.getText();
                boolean Logged=Connecter.Login(ID, password);


                if(Logged) {
                	_User=Connecter.getStudentInfo(ID);
                	Connecter.Save();
            	   MainView mainView = new MainView();
            	   mainView.start(primaryStage);}
               else{

                    alert.setContentText("User ID or password is wrong");
                    alert.show();

                }

                
                
            }
             catch(NumberFormatException e2){



                 alert.setContentText("please enter correct ID");
                 alert.show();
             }
            catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        primaryStage.setTitle("REGISTRAR SYSTEM");
        primaryStage.setScene(new Scene(grid, 250, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
