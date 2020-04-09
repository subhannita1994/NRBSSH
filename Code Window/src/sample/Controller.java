package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
//import java.awt.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;


import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controller{

    @FXML
    TextArea opText;
    @FXML
    TextArea ipText;
    @FXML
    TextField DebugText;
    @FXML
    Menu devOptionsMenu;
    @FXML
    Label SuccessMsg;
    @FXML MenuItem optimizeCode;
    @FXML MenuItem generateCode;
    @FXML Menu allOptions;
    @FXML MenuItem allOptionsGenerateCode;
    @FXML MenuItem allOptionsOptimizeCode;
    @FXML MenuItem allOptionsDevOptions;
    @FXML Button closeButtonChangeUserType;
    
    private static HashMap<String, String> devOptions = new HashMap<String, String>();

    /**
     *
     */
    public Controller() {
        devOptions.put("dollar", "-fno-dollars-in-identifiers");
        devOptions.put("verbose", "-v");
        devOptions.put("warning", "-Wall");
        
    }
    
    @FXML
    public void initialize() {
    	
    }
    
    
    
    @FXML
    public boolean isSaved() throws IOException {
      if(InitController.getStage().getTitle() == "FileName.cpp - Smart Gcc")
      {return false; }
      return true;
    }
    @FXML
    private void close(ActionEvent event) throws IOException {
        if(isSaved()){
            InitController.getStage().close();
        }
        else{
            saveAsFile();
        }
    }
    @FXML
    private void runCMD(ActionEvent event) throws IOException {
        event.consume();
        System.out.println("Running(Normal configuration)...");
         String a= opText.getText();
        System.out.println(a);
        _runCode(a,"");
    }
    @FXML
    private void runDev(ActionEvent event) throws IOException {
        event.consume();
        System.out.println("Running(Developer option)...");
         String a= opText.getText();
        System.out.println(a);
        String data = ((MenuItem)event.getSource()).getId();
        String devOption = this.devOptions.get(data);
        if(devOption==null) {
        	System.out.println("No developer option exists for this alias: "+data);
        	devOption = "";
        }
        _runCode(a,devOption);
    }
    public HashMap<String, String> getDevOptions() {
		return devOptions;
	}
	public void setDevOptions(String key, String value) {
		this.devOptions.put(key, value);
	}
    private String _getPath()
    {
        String savePath = System.getProperty("user.dir") + System.getProperty("file.separator");
        return savePath;
    }

    private void _runCode(String Code,  String attachedCode) throws IOException {
        String Location= _saveTempFile(Code);
        Runtime runtime = Runtime.getRuntime();
        ProcessBuilder builder = new ProcessBuilder();
        //builder.command("cmd.exe", "/c", a);
        //builder.command("cmd.exe", "/c", "cd"+Location+" && dir & java out.java"); // executing commands of gcc
        final String os = System.getProperty("os.name").toLowerCase();
        if(os.indexOf("win") >= 0)
        	builder.command("cmd.exe", "/c", "cd \""+Location+"\"&& g++ "+attachedCode+" -o program out.cpp & .\\program"); // executing commands of gcc
        else if(os.indexOf("mac") >= 0)	//adding compatibility for mac os
        	builder.command("bash", "-c", "cd \""+Location+"\"&& g++ "+attachedCode+" -o program out.cpp && ./program");
        builder.redirectErrorStream(true);
        try {
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String line;
            String res = "";
            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }
                System.out.println(line);
                res= res+"\n"+ line;

            }
            if(res.contains(" error"))
            {
                ipText.setStyle("-fx-text-inner-color: red;");
                ipText.setText(res);
            }
            else if(res.contains(" warning")) {
            	ipText.setStyle("-fx-text-inner-color: pink;");
                ipText.setText(res);
            }
            else{
                ipText.setStyle("-fx-text-inner-color: green;");
                ipText.setText(res);
            }


        } catch (Exception e1) {
        	
            e1.printStackTrace();
        }
    }

    @FXML
    private void debugCMD(ActionEvent event) throws IOException {
        event.consume();
        System.out.println("Debug Method");
        String a= opText.getText();
        System.out.println(a);
        String attachedCode= DebugText.getText();
        _runCode(a,attachedCode);
    }
    
    @FXML
    public void debug(ActionEvent event) throws IOException {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        Stage primaryStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("DebugDialog.fxml"));
        primaryStage.setTitle("Debug");
        primaryStage.setResizable(false);
        dialog.initOwner(primaryStage);
        dialog.setTitle("Debug");
        dialog.setResizable(false);
        Scene dialogScene = new Scene(root, 405, 124);
        dialog.setScene(dialogScene);
        dialog.show();

    }
    
    private String _saveTempFile(String code) throws IOException {
        String savePath = _getPath();
        File saveLocation = new File(savePath);
        String Location=  System.getProperty("user.dir");
        if(saveLocation.exists()){
            //saveLocation.mkdir();
            File myFile = new File(savePath, "out.cpp");
            PrintWriter textFileWriter = new PrintWriter(new FileWriter(myFile));
            textFileWriter.write(code);
            textFileWriter.close();
        }
        return Location;
    }

    @FXML
    public void fileInput(ActionEvent arg0) {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CPP files (*.cpp)", "*.cpp");
        FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("C files (*.c)", "*.c");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.getExtensionFilters().add(extFilter2);

        //Show save file dialog
        Stage primaryStage=new Stage();
        File file = fileChooser.showOpenDialog(primaryStage);
        if(file != null){
            opText.setText(readFile(file));
        }
    }

    @FXML
    private String saveAsFile() throws IOException {
        FileChooser fileChooser = new FileChooser();
        String msg="Success";

        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CPP files (*.cpp)", "*.cpp");
        FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("C files (*.c)", "*.c");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.getExtensionFilters().add(extFilter2);
        Stage primaryStage=new Stage();
        File file = fileChooser.showSaveDialog(primaryStage);
        InitController.getStage().setTitle(file.toString()+"  - SmartGCC");



        if (file != null) {
            saveTextToFile(opText.getText(), file);
            this.onSuccess();
            return msg;
        }
        else{
            this.onError();
            return msg="Error";
        }
    }

    @FXML
    private void saveFile() throws IOException {
        if(!isSaved()){
            FileChooser fileChooser = new FileChooser();
            String msg="Success";
            //Set extension filter for text files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CPP files (*.cpp)", "*.cpp");
            FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("C files (*.c)", "*.c");
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.getExtensionFilters().add(extFilter2);
            Stage primaryStage=new Stage();
            File file = fileChooser.showSaveDialog(primaryStage);
            InitController.getStage().setTitle(file.toString()+"  - SmartGCC");
            if (file != null) {
                saveTextToFile(opText.getText(), file);
                this.onSuccess();
            }
            else{
                this.onError();
            }
        }
        else{
            File file = new File(InitController.getStage().getTitle());
            saveTextToFile(opText.getText(), file);
        }

    }


    private void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String readFile(File file){
        StringBuilder stringBuffer = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {

            bufferedReader = new BufferedReader(new FileReader(file));

            String text;
            while ((text = bufferedReader.readLine()) != null) {
                stringBuffer.append(text+"\n");
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return stringBuffer.toString();
    }

    

    @FXML
    public void onSuccess() throws IOException {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        Stage primaryStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("SuccessDialog.fxml"));
        primaryStage.setTitle("Success");
        primaryStage.setResizable(false);
        dialog.initOwner(primaryStage);
        dialog.setTitle("Success");
        dialog.setResizable(false);
        //Label divider = new Label();
        Scene dialogScene = new Scene(root, 405, 124);
        dialog.setScene(dialogScene);
        dialog.show();

    }

    @FXML
    public void onError() throws IOException {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        Stage primaryStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ErrorDialog.fxml"));
        primaryStage.setTitle("Error");
        primaryStage.setResizable(false);
        dialog.initOwner(primaryStage);
        dialog.setTitle("Error");
        dialog.setResizable(false);
        //Label divider = new Label();
        Scene dialogScene = new Scene(root, 405, 124);
        dialog.setScene(dialogScene);
        dialog.show();

    }

    @FXML
    public void changeUserTypeDialog(ActionEvent event) throws IOException{
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("changeUserType.fxml"));
        Parent root = loader.load();
        Stage primaryStage= new Stage();
        changeUserTypeController oController= (changeUserTypeController)loader.getController();
        oController.setGoBack(this);
        
        primaryStage.setTitle("Change User Type");
        primaryStage.setScene(new Scene(root, 640, 350));
        primaryStage.setResizable(false);
        primaryStage.setMaximized(false);
        primaryStage.show();
    	
    }
    
    

    
    @FXML
    public void find(String userMode){
        System.out.println(userMode);
        //return userMode;
    }
    
    /**
     * resets the fields whose IDs are passed as a space separated string in userData of the button
     * the userData attribute in xml MUST follow the format as shown in addDevOptionDialogue.fxml:line17:userData
     * the text fields to be resetted MUST share the same scene as this button
     * @param event -- button whose onAction has trigerred this method. MUST be a button or any child of class Node
     * @throws IOException 
     */
    @FXML
    public void resetFields(ActionEvent event) throws IOException{
    	event.consume();
    	String data = (String) ((Node)event.getSource()).getUserData();
    	System.out.println("Resetting text fields: "+ data);
    	String[] textFieldIDs = data.split(" ");
    	Scene scene = ((Node)event.getSource()).getScene();
    	for(String id : textFieldIDs) {
    		TextField tf = (TextField)scene.lookup("#"+id);
    		tf.clear();
    	}
    	
    }

    /**
     * make all options visible before setting some off
     * otherwise when you switch from (example:) typical to novice, the options that are by default invisible for typical remain invisible
     */
	public void makeAllVisible() {
		this.generateCode.setVisible(true);
		this.optimizeCode.setVisible(true);
		this.allOptions.setVisible(true);
		this.allOptionsDevOptions.setVisible(true);
		this.allOptionsGenerateCode.setVisible(true);
		this.allOptionsOptimizeCode.setVisible(true);
		this.devOptionsMenu.setVisible(true);
	}
	
	/**
	 * make a particular component visible when user selects from all options
	 * @param event
	 */
	@FXML
	public void makeComponentVisible(ActionEvent event) {
		event.consume();
		String id = ((MenuItem)event.getSource()).getId();
		if(id.equals("allOptionsGenerateCode")) {
			allOptionsGenerateCode.setVisible(false);
			generateCode.setVisible(true);
		}else if(id.equals("allOptionsOptimizeCode")) {
			allOptionsOptimizeCode.setVisible(false);
			optimizeCode.setVisible(true);
		}else if(id.equals("allOptionsDevOptions")) {
			allOptionsDevOptions.setVisible(false);
			devOptionsMenu.setVisible(true);
		}
	}


}








