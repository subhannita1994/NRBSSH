package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
//import java.awt.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.HashMap;


public class Controller {

    
	@FXML TextArea opText;
    @FXML TextArea ipText;
    @FXML TextField DebugText;
    private HashMap<String,String> devOptions = new HashMap<String,String>();
    
    

	/**
	 * 
	 */
	public Controller() {
		devOptions.put("dollar", "-fno-dollars-in-identifiers");
		devOptions.put("verbose", "-v");
		devOptions.put("warning", "-Wall");
		
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
        	System.out.println("No developer option exists for this alias");
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
            if(!res.contains(" error"))
            {
                ipText.setStyle("-fx-text-inner-color: green;");
                ipText.setText(res);
            }
            else if(!res.contains(" warning")) {
            	ipText.setStyle("-fx-text-inner-color: pink;");
                ipText.setText(res);
            }
            else{
                ipText.setStyle("-fx-text-inner-color: red;");
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
    @FXML
    public void find(String userMode){
        System.out.println(userMode);
        //return userMode;
    }


}