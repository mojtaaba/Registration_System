

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Created by ahmd on 29 نو�?، 2018 م.
 */





public class RegistrationStatus extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Label Title = new Label("Registration Status\n\n");
        Title.setFont(Font.font(30));
        Title.setTextAlignment(TextAlignment.CENTER);
        Button back = new Button("Back");
        HBox btnB = new HBox();
        btnB.getChildren().add(back);
        HBox PWB = new HBox();
        HBox TableBox = new HBox();

        Label EarnedCredit = new Label("Earned Credit");
        Label Curriculum = new Label("Curriculum information");
        EarnedCredit.setFont(Font.font(20));
        Curriculum.setFont(Font.font(20));

        TableView tableView = new TableView();
        TableBox.getChildren().add(tableView);

        TableColumn columnLevel = new TableColumn("Level");
        TableColumn columnTerm = new TableColumn("Term");
        TableColumn columnCredit    = new TableColumn("Credit");
        tableView.getColumns().addAll(columnLevel,columnTerm,columnCredit);
        tableView.setPrefSize(240,100);


        Label rigInfo = new Label("\n\n\n");
        Label curInfo = new Label("\n\n\n");
        ComboBox term = new ComboBox();

        GridPane grid = new GridPane();
        GridPane grid1 = new GridPane();
        GridPane grid2 = new GridPane();
        GridPane grid3 = new GridPane();

        grid.add(back,0,0);
        grid.add(grid1,0,1);
        grid.add(grid2,0,2);
        grid.add(grid3,0,3);



        grid1.add(Title,0,0);
        grid1.add(term,0,1);
        grid1.add(rigInfo,0,2);

        grid2.add(EarnedCredit,0,0);
        grid2.add(TableBox,0,1);

        grid3.add(Curriculum,0,0);
        grid3.add(curInfo,0,1);



        grid.setPadding(new Insets(10,10,10,10));



        primaryStage.setTitle("Registration Status");
        primaryStage.setScene(new Scene(grid, 550, 450));
        primaryStage.show();

        back.setOnAction((ActionEvent e) -> {
            MainView show = new MainView();
            try {
                show.start(primaryStage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });


    }


    public static void main(String[] args) {
        launch(args);
    }
}


