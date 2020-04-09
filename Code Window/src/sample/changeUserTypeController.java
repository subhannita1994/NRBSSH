package sample;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class changeUserTypeController {
	
	@FXML
	Button closeButtonChangeUserType;
	private Controller oController;
	
	@FXML
	public void changeUserType(ActionEvent event) throws IOException{
		String userChoice = ((Button)event.getSource()).getUserData().toString();
		Stage stage = (Stage)closeButtonChangeUserType.getScene().getWindow();
        stage.close();
        oController.makeAllVisible();
        if(userChoice.equals("novice")) {
        	System.out.println("user chose novice");
        	oController.devOptionsMenu.setVisible(false);
            oController.generateCode.setVisible(false);
            oController.optimizeCode.setVisible(false);
        }else if(userChoice.equals("typical")) {
        	System.out.println("user chose typical");
        	oController.devOptionsMenu.setVisible(false);
            oController.allOptionsGenerateCode.setVisible(false);
            oController.allOptionsOptimizeCode.setVisible(false);
        }else if(userChoice.equals("expert")) {
        	System.out.println("user chose expert");
        	oController.allOptions.setVisible(false);
            
        }
	}

	public void setGoBack(Controller controller) {
		// TODO Auto-generated method stub
		this.oController = controller;
		System.out.println("controller set");
	}

}
