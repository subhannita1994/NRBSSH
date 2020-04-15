package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

//import java.awt.*;


public class InitController {

    @FXML TextArea opText;
    @FXML
    public Button closeButton;

    private static Stage stage=new Stage();	//has to remain static to let changeUserType work
    public static Stage getStage() { return stage; }
    public Controller oController;

    @FXML
    public void initialize() {
    	stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, e->{
            e.consume();
            
            try {
				oController._saveTempFile(oController.opText.getText());
				final Stage dialog = new Stage();
	            dialog.initModality(Modality.APPLICATION_MODAL);
	            Stage primaryStage=new Stage();
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("promptExit.fxml"));
	            Parent root = null;
				try {
					root = loader.load();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            primaryStage.setTitle("Exit");
	            primaryStage.setResizable(false);
	            dialog.initOwner(primaryStage);
	            dialog.setTitle("Exit");
	            dialog.setResizable(false);
	            Scene dialogScene = new Scene(root, 405, 124);
	            dialog.setScene(dialogScene);
	            dialog.show();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}	//auto-save
            
            
        });
    }
    
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
        this.oController= (Controller)loader.getController();
        Stage primaryStage= getStage();
        
        oController.makeAllVisible();
        oController.devOptionsMenu.setVisible(false);
        oController.generateCode.setVisible(false);
        oController.optimizeCode.setVisible(false);
        settingUserPreference(oController);
        oController.setUserMode("User mode: Novice");
        Stage stage = (Stage)closeButton.getScene().getWindow();
        stage.close();
        
        primaryStage.setTitle("FileName.cpp - Smart Gcc");
        primaryStage.setScene(new Scene(root, 640, 300));
        primaryStage.setMaximized(true);
        primaryStage.show();
        
        

    }

    @FXML
    private void settingUserPreference( Controller oController) throws IOException {
    	StringBuilder stringBuffer = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {

            bufferedReader = new BufferedReader(new FileReader(new File("userAllOptions.txt")));

            String text;
            while ((text = bufferedReader.readLine()) != null) {
            	StringTokenizer st=new StringTokenizer(text,"=",false);
            	String key=st.nextToken();
            	String val=st.nextToken();
            	
                
                if(key.equals("generateCode")) {
                	if(val.equals("true"))
                	{
                	oController.allOptionsGenerateCode.setVisible(false);
        			oController.generateCode.setVisible(true);
        			oController.allOptionsUerMap.put("generateCode", true);
                	}
                	else
                	{
                		oController.generateCode.setVisible(false);	
                	}
        		}
                else if(key.equals("devOptionsMenu")) {
                	if(val.equals("true"))
                	{
                	oController.allOptionsDevOptions.setVisible(false);
        			oController.devOptionsMenu.setVisible(true);
        			oController.allOptionsUerMap.put("devOptionsMenu", true);
                	}
                	else
                	{
                		oController.devOptionsMenu.setVisible(false);	
                	}
        		}
                else if(key.equals("optimizeCode")) {
                	if(val.equals("true"))
                	{
                	oController.allOptionsOptimizeCode.setVisible(false);
        			oController.optimizeCode.setVisible(true);
        			oController.allOptionsUerMap.put("optimizeCode", true);
                	}
                	else
                	{
                		oController.optimizeCode.setVisible(false);	
                	}
        		}
            }
            
            if(oController.optimizeCode.isVisible() && oController.devOptionsMenu.isVisible() && oController.generateCode.isVisible())
            {
            	oController.allOptions.setVisible(false);
            }
    	
        }
        
        catch(Exception e) {}
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
        this.oController= (Controller)loader.getController();
        Stage primaryStage= getStage();
        
        oController.makeAllVisible();
        oController.devOptionsMenu.setVisible(false);
        oController.allOptionsGenerateCode.setVisible(false);
        oController.allOptionsOptimizeCode.setVisible(false);
        oController.setUserMode("User mode: Typical");
        settingUserPreference(oController);
        Stage stage = (Stage)closeButton.getScene().getWindow();
        stage.close();
        
        primaryStage.setTitle("FileName.cpp - Smart Gcc");
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
        this.oController= (Controller)loader.getController();
        Stage primaryStage= getStage();
        
        oController.makeAllVisible();
        oController.allOptions.setVisible(false);
        oController.setUserMode("User mode: Expert");
        Stage stage = (Stage)closeButton.getScene().getWindow();
        stage.close();
        
        primaryStage.setTitle("FileName.cpp - Smart Gcc");

        primaryStage.setScene(new Scene(root, 640, 300));
        primaryStage.setMaximized(true);
        primaryStage.show();



    }

//    @FXML
//    Button saveyes;
//    @FXML
//    public void clickedonSave(ActionEvent event) throws IOException {
//        Controller con = new Controller();
//        con.saveAsFile();
//        InitController.getStage().close();
//        Stage stage = (Stage) saveno.getScene().getWindow();
//        stage.close();
//    }
//
//    @FXML
//    Button saveno;
//
//    @FXML
//    public void clickedonNoSave(ActionEvent event) throws IOException {
//        InitController.getStage().close();
//        Stage stage = (Stage) saveno.getScene().getWindow();
//        stage.close();
//    }
//
//
//    @FXML
//    public void askIfSaveWanted() throws IOException {
//    	System.out.println("ervwet");
//        final Stage dialog = new Stage();
//        dialog.initModality(Modality.APPLICATION_MODAL);
//        Stage primaryStage = new Stage();
//        Parent root = FXMLLoader.load(getClass().getResource("CloseDialogue.fxml"));
//        primaryStage.setTitle("Debug");
//        primaryStage.setResizable(false);
//        dialog.initOwner(primaryStage);
//        dialog.setTitle("Debug");
//        dialog.setResizable(false);
//        Scene dialogScene = new Scene(root, 405, 124);
//        dialog.setScene(dialogScene);
//        dialog.show();
//
//    }

	public static void close() {
		// TODO Auto-generated method stub
		
		stage.close();
		
	}


}