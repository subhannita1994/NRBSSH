package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map.Entry;

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
        
        
		oController.resetSelectedOptions();
        oController.makeAllVisible();
        
        if(userChoice.equals("novice")) {
        	System.out.println("user chose novice");
        	oController.devOptionsMenu.setVisible(false);
            oController.generateCode.setVisible(false);
            oController.optimizeCode.setVisible(false);        
            oController.setUserMode("User mode: Novice");

        }else if(userChoice.equals("typical")) {
        	System.out.println("user chose typical");
        	oController.devOptionsMenu.setVisible(false);
            oController.allOptionsGenerateCode.setVisible(false);
            oController.allOptionsOptimizeCode.setVisible(false);
            oController.setUserMode("User mode: Typical");

        }else if(userChoice.equals("expert")) {
        	System.out.println("user chose expert");
        	oController.allOptions.setVisible(false);
            oController.setUserMode("User mode: Expert");

        }
        settingUserPreference(oController);
	}

	public void setGoBack(Controller controller) {
		// TODO Auto-generated method stub
		this.oController = controller;
		System.out.println("controller set");
	}
	
	@FXML
	public void cancel(ActionEvent event) throws IOException{
		Stage stage = (Stage)closeButtonChangeUserType.getScene().getWindow();
        stage.close();
	}

	@FXML
    private void settingUserPreference( Controller oController) throws IOException {
    	StringBuilder stringBuffer = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {

        	for(Entry<String, Boolean> m :oController.allOptionsUerMap.entrySet()){
                if(m.getKey().equals("generateCode")) {
                	if(m.getValue())
                	{
                	oController.allOptionsGenerateCode.setVisible(false);
        			oController.generateCode.setVisible(true);
                	}
                	else
                	{
                		oController.generateCode.setVisible(false);	
                	}
        		}
                else if(m.getKey().equals("devOptionsMenu")) {
                	if(m.getValue())
                	{
                	oController.allOptionsDevOptions.setVisible(false);
        			oController.devOptionsMenu.setVisible(true);
                	}
                	else
                	{
                		oController.devOptionsMenu.setVisible(false);	
                	}
        		}
                else if(m.getKey().equals("optimizeCode")) {
                	if(m.getValue())
                	{
                	oController.allOptionsOptimizeCode.setVisible(false);
        			oController.optimizeCode.setVisible(true);
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
	
}
