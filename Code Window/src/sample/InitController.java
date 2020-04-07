package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.*;

//import java.awt.*;


public class InitController {

    @FXML TextArea opText;
    @FXML Button novice;
    @FXML Button confirmChange;
    
    /**
     * operations that are common when any type of user mode is selected
     * the screen corresponding to the user mode is launched
     * static variable Collector.userMode is set accordingly
     * Assumption: fxml files follow naming convention - sample<filename>.fxml
     * @param filename	: Novice/Typical/Expert
     * @throws IOException
     */
    private void commonOps( String filename) throws IOException {
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("sample"+filename+".fxml"));
    	System.out.println("Hello "+filename);
    	Stage primaryStage =new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("sample"+filename+".fxml"));
        Controller.userMode = filename.toLowerCase();	//set the user mode
        primaryStage.setTitle("FileName.cpp - Smart Gcc");
    	
        primaryStage.setScene(new Scene(root, 640, 300));
        primaryStage.setMaximized(true);
        primaryStage.show();
        System.out.println("User mode: "+Controller.userMode);
    }
    
    /**
     * closes the screen which asks user to select user mode
     */
    @FXML
    public void closeUserModeScene() {
    	Stage stage = (Stage)novice.getScene().getWindow();
    	stage.close();
    }
    
    /**
     * action handler for novice button in scene1.fxml
     * this handler is called at two events - 
     * 1. when application is launched for the first time in novice mode
     * 2. when user chooses to change user mode to novice
     * @param event
     * @throws IOException
     */
    @FXML
    private void startSceneNovice(ActionEvent event) throws IOException {
    	if(this.checkChangeUser(event)==1) {
        	//user wants to CHANGE user mode
    		System.out.println(Controller.userMode+" wants to change to Novice");
    		launchConfirmChangeUser("Novice");
        }else if(this.checkChangeUser(event)==-1) {
        	//user selected the same user mode that is going on
        	System.out.println(Controller.userMode+" wants to change to Novice -- closing");
        	
        }else {
        System.out.println("Novice user launched application");
        commonOps("Novice");
        }
    	closeUserModeScene();
    }

    /**
     * action handler for typical button in scene1.fxml
     * this handler is called at two events - 
     * 1. when application is launched for the first time in typical mode
     * 2. when user chooses to change user mode to typical
     * @param event
     * @throws IOException
     */
    @FXML
    private void startSceneTypical(ActionEvent event) throws IOException {
        if(this.checkChangeUser(event)==1) {
        	//user wants to CHANGE user mode
    		System.out.println(Controller.userMode+" wants to change to Typical");
        	launchConfirmChangeUser("Typical");
        }else if(this.checkChangeUser(event)==-1) {
        	//user selected the same user mode that is going on 
        	System.out.println(Controller.userMode+" wants to change to Typical -- closing");
        	
        }else {
        	//user has just launched the application
        	System.out.println("Typical user launched application");
        commonOps("Typical");
        }
        closeUserModeScene();
    }

    /**
     * action handler for expert button in scene1.fxml
     * this handler is called at two events - 
     * 1. when application is launched for the first time in expert mode
     * 2. when user chooses to change user mode to expert
     * @param event
     * @throws IOException
     */
    @FXML
    private void startSceneExpert(ActionEvent event) throws IOException {
    	if(this.checkChangeUser(event)==1) {
        	//user wants to CHANGE user mode
    		System.out.println(Controller.userMode+" wants to change to Expert");
    		launchConfirmChangeUser("Expert");
        }else if(this.checkChangeUser(event)==-1) {
        	//user selected the same user mode that is going on
        	System.out.println(Controller.userMode+" wants to change to Expert -- closing"); 
        	
        }else {
        
        	System.out.println("Expert user launched application");
        commonOps("Expert");
        }
    	closeUserModeScene();
    }
    
    /**
     * checks if actionhandlers are called during application launch or changing user mode
     * @param event
     * @return 0: user has just launched the application
     * 		  -1: user wants to change user mode but has selected the same user mode that he is in right now
     * 		   1: user wants to change user mode to a different user mode than what he is in right now
     */
    private int checkChangeUser(ActionEvent event) {
    	Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
    	String title = stage.getTitle();
    	String userChoice = ((Button) event.getSource()).getId();
    	if(title.contains("Change")) {
    		if(userChoice.equals(Controller.userMode))
    			return -1;
    		else
    			return 1;
    	}else
    		return 0;
    	
    }
    
    /**
     * launches the dialogue box that asks user to confirm selection of a different user mode
     * @param user
     * @throws IOException
     */
    @FXML
    public void launchConfirmChangeUser(String user) throws IOException {
    	final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        Stage primaryStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("userModeChangeConfirm.fxml"));
        primaryStage.setTitle("Confirm User Type - "+user);
        primaryStage.setResizable(false);
        dialog.initOwner(primaryStage);
        dialog.setTitle("Confirm User Type - "+user);
        dialog.setResizable(false);
        Scene dialogScene = new Scene(root, 640, 350);
        dialog.setScene(dialogScene);
        dialog.show();
    }
    
    /**
     * action handler for the "yes" and "cancel" buttons in dialogue box where user is asked to confirm change of user mode
     * If "yes" selected : launches the screen corresponding to the user mode selected previously
     * If "cancel" selected : does nothing
     * @param event
     * @throws IOException
     */
    @FXML
    public void confirmChangeUser(ActionEvent event) throws IOException {
    	String confirmation = ((Button)event.getSource()).getId();
    	Stage stage1 = (Stage) confirmChange.getScene().getWindow();
    	stage1.close();
    	if(confirmation.equals("confirmChange")) {
    		//user confirmed change of user type
    		System.out.println("User confirmed change");
    		Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
    		String title = stage.getTitle();
    		if(title.contains("Novice")) {
    			this.commonOps("Novice");
    		}else if(title.contains("Typical")) {
    			this.commonOps("Typical");
    		}else if(title.contains("Expert")) {
    			this.commonOps("Expert");
    		}
    	}else {
    		System.out.println("User did not confirm change");
    	}
    	
    	
    }


}