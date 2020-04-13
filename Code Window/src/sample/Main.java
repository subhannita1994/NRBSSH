package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	private  static Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception{
    	Main.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("scene1.fxml"));
        primaryStage.setTitle("Smart Gcc");
        primaryStage.setScene(new Scene(root, 640, 350));
        primaryStage.setResizable(false);
        primaryStage.setMaximized(false);
        primaryStage.show();
    }

    public static Stage getStage() {
    	return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
