package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

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
        }else if(userChoice.equals("typical")) {
        	System.out.println("user chose typical");
        	oController.devOptionsMenu.setVisible(false);
            oController.allOptionsGenerateCode.setVisible(false);
            oController.allOptionsOptimizeCode.setVisible(false);
        }else if(userChoice.equals("expert")) {
        	System.out.println("user chose expert");
        	oController.allOptions.setVisible(false);
            
        }
        settingUserPreference(oController);
	}

	public void setGoBack(Controller controller) {
		// TODO Auto-generated method stub
		this.oController = controller;
		System.out.println("controller set");
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
	
}
