package sample;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class fileController {

	private Controller oController;
	
	
	public void setGoBack(Controller controller) {
		// TODO Auto-generated method stub
		this.oController = controller;
	}
	
	@FXML
	public void goBack(ActionEvent event) throws IOException {
		Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.close();
		String id = ((Button)event.getSource()).getId();
		if(id.equals("yes")) {
			oController.openNewFile();
		}
		
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
    
    
    @FXML
    public void debug(ActionEvent event) throws IOException{
    	event.consume();
    	TextField textField = null;
    	for (Object o : ((Button)event.getSource()).getParent().getChildrenUnmodifiable()) {
    		if(o instanceof TextField) {
    			 textField = (TextField) o;
    			 break;
    		}
    	}

		this.oController.debugCMD(textField.getText());
    }

}
