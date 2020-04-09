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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        System.out.println("Hello Novice");
        Stage primaryStage= getStage();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("FileName.cpp - Smart Gcc");
        //Controller oSetMode = loader.getController();
        Controller oController= loader.getController();
     //   oController.find("NOVICE");
        Stage stage = (Stage)closeButton.getScene().getWindow();
        stage.close();
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

    }

    @FXML
    private void startSceneExpert(ActionEvent event) throws IOException {
        event.consume();

        System.out.println("Hello Expert");



    }


}