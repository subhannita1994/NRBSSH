package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;

//import java.awt.*;


public class InitController {

    @FXML TextArea opText;
    @FXML
    public Button closeButton;

    private static Stage stage=new Stage();
    public static Stage getStage() { return stage; }
    

    @FXML
    private void startSceneNovice(ActionEvent event) throws IOException {
        event.consume();
        
        System.out.println("Hello Novice");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        Controller oController= (Controller)loader.getController();
        Stage primaryStage= getStage();
        
        oController.makeAllVisible();
        oController.devOptionsMenu.setVisible(false);
        oController.generateCode.setVisible(false);
        oController.optimizeCode.setVisible(false);
        
        Stage stage = (Stage)closeButton.getScene().getWindow();
        stage.close();
        
        primaryStage.setTitle("FileName.cpp - Smart Gcc");
        primaryStage.setScene(new Scene(root, 640, 300));
        primaryStage.setMaximized(true);
        primaryStage.show();
        
        

    }

    @FXML
    public void setTitle(String newLabel)
    {
        Stage primaryStage =new Stage();
        primaryStage.setTitle(newLabel);

    }

    @FXML
    private void startSceneTypical(ActionEvent event) throws IOException {
    	event.consume();
        
        System.out.println("Hello Typical");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        Controller oController= (Controller)loader.getController();
        Stage primaryStage= getStage();
        
        oController.makeAllVisible();
        oController.devOptionsMenu.setVisible(false);
        oController.allOptionsGenerateCode.setVisible(false);
        oController.allOptionsOptimizeCode.setVisible(false);
        
        Stage stage = (Stage)closeButton.getScene().getWindow();
        stage.close();
        
        primaryStage.setTitle("FileName.cpp - Smart Gcc");
        primaryStage.setScene(new Scene(root, 640, 300));
        primaryStage.setMaximized(true);
        primaryStage.show();

    }

    @FXML
    private void startSceneExpert(ActionEvent event) throws IOException {
    	event.consume();
        
        System.out.println("Hello Novice");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        Controller oController= (Controller)loader.getController();
        Stage primaryStage= getStage();
        
        oController.makeAllVisible();
        oController.allOptions.setVisible(false);
        
        Stage stage = (Stage)closeButton.getScene().getWindow();
        stage.close();
        
        primaryStage.setTitle("FileName.cpp - Smart Gcc");
        primaryStage.setScene(new Scene(root, 640, 300));
        primaryStage.setMaximized(true);
        primaryStage.show();



    }


}