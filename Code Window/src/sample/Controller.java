package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
//import java.awt.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;


public class Controller implements CommandListener, Terminal{

    @FXML
    TextArea opText;
    @FXML
    TextArea ipText;
    @FXML
    Menu devOptionsMenu;
    @FXML
    Label SuccessMsg;
    @FXML Menu optimizeCode;
    @FXML Menu generateCode;
    @FXML Menu allOptions;
    @FXML MenuItem allOptionsGenerateCode;
    @FXML MenuItem allOptionsOptimizeCode;
    @FXML MenuItem allOptionsDevOptions;
    @FXML Button closeButtonChangeUserType;
    @FXML CheckMenuItem dev1;
    @FXML CheckMenuItem dev2;
    @FXML CheckMenuItem dev3;
    @FXML CheckMenuItem link1;
    @FXML CheckMenuItem link2;
    @FXML CheckMenuItem link3;
    @FXML CheckMenuItem gen1;
    @FXML CheckMenuItem gen2;
    @FXML CheckMenuItem gen3;
    @FXML RadioMenuItem opt1;
    @FXML RadioMenuItem opt2;
    @FXML RadioMenuItem opt3;
    @FXML RadioMenuItem opt4;
    @FXML RadioMenuItem opt5;
    
    private Command cmd;
    private int userInputStart = 0;
    private static HashMap<String, String> options = new HashMap<String, String>();
    private static HashMap<String, Boolean> selectedOptions = new HashMap<String, Boolean>();
    

    /**
     *
     */
    public Controller() {
    	options.put("dev1", "-dumpmachine");// g++ -ftime-report out.cpp -o -
    	options.put("dev2","-print-search-dirs");//g++ -print-search-dirs out.cpp -o -
    	options.put("dev3", "-save-temps"); //g++ -save-temps out.cpp -o -
//        options.put("dev1", "-fno-dollars-in-identifiers"); 
//        options.put("dev2", "-v");
//        options.put("dev3", "-Wall");
        options.put("link1", "-nostartfiles");//g++ -nostartfiles out.cpp -o -
        options.put("link2", "-r");
        options.put("link3", "-static-libstdc++");
        options.put("opt1", "-O1");//g++ -O1 out.cpp -o -
        options.put("opt2", "-O2");
        options.put("opt3", "-O3");
        options.put("opt4", "-Os");
        options.put("opt5", "-Ofast");//
        options.put("gen1", "-fverbose-asm");//g++ -S  out.cpp -fverbose-asm -o -
        options.put("gen2", "-fexceptions"); //g++ -S  out.cpp -fexceptions -o -
        options.put("gen3", "-fshort-enums"); //g++ -S  out.cpp -fshort-enums -o -

        InitController.getStage().setOnCloseRequest(event1 -> {
            event1.consume();
            System.out.println("Close Clicked");

            try {
                if (isSaved()) {
                    InitController.getStage().close();

                } else {
                    //askIfSaveWanted();
                    System.out.println(";jhgsss");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        
        cmd = new Command(this);
      
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


    @FXML
    public void initialize() { //try

    	ipText.setOnKeyPressed(event->{
			if (event.getCode() == KeyCode.ENTER){
		    	int range = ipText.getCaretPosition() - userInputStart;
				try {
					String text = ipText.getText().trim();
					text=text.substring(userInputStart, userInputStart+range);
					System.out.println("[" + text + "]");
					userInputStart += range;
					if (!cmd.isRunning()) {
						cmd.execute("");
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
    public boolean isSaved() throws IOException {
      if(InitController.getStage().getTitle() == "FileName.cpp - Smart Gcc")
      {return false;
      }
      return true;
    }
    @FXML
    private void close(ActionEvent event) throws IOException {
        if(isSaved()){
            InitController.close();
        }
        else{
            saveAsFile();
        }
    }
    
    /**
     * runs the code: sends the run configurations to _runcode()
     * @param event
     * @throws IOException
     */
    @FXML
    private void runCMD(ActionEvent event) throws IOException {
        event.consume();
         String a= opText.getText();
        System.out.println(a);
        //ipText.setEditable(true);
        String runOptions = "";
        boolean flag = false;
        for(String key : selectedOptions.keySet()) {
        	if(selectedOptions.get(key)==true)
        		runOptions+= " "+options.get(key);
        	if(key.contains("gen"))
        		flag = true;
        }
        if(flag)
        	runOptions = " -S "+runOptions;
        System.out.println("Running..."+runOptions);
        ipText.clear();
        _runCode(a,runOptions);
    }
    
    /**
     * action handler when any option is selected (except anything under all options): sets the HashMap value of corresponding option to true
     * @param event
     * @throws IOException
     */
    @FXML
    private void checkOption(ActionEvent event) throws IOException{
    	event.consume();
    	System.out.println("Adding options...");
    	String id = ((MenuItem)event.getSource()).getId();
    	if(event.getSource() instanceof RadioMenuItem) {	//its an optimization option
    		selectedOptions.put("opt1",false);
            selectedOptions.put("opt2",false);
            selectedOptions.put("opt3",false);
            selectedOptions.put("opt4",false);
            selectedOptions.put("opt5",false);
            selectedOptions.put(id, true);
    	}else {	//it could be linker/code generation/ developer options
    		Boolean checkedData = ((CheckMenuItem)event.getSource()).isSelected();
    		selectedOptions.put(id, checkedData);
    	}
    }
    
    
  
    public HashMap<String, String> getOptions() {
		return options;
	}
	public void setOptions(String key, String value) {
		options.put(key, value);
	}
	public HashMap<String, Boolean> getSelectedOptions() {
		return selectedOptions;
	}
	public void setSelectedOptions(String key, Boolean value) {
		selectedOptions.put(key, value);
	}
    private String _getPath()
    {
        String savePath = System.getProperty("user.dir") + System.getProperty("file.separator");
        return savePath;
    }

    private void _runCode(String Code,  String attachedCode) throws IOException {
//        String Location= _saveTempFile(Code);
//        Runtime runtime = Runtime.getRuntime();
//        ProcessBuilder builder = new ProcessBuilder();
//        //builder.command("cmd.exe", "/c", a);
//        //builder.command("cmd.exe", "/c", "cd"+Location+" && dir & java out.java"); // executing commands of gcc
//        final String os = System.getProperty("os.name").toLowerCase();
//        if(os.indexOf("win") >= 0)
//        	builder.command("cmd.exe", "/c", "cd \""+Location+"\"&& g++ "+attachedCode+" -o program out.cpp & .\\program"); // executing commands of gcc
//        else if(os.indexOf("mac") >= 0)	//adding compatibility for mac os
//        	builder.command("bash", "-c", "cd \""+Location+"\"&& g++ "+attachedCode+" -o program out.cpp && ./program");
//        builder.redirectErrorStream(true);
//        try {
//            Process p = builder.start();
//            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
//            String line;
//            String res = "";
//            while (true) {
//                line = r.readLine();
//                if (line == null) {
//                    break;
//                }
//                System.out.println(line);
//                res= res+"\n"+ line;
//
//            }
//            if(res.contains(" error"))
//            {
//                ipText.setStyle("-fx-text-inner-color: red;");
//                ipText.setText(res);
//            }
//            else if(res.contains(" warning")) {
//            	ipText.setStyle("-fx-text-inner-color: pink;");
//                ipText.setText(res);
//            }
//            else{
//                ipText.setStyle("-fx-text-inner-color: green;");
//                ipText.setText(res);
//            }
//
//
//        } catch (Exception e1) {
//        	
//            e1.printStackTrace();
//        }
    	
    	if (!cmd.isRunning()) {
			cmd.execute(attachedCode);
		} else {
			try {
				cmd.send("" + "\n");
			} catch (IOException ex) {
				appendTextValue("!! Failed to send command to process: " + ex.getMessage() + "\n");
			}
		}
    }

    /**
     * action handler for run button on the debug dialogue
     * @param event
     * @throws IOException
     */
    @FXML
    public void debugCMD(String attachedCode) throws IOException {
//        event.consume();
//        System.out.println("Debug Method");
//        String a= opText.getText();
//        System.out.println(a);
//        String attachedCode= DebugText.getText();
//        _runCode(a,attachedCode);
    	
    	String a = opText.getText();
    	System.out.println(a);
    	_runCode(a,attachedCode);
    	
    }
    
    /**
     * action handler for debug button
     * @param event
     * @throws IOException
     */
    @FXML
    public void debug(ActionEvent event) throws IOException {

        
        event.consume();
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("DebugDialog.fxml"));
    	Parent root = loader.load();
        fileController oController = (fileController)loader.getController();
        oController.setGoBack(this);
    	Stage primaryStage = Main.getStage();
    	primaryStage.setTitle("Debug");
        primaryStage.setScene(new Scene(root, 405, 124));
        primaryStage.setResizable(false);
        primaryStage.setMaximized(false);
        primaryStage.show();

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
    public String saveAsFile() throws IOException {
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
            System.out.println("Should display success label");
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
            //Set extension filter for text files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CPP files (*.cpp)", "*.cpp");
            FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("C files (*.c)", "*.c");
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.getExtensionFilters().add(extFilter2);
            Stage primaryStage=new Stage();
            File file = fileChooser.showSaveDialog(primaryStage);
            InitController.getStage().setTitle(file.toString()+"  - SmartGCC");
            if (file != null) {
                System.out.println("Should display success label");
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
/**
 * action handler for File->new
 * opens a new window from start
 * @param event
 * @throws IOException
 */
    @FXML
    public void newFile(ActionEvent event) throws IOException{
    	event.consume();
    	this.saveFile();
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("newFilePrompt.fxml"));
    	Parent root = loader.load();
        fileController oController = (fileController)loader.getController();
        oController.setGoBack(this);
    	Stage primaryStage = Main.getStage();
    	primaryStage.setTitle("New file");
        primaryStage.setScene(new Scene(root, 640, 350));
        primaryStage.setResizable(false);
        primaryStage.setMaximized(false);
        primaryStage.show();
    }


    
    public void openNewFile() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("scene1.fxml"));
    	Parent root = loader.load();
        Stage primaryStage = Main.getStage();
    	primaryStage.setTitle("Smart Gcc");
        primaryStage.setScene(new Scene(root, 640, 350));
        primaryStage.setResizable(false);
        primaryStage.setMaximized(false);
        primaryStage.show();
    }
    

    @FXML
    public void onSuccess() throws IOException {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        Stage primaryStage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("successDialogue.fxml"));
        Parent root = loader.load();
        fileController oController = (fileController) loader.getController();
        oController.setGoBack(this);
        primaryStage.setTitle("Success");
        primaryStage.setResizable(false);
        dialog.initOwner(primaryStage);
        dialog.setTitle("Success");
        dialog.initStyle(StageStyle.UNDECORATED);
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("errorDialogue.fxml"));
        Parent root = loader.load();
        fileController oController = (fileController) loader.getController();
        oController.setGoBack(this);
        primaryStage.setTitle("Error");
        primaryStage.setResizable(false);
        dialog.initOwner(primaryStage);
        dialog.setTitle("Error");
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.setResizable(false);
        //Label divider = new Label();
        Scene dialogScene = new Scene(root, 405, 124);
        dialog.setScene(dialogScene);
        dialog.show();

    }

    /**
     * action handler for change user type menuItem : launched the dialogue box changeUserType.fxml
     * @param event
     * @throws IOException
     */
   /* @FXML
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
    	
    }*/

    @FXML
    public void changeUserTypeDialog(ActionEvent event) throws IOException {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        Stage primaryStage=new Stage();
        //Parent root = FXMLLoader.load(getClass().getResource("changeUserType.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("changeUserType.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Change User Type Dialog");
        primaryStage.setResizable(false);
        dialog.initOwner(primaryStage);
        changeUserTypeController oController= (changeUserTypeController)loader.getController();
        oController.setGoBack(this);
        dialog.setTitle("Change User Type Dialog");
        dialog.setResizable(false);
        Scene dialogScene = new Scene(root, 640, 350);
        //primaryStage.setScene(new Scene(root, 596, 150));
        dialog.setScene(dialogScene);
        dialog.show();

    }
    
    

    
    @FXML
    public void find(String userMode){
        System.out.println(userMode);
        //return userMode;
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
	/**
	 * reset all checks against all options, i.e., configuration will now be run without any additional options until an option is selected
	 */
	@FXML
	public void resetSelectedOptions() {
		selectedOptions.put("dev1",false);
        selectedOptions.put("dev2",false);
        selectedOptions.put("dev3",false);
        selectedOptions.put("link1",false);
        selectedOptions.put("link2",false);
        selectedOptions.put("link3",false);
        selectedOptions.put("gen1",false);
        selectedOptions.put("gen2",false);
        selectedOptions.put("gen3",false);
        selectedOptions.put("opt1",true);	//O1 is selected by default
        selectedOptions.put("opt2",false);
        selectedOptions.put("opt3",false);
        selectedOptions.put("opt4",false);
        selectedOptions.put("opt5",false);
        
		dev1.setSelected(false);
		dev2.setSelected(false);
		dev3.setSelected(false);
		gen1.setSelected(false);
		gen2.setSelected(false);
		gen3.setSelected(false);
		link1.setSelected(false);
		link2.setSelected(false);
		link3.setSelected(false);
		opt1.setSelected(true);
		opt2.setSelected(false);
		opt3.setSelected(false);
		opt4.setSelected(false);
		opt5.setSelected(false);
	}
	@Override
	public int getUserInputStart() {
		// TODO Auto-generated method stub
		return userInputStart;
	}
	@Override
	public void appendTextValue(String text) {
		// TODO Auto-generated method stub
		ipText.appendText(text);
		updateUserInputPos();
	}
	@Override
	public void commandOutput(String text) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new AppendTask(this, "Command failed - " + exp.getMessage()));
		
	}
	
	protected void updateUserInputPos() {
		int pos = ipText.getCaretPosition();
		ipText.selectPositionCaret(ipText.getText().length());
		userInputStart = pos;

	}
	
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


    public void format1(ActionEvent actionEvent) throws IOException {
        CodeTab ct=new CodeTab();
        String fname=_saveTempFile(opText.getText());
	    ct.codeFormat(fname,1);
	    System.out.println("Done");
	    readfileAndSet(fname);

    }

    private void readfileAndSet(String fname) {
	    String name= "\\"+"out.cpp";
        File file = new File(fname+"\\temp.cpp");
        if(file != null){
            opText.setText(readFile(file));
        }
    }

    public void format2(ActionEvent actionEvent) throws IOException {
        CodeTab ct=new CodeTab();
        String fname=_saveTempFile(opText.getText());
        ct.codeFormat(fname,2);
        System.out.println("Done");
        readfileAndSet(fname);
    }
}

class ProcessRunner extends Thread {

	private Controller listener;
	private String attachedCode;

	private Process process;

	public ProcessRunner(Controller listener, String attachedCode) {
		this.listener = listener;
		this.attachedCode = attachedCode;
		start();
	}

	@Override
	public void run() {
		try {
			 String code= listener.getOpText().getText();
			 String Location= listener._saveTempFile(code);
//			 String attachedCode= "";
		        ProcessBuilder builder = new ProcessBuilder();
		        final String os = System.getProperty("os.name").toLowerCase();
		        if(os.indexOf("win") >= 0)
		        	builder.command("cmd.exe", "/c", "cd \""+Location+"\"&& g++ "+attachedCode+" -o program out.cpp  & .\\program"); // executing commands of gcc
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










