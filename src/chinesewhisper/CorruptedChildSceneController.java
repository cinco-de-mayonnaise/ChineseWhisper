/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package chinesewhisper;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Abdullah
 */
public class CorruptedChildSceneController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextArea TA_phrase;

    CommonInstancesClass CIC = CommonInstancesClass.getInstance();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        String value = (String) CIC.getObject("jeFnrf1bqD");   // key used to get string from previous window!!!!
        
        TA_phrase.setText(value);
        
        int in_total = (int) CIC.getObject("in_total");
        System.out.printf("%s | %d\n", value, in_total);
        label.setText("Scene #" + Integer.toString(in_total) + " said...");
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) 
    {
        String userinput = TA_phrase.getText();
        // we are in corrupted scene, let's do some corruption
        if (userinput.length() > 0) // lets not corrupt if its empty lol 
        {
            Random r = new Random();
        
            int corruptionproportion = r.nextInt(userinput.length()/2);
            
            char[] arr = userinput.toCharArray();
            for (int i = 0; i < corruptionproportion; i++)
            {
                arr[r.nextInt(arr.length)] += (r.nextInt(11) - 5); // mutate random part of the array by some value from -5 to 5
            }
            
            userinput = new String(arr);
        }
        
        
        CIC.overwriteObject("jeFnrf1bqD", userinput);  // if you want to overwrite over same identifier, you have to use this method
                                                                    // did it so you dont accidentally lose important data
        
        int in_total = (int) CIC.getObject("in_total"); // Retrieving, see how its the exact same as above.
        in_total++; // increment by 1 cause we made another window!
        CIC.overwriteObject("in_total", in_total); // now we put it back!
        
        SceneSwitcher.createStagewithScene("CorruptedChildScene.fxml", false);
        
        TA_phrase.setEditable(false);
    }

    @FXML
    private void ToggleCorruption(ActionEvent event) 
    {
        /* Here we don't spawn a new window, but switch to a new Scene inside the same window! 
        To do this, we need a reference to the Stage(window) whose Scene we want to change 
        In this case, we want to change the scene of the current window, which is why we call 'getStageFromEvent(event)' 
        which gives us a reference to the current stage!
        */
        SceneSwitcher.switchToScene(SceneSwitcher.getStageFromEvent(event), "ChildScene.fxml");
    }
    
}
