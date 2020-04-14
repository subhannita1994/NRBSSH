package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.*;

//import java.awt.*;


public class InitController {

    @FXML TextArea opText;
    @FXML
    public Button closeButton;

    private static Stage stage=new Stage();	//has to remain static to let changeUserType work
    public static Stage getStage() { return stage; }
    
/**
 * loads novice screen with default options for novice
 * @param event
 * @throws IOException
 */
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
        primaryStage.setOnCloseRequest(event1 -> {
            event1.consume();
            System.out.println("Close Clicked");
            Controller con = new Controller();

            try {
                if (con.isSaved()) {
                    InitController.getStage().close();

                } else {
                    askIfSaveWanted();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
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
    
    /**
     * loads typical screen with default options for typical
     * @param event
     * @throws IOException
     */
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
        primaryStage.setOnCloseRequest(event1 -> {
            event1.consume();
            System.out.println("Close Clicked");
            Controller con = new Controller();

            try {
                if (con.isSaved()) {
                    InitController.getStage().close();

                } else {
                    askIfSaveWanted();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        primaryStage.setScene(new Scene(root, 640, 300));
        primaryStage.setMaximized(true);
        primaryStage.show();

    }
    
    /**
     * loads expert screen with default options for expert
     * @param event
     * @throws IOException
     */
    @FXML
    private void startSceneExpert(ActionEvent event) throws IOException {
    	event.consume();
        
        System.out.println("Hello Expert");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        Controller oController= (Controller)loader.getController();
        Stage primaryStage= getStage();
        
        oController.makeAllVisible();
        oController.allOptions.setVisible(false);
        
        Stage stage = (Stage)closeButton.getScene().getWindow();
        stage.close();
        
        primaryStage.setTitle("FileName.cpp - Smart Gcc");
        primaryStage.setOnCloseRequest(event1 -> {
            event1.consume();
            System.out.println("Close Clicked");
            Controller con = new Controller();

            try {
                if (con.isSaved()) {
                    InitController.getStage().close();

                } else {
                    askIfSaveWanted();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        primaryStage.setScene(new Scene(root, 640, 300));
        primaryStage.setMaximized(true);
        primaryStage.show();



    }

    @FXML
    Button saveyes;
    @FXML
    public void clickedonSave(ActionEvent event) throws IOException {
        Controller con = new Controller();
        con.saveAsFile();
        InitController.getStage().close();
        Stage stage = (Stage) saveno.getScene().getWindow();
        stage.close();
    }

    @FXML
    Button saveno;

    @FXML
    public void clickedonNoSave(ActionEvent event) throws IOException {
        InitController.getStage().close();
        Stage stage = (Stage) saveno.getScene().getWindow();
        stage.close();
    }


    @FXML
    public void askIfSaveWanted() throws IOException {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("CloseDialogue.fxml"));
        primaryStage.setTitle("Debug");
        primaryStage.setResizable(false);
        dialog.initOwner(primaryStage);
        dialog.setTitle("Debug");
        dialog.setResizable(false);
        Scene dialogScene = new Scene(root, 405, 124);
        dialog.setScene(dialogScene);
        dialog.show();

    }

	public static void close() {
		// TODO Auto-generated method stub
		
		stage.close();
		
	}


}