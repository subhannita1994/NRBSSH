package sample;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map.Entry;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

	private  static Stage primaryStage;
	private static Controller oController;
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
    
    
    
    @Override
    public void stop() throws IOException{
    	
        try {
            File fileTwo=new File("userAllOptions.txt");
            FileOutputStream fos=new FileOutputStream(fileTwo);
            PrintWriter pw=new PrintWriter(fos);

            for(Entry<String, Boolean> m :Controller.allOptionsUerMap.entrySet()){
                pw.println(m.getKey()+"="+m.getValue());
            }

            pw.flush();
            pw.close();
            fos.close();
        } catch(Exception e) {
        	e.printStackTrace();
        }

        // Save file
        System.out.println("Stage is closing");
        
    }
    
    public static void setController(Controller oController) {
    	Main.oController = oController;
    }
}
