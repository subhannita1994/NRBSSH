package sample;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
//import java.awt.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Controller implements CommandListener, Terminal {

    
	public TextArea getOpText() {
		return opText;
	}

	public void setOpText(TextArea opText) {
		this.opText = opText;
	}

	public TextArea getIpText() {
		return ipText;
	}

	public void setIpText(TextArea ipText) {
		this.ipText = ipText;
	}

	@FXML  TextArea opText;
    @FXML  TextArea ipText;
    @FXML  TextField DebugText;
    @FXML static Menu devOptionsMenu;
    private int userInputStart = 0;
	private Command cmd;
    private HashMap<String,String> devOptions = new HashMap<String,String>();
    
    

	/**
	 * 
	 */
	public Controller() {
		devOptions.put("dollar", "-fno-dollars-in-identifiers");
		devOptions.put("verbose", "-v");
		devOptions.put("warning", "-Wall");
		cmd = new Command(this);
		
	}

	@FXML
    public void initialize() {
		ipText.setOnKeyPressed(event->{
			if (event.getCode() == KeyCode.ENTER){
		    	int range = ipText.getCaretPosition() - userInputStart;
				try {
					String text = ipText.getText().trim();
					text=text.substring(userInputStart, userInputStart+range);
					System.out.println("[" + text + "]");
					userInputStart += range;
					if (!cmd.isRunning()) {
						cmd.execute();
					} else {
						try {
							cmd.send(text + "\n");
						} catch (IOException ex) {
							appendTextValue("!! Failed to send command to process: " + ex.getMessage() + "\n");
						}
					}
				} catch (Exception ex) {
					Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
				}
		    }
		}); 
    }
	
    @FXML
    private void runCMD(ActionEvent event) throws IOException {
        event.consume();
        System.out.println("Running(Normal configuration)...");
         String a= opText.getText();
       // System.out.println(a);
        if (!cmd.isRunning()) {
			cmd.execute();
		} else {
			try {
				cmd.send("" + "\n");
			} catch (IOException ex) {
				appendTextValue("!! Failed to send command to process: " + ex.getMessage() + "\n");
			}
		}
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

    public String _saveTempFile(String code) throws IOException {
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
    
    @Override
	public void commandOutput(String text) {
    	if(text.contains(" error"))
        {
            ipText.setStyle("-fx-text-inner-color: red;");
        }
        else if(text.contains(" warning")) {
        	ipText.setStyle("-fx-text-inner-color: pink;");
        }
        else{
            ipText.setStyle("-fx-text-inner-color: green;");
        }

		SwingUtilities.invokeLater(new AppendTask(this, text));
	}

	@Override
	public void commandFailed(Exception exp) {
		SwingUtilities.invokeLater(new AppendTask(this, "Command failed - " + exp.getMessage()));
	}

	protected void updateUserInputPos() {
		int pos = ipText.getCaretPosition();
		ipText.selectPositionCaret(ipText.getText().length());
		userInputStart = pos;

	}

	@Override
	public int getUserInputStart() {
		return userInputStart;
	}

	@Override
	public void appendTextValue(String text) {
		ipText.appendText(text);
		updateUserInputPos();
	}


}

 class ProcessRunner extends Thread {

	private Controller listener;

	private Process process;

	public ProcessRunner(Controller listener) {
		this.listener = listener;
		start();
	}

	@Override
	public void run() {
		try {
			 String code= listener.getOpText().getText();
			 String Location= listener._saveTempFile(code);
			 String attachedCode= "";
		        ProcessBuilder builder = new ProcessBuilder();
		        final String os = System.getProperty("os.name").toLowerCase();
		        if(os.indexOf("win") >= 0)
		        	builder.command("cmd.exe", "/c", "cd \""+Location+"\"&& g++ "+attachedCode+" -o program out.cpp & .\\program"); // executing commands of gcc
		        else if(os.indexOf("mac") >= 0)	//adding compatibility for mac os
		        	builder.command("bash", "-c", "cd \""+Location+"\"&& g++ "+attachedCode+" -o program out.cpp && ./program");
		        builder.redirectErrorStream(true);
			
		             process = builder.start();
		            StreamReader reader = new StreamReader(listener, process.getInputStream());
		            BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));
		            String line;
		            String res = "";
		            int result = process.waitFor();
		            reader.join();
//		            while (true) {
//		                line = r.readLine();
//		                if (line == null) {
//		                    break;
//		                }
//		                System.out.println(line);
//		                res= res+"\n"+ line;
//
//		            }
		            
		        }
		        catch (Exception exp) {
					exp.printStackTrace();
					listener.commandFailed(exp);
				}
			// Need a stream writer...
	}

	public void write(String text) throws IOException {
		if (process != null && process.isAlive()) {
			process.getOutputStream().write(text.getBytes());
			process.getOutputStream().flush();
		}
	}
}
