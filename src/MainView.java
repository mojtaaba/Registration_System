
        import javafx.application.Application;
        import javafx.geometry.Pos;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.image.Image;
        import javafx.scene.image.ImageView;
        import javafx.scene.input.MouseEvent;
        import javafx.scene.layout.GridPane;
        import javafx.scene.paint.Color;
        import javafx.scene.shape.Circle;
        import javafx.scene.text.Font;
        import javafx.scene.text.TextAlignment;
        import javafx.stage.Stage;
        import javafx.event.ActionEvent;


public class MainView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Label Title = new Label("Student Main Page\n\n");
        Title.setFont(Font.font(30));
        Title.setTextAlignment(TextAlignment.CENTER);
        Button bt1 = new Button("Add/ Drop");
        Button bt2 = new Button("Transcript");
        Button bt3 = new Button("Weekly Schedule");
        Button bt4 = new Button("Search for Course");
        Button bt5 = new Button("Registration Status");
        bt1.setPrefSize(150,30);
        bt2.setPrefSize(150,30);
        bt3.setPrefSize(150,30);
        bt4.setPrefSize(150,30);
        bt5.setPrefSize(150,30);

        Label name = new Label("Name: ");
        Label kfupmId = new Label("KFUPM ID: ");
        Label lastRT = new Label("Last registerd Term: ");
        Label info = new Label("more info");

        info.setTextFill(Color.BLUE);
        info.setOnMouseClicked((MouseEvent e) -> {System.out.println("Student information will be show here");});
        Circle shape = new Circle();
        shape.setRadius(100);
        Image image = new Image("/user.png");

        ImageView userImage = new ImageView(image);
        userImage.setFitHeight(150);
        userImage.setFitWidth(150);


        GridPane grid = new GridPane();
        GridPane userGrid = new GridPane();

        GridPane grid1 = new GridPane();
        GridPane grid2 = new GridPane();

        grid.setHgap(40);
        grid.setVgap(40);

        grid.setAlignment(Pos.CENTER);
        grid.add(Title,0,0);
        grid.add(grid1,0,1);

        grid1.add(userGrid,0,0);
        userGrid.add(userImage,0,0);
        userGrid.add(name,0,1);
        userGrid.add(kfupmId,0,2);
        userGrid.add(lastRT,0,3);
        userGrid.add(info,0,4);
        userGrid.setVgap(10);

        grid1.add(grid2,1,0);
        grid1.setAlignment(Pos.CENTER);
        grid1.setHgap(80);
        grid1.setVgap(8);


        grid2.add(bt1,0,0);
        grid2.add(bt2,0,1);
        grid2.add(bt3,0,2);
        grid2.add(bt4,0,3);
        grid2.add(bt5,0,4);
        grid2.setHgap(20);
        grid2.setVgap(10);

        grid2.setAlignment(Pos.CENTER);


        primaryStage.setTitle("Student Main Page");
        primaryStage.setScene(new Scene(grid, 550, 450));
        primaryStage.show();
        bt1.setOnAction((ActionEvent e) -> {
            AddDrop show = new AddDrop();
            try {
                show.start(primaryStage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        bt5.setOnAction((ActionEvent e) -> {
            RegistrationStatus show = new RegistrationStatus();
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

