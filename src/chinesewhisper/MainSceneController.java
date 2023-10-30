/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package chinesewhisper;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Abdullah
 */
public class MainSceneController implements Initializable
{
    @FXML
    private Text Text_LineSep;
    @FXML
    private Text Text_LineSep1;
    @FXML
    private Text Text_LineSep2;
    @FXML
    private Text Text_LineSep3;
    @FXML
    private TextArea TA_phrase;

    // a reference to the CIC is necessary so you can get and put objects into the place lol
    CommonInstancesClass CIC = CommonInstancesClass.getInstance();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // ignore this
        Text_LineSep.setText(System.lineSeparator());
        Text_LineSep1.setText(System.lineSeparator());
        Text_LineSep2.setText(System.lineSeparator());
        Text_LineSep3.setText(System.lineSeparator());
        
        // initialize total count of windows
        int total_count = 0; // this is the first window right???
        
        // Putting something... REMEMBER THAT YOU MUST USE THIS EXACT STRING TO ACCESS THIS VARIABLE! Use copy and paste if in doubt.
        CIC.putObjectIfAbsent("in_total", total_count);
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) 
    {
        /* We wanna pass 2 pieces of information to the next scene (child)
        
                - A string (that the user typed in)
                - The nested level of window we are at (level), this is level 0
           
           We do this by using CIC to store (and retrieve) things into some place that ALL CLASSES can access.
           So the controller class of another Scene can easily retrieve them. 
        
           PLEASE MAKE SURE YOURE USING THE EXACT SAME STRING WHEN STORING AND RETRIEVING DATA. Just like how 
           the key to your house cannot be used to open your neighbor's house, a different string cannot be used
           to get the data that you want.
        */
        
        // Get and store string
        String userinput = TA_phrase.getText();
        CIC.putObjectIfAbsent("jeFnrf1bqD", userinput);  // it doesn't matter what the string is! just make sure you use the exact same string to get userinput back
        
        // Now this button may be pressed multiple times... In which case we will spawn multiple windows. 
        // So retrieve and increment that appropriately
        
        int in_total = (int) CIC.getObject("in_total"); // Retrieving, see how its the exact same as above.
        in_total++; // increment by 1 cause we made another window!
        CIC.overwriteObject("in_total", in_total); // now we put it back!
        
        // well we're done storing info, now we can switch to the next scene!
        // here we switch to the scene by creating a new window! Study the code of .createStageWithScene() to understand how it works.
        SceneSwitcher.createStagewithScene("ChildScene.fxml", false);
        // Now go to the ChildSceneController.java's code.
        
        TA_phrase.setEditable(false);
    }
    
}
